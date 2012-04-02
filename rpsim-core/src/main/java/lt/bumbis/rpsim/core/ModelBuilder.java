package lt.bumbis.rpsim.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("11fdf70a-7ce9-11e1-a49d-028037ec0200")
public abstract class ModelBuilder {
    @SuppressWarnings("unused")
    @objid ("12005964-7ce9-11e1-a49d-028037ec0200")
    private SimulationEngine simEngine;


    @objid ("12005966-7ce9-11e1-a49d-028037ec0200")
    public ModelBuilder(final SimulationEngine simEngine) {
        this.simEngine = simEngine;
    }

    @objid ("1202bbc0-7ce9-11e1-a49d-028037ec0200")
    public abstract void init();

    @objid ("1202bbc1-7ce9-11e1-a49d-028037ec0200")
    public abstract void doInitialSchedules();

}
