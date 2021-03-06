package lt.bumbis.rpsim.core.simconfig;

import java.util.concurrent.TimeUnit;

public class TimerEvent extends ConfigElement<TimerEvent> {
	
	private long time;
	private TimeUnit timeUnit;

	//---------------------------------------------
	// Constructors
	//---------------------------------------------
	public TimerEvent() {
		super();
	}
	
	public TimerEvent(String name, long time, TimeUnit timeUnit, boolean showInReport, boolean showInTrace) {
		super(name, showInReport, showInTrace);
		this.time = time;
		this.timeUnit = timeUnit;
	}
	
	//---------------------------------------------
	// Configuration methods
	//---------------------------------------------
	public TimerEvent time(long time) {
		setTime(time);
		return this;
	}
	
	public TimerEvent timeUnit(TimeUnit timeUnit) {
		setTimeUnit(timeUnit);
		return this;
	}
	
	//---------------------------------------------
	// Getter and Setters
	//---------------------------------------------
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
