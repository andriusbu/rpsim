package lt.bumbis.rpsim.core;

public class TestProcessEngine implements IProcessEngine {
	
	private int procStartCount = 0;
	private String lastProcessName;

	public long startProcess(String processName) {
		procStartCount++;
		lastProcessName = processName;
		return 0;
	}

	public int getProcStartCount() {
		return procStartCount;
	}

	public String getLastProcessName() {
		return lastProcessName;
	}

}
