package lt.bumbis.rpsim.core.simconfig;

import desmoj.core.dist.ContDist;

public class Distribution extends ConfigElement<Distribution> {
	
	private Class<? extends ContDist> distClass;
	private Object[] distParams;
		
	//---------------------------------------------
	// Constructors
	//---------------------------------------------
	public Distribution() {
		super();
	}
	
	public Distribution(String name, Class<? extends ContDist> distClass, Object[] distParams, boolean showInReport, boolean showInTrace) {
		super(name, showInReport, showInTrace);
		this.distClass = distClass;
		this.distParams = distParams;
	}
	
	//---------------------------------------------
	// Configuration methods
	//---------------------------------------------
	public Distribution distClass(Class<? extends ContDist> distClass) {
		setDistClass(distClass);
		return this;
	}
	
	public Distribution distParams(double p1) {
		Object[] params = new Object[] { p1 };
		setDistParams(params);
		return this;
	}
	
	public Distribution distParams(double p1, double p2) {
		Object[] params = new Object[] { p1, p2 };
		setDistParams(params);
		return this;
	}
	
	public Distribution distParams(double p1, double p2, double p3) {
		Object[] params = new Object[] { p1, p2, p3 };
		setDistParams(params);
		return this;
	}

	//---------------------------------------------
	// Getter and Setters
	//---------------------------------------------
	public Class<? extends ContDist> getDistClass() {
		return distClass;
	}

	public void setDistClass(Class<? extends ContDist> distClass) {
		this.distClass = distClass;
	}

	public Object[] getDistParams() {
		return distParams;
	}

	public void setDistParams(Object[] distParams) {
		this.distParams = distParams;
	}
}