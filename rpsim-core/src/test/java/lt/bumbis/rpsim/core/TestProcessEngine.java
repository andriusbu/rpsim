package lt.bumbis.rpsim.core;

import java.util.Observable;
import java.util.Observer;

public class TestProcessEngine implements IProcessEngine, Observer {
	
	private int procStartCount = 0;
	private String lastProcessName;
	private long timeUpdateCount = 0;
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

	public void update(Observable o, Object arg) {
		timeUpdateCount++;
	}
	
	public long getTimeUpdateCount() {
		return timeUpdateCount;
	}
}
