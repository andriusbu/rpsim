package lt.bumbis.rpsim;

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
	
//	@Test
//	public void testStep() {
//		ProcessEngineImpl engine = new ProcessEngineImpl();
//		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
//		engine.startEngine(false);
//		ProcessInstance process = engine.startProcess("changeSet2_process1");
//		PseudoClockScheduler clock = engine.getKnowledgeSession().getSessionClock();
//		assertEquals(2, clock.getTimeToNextJob());
//		engine.step(1, TimeUnit.MILLISECONDS);
//		assertProcessInstanceActive(process.getId(), engine.getKnowledgeSession());
//		engine.step(2, TimeUnit.MILLISECONDS);
//		assertProcessInstanceCompleted(process.getId(), engine.getKnowledgeSession());
//	}
	
//	//TODO it is not necessary to have such functionality and test
//	@Test
//	public void testStartProcessRealTime() {
//		ProcessEngineImpl engine = new ProcessEngineImpl();
//		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet2.xml"));
//		engine.startEngine(true);
//		ProcessInstance process = engine.startProcess("changeSet2_process1");
//		assertNotNull("Process instance not created", process);
//		assertProcessInstanceActive(process.getId(), engine.getKnowledgeSession());
//		try {
//			Thread.sleep(50);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		assertProcessInstanceCompleted(process.getId(), engine.getKnowledgeSession());
//	}
	
	
//	@Test
//	public void testGetKnowledgeSession () {
//		ProcessEngineImpl engine = new ProcessEngineImpl();
//		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet1.xml"));
//		engine.startEngine();
//		assertNotNull("Knowledge session is not returned", engine.getKnowledgeSession());
//	}
	
//	@Test
//	public void testStartProcess() {
//		ProcessEngineImpl engine = new ProcessEngineImpl();
//		engine.addChangeSet(ResourceFactory.newClassPathResource("changeSet1.xml"));
//		engine.startEngine();
//		ProcessInstance process = engine.startProcess("changeSet1_process1");
//		assertNotNull("Process instance not created", process);
//		assertProcessInstanceCompleted(process.getId(), engine.getKnowledgeSession());
//	}

}