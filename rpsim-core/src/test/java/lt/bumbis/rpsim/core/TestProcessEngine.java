package lt.bumbis.rpsim.core;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestProcessEngine implements IProcessEngine {
	
	private int procStartCount = 0;
	private int procWithDataStartCount = 0;
	private int procWithContextDataStartCount = 0;
	private String lastProcessName;
	private long timeUpdateCount = 0;
	private boolean engineStarted = false;
	private ISimEngine simEngine;
	private TestHandler handler = new TestHandler();
	private String activityName = "";
	
	public TestProcessEngine(ISimEngine simEngine, String activityName) {
		this.simEngine = simEngine;
		this.activityName = activityName;
	}
	
	public TestProcessEngine() {
	}

	public long startProcess(String processName) {
		procStartCount++;
		lastProcessName = processName;
		if ( simEngine != null) {
			simEngine.newServiceRequest(activityName, handler);
		}
		return 0;
	}
	
	public long startProcess(String processName, Map<String, Object> data) {
		procWithDataStartCount++;
		return startProcess(processName);
	}
	
	public long startProcess(String processName,
			Map<String, Object> processData, Map<String, Object> contextData) {
		procWithContextDataStartCount++;
		return startProcess(processName);
	}

	public int getProcStartCount() {
		return procStartCount;
	}
	
	public int getProcWithDataStartCount() {
		return procWithDataStartCount;
	}

	public String getLastProcessName() {
		return lastProcessName;
	}
	
	public int getCompletedActivities() {
		return handler.getUpdateCalled();
	}

	public long getTimeUpdateCount() {
		return timeUpdateCount;
	}
	
	public int getProcWithContextDataStartCount() {
		return procWithContextDataStartCount;
	}
	
	public void syncTime(long time, TimeUnit timeUnit) {
		timeUpdateCount++;		
	}

	public IProcessEngine startEngine() {
		this.engineStarted = true;
		return this;
	}
	
	public boolean isEngineStarted() {
		return engineStarted;
	}

	public void stopEngine() {
		this.engineStarted = false;
		
	}

	public void addContextData(Object obj) {
		// TODO Auto-generated method stub	
	}
}
