package lt.bumbis.rpsim.core.simconfig;

import java.util.concurrent.TimeUnit;

public class ResourcePool extends ConfigElement {
	
	private long resourceCount;
	private double resourcePrice;
	private TimeUnit resourceTimeUnit;

	//---------------------------------------------
	// Constructors
	//---------------------------------------------
	public ResourcePool() {
		super();
		this.resourceCount = Long.MAX_VALUE;
		this.resourcePrice = 0.0;
		this.resourceTimeUnit = TimeUnit.HOURS;
	}

	public ResourcePool(String name, long count, double price, TimeUnit timeUnit, boolean showInReport, boolean showInTrace) {
		super(name, showInReport, showInTrace);
		this.resourceCount = count;
		this.resourcePrice = price;
		this.resourceTimeUnit = timeUnit;
	}

	//---------------------------------------------
	// Configuration methods
	//---------------------------------------------
	@Override
	public ResourcePool name(String name) {
		setName(name);
		return this;
	}

	@Override
	public ResourcePool showInReport(boolean showInReport) {
		setShowInReport(showInReport);
		return this;
	}

	@Override
	public ResourcePool showInTrace(boolean showInTrace) {
		setShowInTrace(showInTrace);
		return null;
	}
	
	public ResourcePool count(long resourceCount) {
		setResourceCount(resourceCount);
		return this;
	}
	
	public ResourcePool price(double resourcePrice) {
		setResourcePrice(resourcePrice);
		return this;
	}
	
	public ResourcePool tiemUnit(TimeUnit timeUnit) {
		setResourceTimeUnit(timeUnit);
		return this;
	}
	
	//---------------------------------------------
	// Getter and Setters
	//---------------------------------------------
	public long getResourceCount() {
		return resourceCount;
	}

	public void setResourceCount(long resourceCount) {
		this.resourceCount = resourceCount;
	}

	public double getResourcePrice() {
		return resourcePrice;
	}

	public void setResourcePrice(double resourcePrice) {
		this.resourcePrice = resourcePrice;
	}

	public TimeUnit getResourceTimeUnit() {
		return resourceTimeUnit;
	}

	public void setResourceTimeUnit(TimeUnit resourceTimeUnit) {
		this.resourceTimeUnit = resourceTimeUnit;
	}
}