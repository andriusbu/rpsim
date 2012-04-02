package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

@objid ("961eae68-7a86-11e1-9a4b-028037ec0200")
public class SimulationEngine extends Model {
    @objid ("45381754-7a96-11e1-9a4b-028037ec0200")
    private ProcessEngine processEngine;

    @objid ("9aa664c6-7a88-11e1-9a4b-028037ec0200")
    public SimulationEngine(final Model arg0, final String arg1, final boolean arg2, final boolean arg3) {
        super(arg0, arg1, arg2, arg3);
        // TODO Auto-generated constructor stub
    }

    @Override
    @objid ("9aa664ce-7a88-11e1-9a4b-028037ec0200")
    public String description() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @objid ("9aa664d3-7a88-11e1-9a4b-028037ec0200")
    public void doInitialSchedules() {
        TokenGenerator tokenGenerator = new TokenGenerator(this, "Token Generator", true);
        tokenGenerator.schedule(new TimeSpan(0));
    }

    @Override
    @objid ("9aa664d6-7a88-11e1-9a4b-028037ec0200")
    public void init() {
        processEngine.startEngine();
    }

    @objid ("6ca96526-7a8d-11e1-9a4b-028037ec0200")
    public long startProcess(String startProcessName) {
    	syncTime();
        return processEngine.startProcess(startProcessName);
    }

    @objid ("78b6c0de-7a97-11e1-9a4b-028037ec0200")
    public ProcessEngine getProcessEngine() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.processEngine;
    }

    @objid ("78b6c0e3-7a97-11e1-9a4b-028037ec0200")
    public void setProcessEngine(final ProcessEngine value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.processEngine = value;
    }

    @objid ("1a838c72-7bff-11e1-8f30-028037ec0200")
    protected void syncTime() {
        processEngine.setTime((long)presentTime().getTimeAsDouble(TimeUnit.MILLISECONDS));
    }

}
