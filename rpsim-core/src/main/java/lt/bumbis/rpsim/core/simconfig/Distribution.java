package lt.bumbis.rpsim.core.simconfig;

import desmoj.core.dist.ContDist;

public class Distribution extends ConfigElement {
	
	private Class<? extends ContDist> distClass;
	private Object[] distParams;
	private boolean showInReport;
	private boolean showInTrace;
	
	public Distribution(String name) {
		super(name);
	}

	public Distribution(String name, Class<? extends ContDist> distClass, Object[] distParams, boolean showInReport, boolean showInTrace) {
		super(name);
		settings(distClass, distParams, showInReport, showInTrace);
	}
	
	public Distribution settings(Class<? extends ContDist> distClass, Object[] distParams, boolean showInReport, boolean showInTrace) {
		this.distClass = distClass;
		this.distParams = distParams;
		this.showInReport = showInReport;
		this.showInTrace = showInTrace;
		return this;
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

	public boolean isShowInReport() {
		return showInReport;
	}

	public void setShowInReport(boolean showInReport) {
		this.showInReport = showInReport;
	}

	public boolean isShowInTrace() {
		return showInTrace;
	}

	public void setShowInTrace(boolean showInTrace) {
		this.showInTrace = showInTrace;
	}
}
