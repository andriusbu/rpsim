package lt.bumbis.rpsim.core.simconfig;

import desmoj.core.dist.ContDist;

public class Distribution extends ConfigElement {
	
	private Class<? extends ContDist> distClass;
	private Object[] distParams;
	
	public Distribution(String name, Class<? extends ContDist> distClass, Object[] distParams, boolean showInReport, boolean showInTrace) {
		super(name, showInReport, showInTrace);
		this.distClass = distClass;
		this.distParams = distParams;
	}
	
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
