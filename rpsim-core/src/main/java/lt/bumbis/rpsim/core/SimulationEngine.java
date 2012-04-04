package lt.bumbis.rpsim.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import desmoj.core.simulator.Model;

@objid ("12051e3d-7ce9-11e1-a49d-028037ec0200")
public class SimulationEngine extends Model {
    @objid ("12051e3e-7ce9-11e1-a49d-028037ec0200")
    private ProcessEngine processEngine;

    @objid ("12051e3f-7ce9-11e1-a49d-028037ec0200")
    private ModelBuilder modelBuilder;

    private ModelComponentFactory componentFactory;

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
        if (modelBuilder != null) modelBuilder.doInitialSchedules();
        if (processEngine != null) {
        	getExperiment().getSimClock().addObserver(processEngine);
        }
    }

    @Override
    @objid ("12051e52-7ce9-11e1-a49d-028037ec0200")
    public void init() {
    	componentFactory = new ModelComponentFactory(this);
        if (processEngine != null) processEngine.startEngine();
        if (modelBuilder != null) modelBuilder.init();
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
    
    public ModelComponentFactory getComponentFactory() {
		return componentFactory;
	}

}
