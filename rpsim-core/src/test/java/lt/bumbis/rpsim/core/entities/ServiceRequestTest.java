package lt.bumbis.rpsim.core.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;

public class ServiceRequestTest {
	
	private class TestClass {
	}
	
	private class TestModel extends Model {
		public TestModel(Model arg0, String arg1, boolean arg2, boolean arg3) {
			super(arg0, arg1, arg2, arg3);
		}
		@Override public String description() { return null; }
		@Override public void doInitialSchedules() {	}
		@Override public void init() {}
	}
	
	private ServiceRequest serviceRequest;
	private TestModel model;
	private Experiment exp;
	
	@Before
	public void runBeforeClass() {
		model = new TestModel(null, "Test model", false, false);
		exp = new Experiment("Test Experiment", false);		
		model.connectToExperiment(exp);
		serviceRequest = new ServiceRequest(model, "Test SR", false);
	}

	@Test
	public void testSetExternalRef() {
		TestClass testClass = new TestClass();
		assertEquals(serviceRequest.setExternalRef(testClass).getExternalRef(), testClass);
	}

	@Test
	public void testSetWaitQueue() {
		Queue<ServiceRequest> queue = new Queue<ServiceRequest>(model, "test", false, false);
		assertEquals(serviceRequest.setWaitQueue(queue).getWaitQueue(), queue);
	}

	@Test
	public void testSetProcIdleQueue() {
		Queue<ServiceProcessor> queue = new Queue<ServiceProcessor>(model, "Test", false, false);
		assertEquals(serviceRequest.setProcIdleQueue(queue).getProcIdleQueue(), queue);
	}

	@Test
	public void testSettings() {
		Queue<ServiceRequest> queue1 = new Queue<ServiceRequest>(model, "test", false, false);
		Queue<ServiceProcessor> queue2 = new Queue<ServiceProcessor>(model, "Test", false, false);
		TestClass testClass = new TestClass();
		serviceRequest.settings(queue1, queue2, testClass);
		assertEquals(serviceRequest.getWaitQueue(), queue1);
		assertEquals(serviceRequest.getProcIdleQueue(), queue2);
		assertEquals(serviceRequest.getExternalRef(), testClass);
	}

}
