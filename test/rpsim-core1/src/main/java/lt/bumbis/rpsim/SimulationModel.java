package lt.bumbis.rpsim;

import desmoj.core.simulator.Model;

public class SimulationModel extends Model {
	
	private ProcessEngine processEngine;
	private String startProcessName;

	public SimulationModel(Model owner, String name, boolean showInReport, boolean showInTrace ) {
		super(owner, name, showInReport, showInTrace);
	}

	@Override
	public String description() {
		return "Rules based Process simulation model";
	}

	@Override
	public void doInitialSchedules() {
		
	}

	@Override
	public void init() {
		processEngine.startEngine();
	}
		
	public ProcessEntity startProcess() {
		return processEngine.startProcess(this, "Process Instance", false);
	}

	//Getters and Setters
	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}
	
	public void setStartProcess(String process) {
		startProcessName = process;
	}
	
	public String getStartProcess() {
		return this.startProcessName;
	}
	

}
