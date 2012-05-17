package lt.bumbis.rpsim.core.events;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.IDataProvider;
import lt.bumbis.rpsim.core.SimModel;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;
import desmoj.core.statistic.Count;

public class NewProcessToken extends ExternalEvent {
	
	private String processName;
	private ContDist dist;
	private TimeUnit timeUnit;
	private IDataProvider dataProvider;
	
	private SimModel model;
	private Count tokenCount;
	
	public NewProcessToken(Model model, String name, boolean showInReport) {
		super(model, name, showInReport);
		this.model = (SimModel) model;
		tokenCount = new Count(model, "TokeCount_" + name, showInReport, showInReport);
	}

	@Override
	public void eventRoutine() {
		tokenCount.update();
		if ( dataProvider == null ) {
			model.getProcessEngine().startProcess(processName);
		} else {
			model.getProcessEngine().startProcess(processName, dataProvider.getData());
		}
		schedule(new TimeSpan(dist.sample(), timeUnit));
	}
	
	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public ContDist getDist() {
		return dist;
	}

	public void setDist(ContDist dist) {
		this.dist = dist;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}

	public Count getTokenCount() {
		return tokenCount;
	}

	public IDataProvider getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(IDataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}
}