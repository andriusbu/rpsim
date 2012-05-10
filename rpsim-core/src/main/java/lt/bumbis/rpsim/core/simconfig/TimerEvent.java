package lt.bumbis.rpsim.core.simconfig;

import java.util.concurrent.TimeUnit;

public class TimerEvent extends ConfigElement {
	
	private long time;
	private TimeUnit timeUnit;

	public TimerEvent(String name, long time, TimeUnit timeUnit, boolean showInReport, boolean showInTrace) {
		super(name, showInReport, showInTrace);
		this.time = time;
		this.timeUnit = timeUnit;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}
}
