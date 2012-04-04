package lt.bumbis.rpsim.droolsjbpm;

import java.util.concurrent.TimeUnit;
import org.drools.io.ResourceFactory;
import org.drools.runtime.conf.ClockTypeOption;
import org.jbpm.test.JbpmJUnitTestCase;
import org.junit.Test;

public class ProcessEngineImplTest extends JbpmJUnitTestCase {

	@Test
	public void testAddChangeSet() {
		ProcessEngineImpl engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet1.xml"));
		assertEquals(1, engine.getKnowledgeBase().getProcesses().size());
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
		assertEquals(3, engine.getKnowledgeBase().getProcesses().size());
	}
	
	@Test
	public void testStartEngine() {
		ProcessEngineImpl engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet1.xml"));
		assertNotNull("Engine not starte - Knowledge session is Null", ((ProcessEngineImpl)engine.startEngine()).getKnowledgeSession());
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
	public void testSetAdvanceTime() {
		ProcessEngineImpl engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
		engine.startEngine();
		long processId = engine.startProcess("changeSet2_process1");
		assertProcessInstanceActive(processId, engine.getKnowledgeSession());
		engine.setAdvanceTime(2, TimeUnit.MILLISECONDS);
		engine.setAdvanceTime(3, TimeUnit.MILLISECONDS);
		assertProcessInstanceCompleted(processId, engine.getKnowledgeSession());	
	}
	
	@Test
	public void testSetTime() {
		ProcessEngineImpl engine = new ProcessEngineImpl();
		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
		engine.startEngine();
		long processId = engine.startProcess("changeSet2_process1");
		engine.setTime(3);
		engine.setTime(4);
		assertProcessInstanceActive(processId, engine.getKnowledgeSession());
		engine.setTime(5);
		assertProcessInstanceCompleted(processId, engine.getKnowledgeSession());	
	}
}