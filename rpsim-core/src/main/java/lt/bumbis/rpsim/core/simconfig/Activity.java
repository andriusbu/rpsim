package lt.bumbis.rpsim.core.simconfig;

public class Activity extends ConfigElement{
	
	private String processor;
	
	public Activity(String name, String processor) {
		super(name, false, false);
		this.processor = processor;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}	

}
