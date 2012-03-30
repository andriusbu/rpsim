package lt.bumbis.rpsim;

import desmoj.core.simulator.Model;

public interface ProcessEngine {
	
	public ProcessEngine startEngine();
	public ProcessEntity startProcess(Model owner, String name, boolean showInTrace);

}
