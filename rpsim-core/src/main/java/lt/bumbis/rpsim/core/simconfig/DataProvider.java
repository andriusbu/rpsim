package lt.bumbis.rpsim.core.simconfig;

import lt.bumbis.rpsim.core.IDataProvider;

public class DataProvider extends ConfigElement<DataProvider> {
	
	private Class<? extends IDataProvider> provider;
	
	//---------------------------------------------
	// Constructors
	//---------------------------------------------
	public DataProvider() {
		super();
	}
	
	//---------------------------------------------
	// Configuration methods
	//---------------------------------------------	
	public DataProvider providerClass(Class<? extends IDataProvider> clazz) {
		setProvider(clazz);
		return this;
	}
	
	//---------------------------------------------
	// Getter and Setters
	//---------------------------------------------
	public Class<? extends IDataProvider> getProvider() {
		return provider;
	}

	public void setProvider(Class<? extends IDataProvider> provider) {
		this.provider = provider;
	}
}