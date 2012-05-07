package lt.bumbis.rpsim.core;

public class TestHandler implements IServiceHandler {
	
	private int updateCalled;

	public void update() {
		updateCalled++;
	}
	
	public int getUpdateCalled() {
		return updateCalled;
	}

}
