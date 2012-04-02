package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import desmoj.core.simulator.Model;

@objid ("12051e3d-7ce9-11e1-a49d-028037ec0200")
public class SimulationEngine extends Model {
    @objid ("12051e3e-7ce9-11e1-a49d-028037ec0200")
    private ProcessEngine processEngine;

    @objid ("12051e3f-7ce9-11e1-a49d-028037ec0200")
    private ModelBuilder modelBuilder;


    @objid ("12051e40-7ce9-11e1-a49d-028037ec0200")
    public SimulationEngine(final Model arg0, final String arg1, final boolean arg2, final boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    @Override
    @objid ("12051e4a-7ce9-11e1-a49d-028037ec0200")
    public String description() {
        return null;
    }

    @Override
    @objid ("12051e4f-7ce9-11e1-a49d-028037ec0200")
    public void doInitialSchedules() {
        modelBuilder.doInitialSchedules();
        getExperiment().getSimClock().addObserver(processEngine);
    }

    @Override
    @objid ("12051e52-7ce9-11e1-a49d-028037ec0200")
    public void init() {
        processEngine.startEngine();
        modelBuilder.init();
    }

    @objid ("12051e55-7ce9-11e1-a49d-028037ec0200")
    public long startProcess(final String startProcessName) {
        syncTime();
        return processEngine.startProcess(startProcessName);
    }

    @objid ("12051e5a-7ce9-11e1-a49d-028037ec0200")
    public ProcessEngine getProcessEngine() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.processEngine;
    }

    @objid ("12051e5e-7ce9-11e1-a49d-028037ec0200")
    public void setProcessEngine(final ProcessEngine value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.processEngine = value;
    }

    @objid ("12051e62-7ce9-11e1-a49d-028037ec0200")
    public ModelBuilder getModelBuilder() {
        return modelBuilder;
    }

    @objid ("12078072-7ce9-11e1-a49d-028037ec0200")
    public void setModelBuilder(final ModelBuilder modelBuilder) {
        this.modelBuilder = modelBuilder;
    }

    @objid ("12078075-7ce9-11e1-a49d-028037ec0200")
    protected void syncTime() {
        processEngine.setTime((long)presentTime().getTimeAsDouble(TimeUnit.MILLISECONDS));
    }

}
