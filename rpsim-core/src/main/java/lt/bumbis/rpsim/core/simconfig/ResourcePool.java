package lt.bumbis.rpsim.core.simconfig;

import java.util.concurrent.TimeUnit;

public class ResourcePool extends ConfigElement {
	
	private long resourceCount;
	private double resourcePrice;
	private TimeUnit resourceTimeUnit;

	public ResourcePool(String name, long count, double price, TimeUnit timeUnit, boolean showInReport, boolean showInTrace) {
		super(name, showInReport, showInTrace);
		this.resourceCount = count;
		this.resourcePrice = price;
		this.resourceTimeUnit = timeUnit;
	}

	public long getResourceCount() {
		return resourceCount;
	}

	public double getResourcePrice() {
		return resourcePrice;
	}

	public TimeUnit getResourceTimeUnit() {
		return resourceTimeUnit;
	}
}
