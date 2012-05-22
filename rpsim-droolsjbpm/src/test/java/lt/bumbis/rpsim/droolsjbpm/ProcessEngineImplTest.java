package lt.bumbis.rpsim.droolsjbpm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.runtime.process.WorkflowProcessInstance;
import org.drools.runtime.rule.FactHandle;
import org.jbpm.ruleflow.instance.RuleFlowProcessInstance;
import org.jbpm.test.JbpmJUnitTestCase;
import org.junit.Test;

public class ProcessEngineImplTest extends JbpmJUnitTestCase {

	@Test
	public void testAddChangeSet() {
		ProcessEngineImpl engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet1.xml"));
		assertEquals(1, engine.newKnowledgeBase().getProcesses().size());
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
		assertEquals(3, engine.newKnowledgeBase().getProcesses().size());
	}
	
	@Test
	public void testStartEngine() {
		ProcessEngineImpl engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet1.xml"));
		assertNotNull("Engine not started - Knowledge session is Null", ((ProcessEngineImpl)engine.startEngine()).getKnowledgeSession());
		assertEquals("pseudo", ((ProcessEngineImpl)engine.startEngine()).getKnowledgeSession().getSessionConfiguration().getOption(ClockTypeOption.class).getClockType());
	}
	
	@Test
	public void testStartProcess() {
		ProcessEngineImpl engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
		engine.startEngine();
		long processId = engine.startProcess("changeSet2_process1");
		assertProcessInstanceActive(processId, engine.getKnowledgeSession());
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertProcessInstanceActive(processId, engine.getKnowledgeSession());
	}
	
	@Test
	public void testSyncTime1() {
		ProcessEngineImpl engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
		engine.startEngine();
		long processId = engine.startProcess("changeSet2_process1");
		engine.syncTime(3, TimeUnit.MILLISECONDS);
		engine.syncTime(4, TimeUnit.MILLISECONDS);
		assertProcessInstanceActive(processId, engine.getKnowledgeSession());
		engine.syncTime(5, TimeUnit.MILLISECONDS);
		assertProcessInstanceCompleted(processId, engine.getKnowledgeSession());	
	}
	
	@Test
	public void testSyncTime2() {
		ProcessEngineImpl engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
		engine.startEngine();
		long processId = engine.startProcess("changeSet2_process1");
		engine.syncTime(1, TimeUnit.MINUTES);
		assertProcessInstanceCompleted(processId, engine.getKnowledgeSession());	
	}
	
	@Test
	public void testHandlerAndEvents() {
		TestSimEngine simEngine = new TestSimEngine(true);
		ProcessEngineImpl procEngine = new ProcessEngineImpl(simEngine);
		procEngine.addChangeSet(ResourceFactory.newClassPathResource("changeSet3.xml"));
		procEngine.startEngine();
		long processId = procEngine.startProcess("changeSet3_process1");
		assertProcessInstanceCompleted(processId, procEngine.getKnowledgeSession());
		assertEquals(1, simEngine.getNewProcessArrivalCounter());
		assertEquals(1, simEngine.getNewProcessCompleteionCounter());
		assertEquals(1, simEngine.getNewServiceRequestCounter());
	}
	
	@Test
	public void testRulesEnablement() {
		TestSimEngine simEngine = new TestSimEngine(true);
		ProcessEngineImpl procEngine = new ProcessEngineImpl(simEngine);
		procEngine.setEnableRules(true);
		procEngine.addChangeSet(ResourceFactory.newClassPathResource("changeSet4.xml"));
		procEngine.startEngine();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("test", new TestModelA("a", 1) );
		procEngine.startProcess("changeSet4_process1", data);
		Collection<FactHandle> factHandles = procEngine.getKnowledgeSession().getFactHandles();
		assertEquals(2, factHandles.size());
		for (Iterator<FactHandle> i = factHandles.iterator(); i.hasNext();	) {
			FactHandle factHandle = i.next(); 
			if ( factHandle.getClass().equals(String.class)) {
				assertEquals("Test", procEngine.getKnowledgeSession().getObject(factHandle));
			} else if ( factHandle.getClass().equals(RuleFlowProcessInstance.class)) {
				assertEquals(RuleFlowProcessInstance.class, procEngine.getKnowledgeSession().getObject(factHandle).getClass());
			}
		}
	}
	
	@Test
	public void test() {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("changeSet4_rules.drl"), ResourceType.DRL);
		if( kbuilder.hasErrors() ) {
		    System.out.println( kbuilder.getErrors() );
		}
		assertTrue("Rules has errors", ! kbuilder.hasErrors());
	}
	
	
}
