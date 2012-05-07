package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;

public class TestProcessEngine implements IProcessEngine {
	
	private int procStartCount = 0;
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

	public int getProcStartCount() {
		return procStartCount;
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
}
