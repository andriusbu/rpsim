package lt.bumbis.rpsim.core.simconfig;

public abstract class ConfigElement {
	
	private String name;
	
	public ConfigElement(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
