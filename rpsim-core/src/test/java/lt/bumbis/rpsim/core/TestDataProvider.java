package lt.bumbis.rpsim.core;

public class TestDataProvider implements IDataProvider {
	
	private boolean initCalled = false;

	public void init() {
		initCalled = true;
		
	}

	public Object getData() {
		return null;
	}
	
	public boolean isInitCalled() {
		return initCalled;
	}

}
