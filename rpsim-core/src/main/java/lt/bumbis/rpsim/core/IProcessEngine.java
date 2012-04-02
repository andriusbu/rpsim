package lt.bumbis.rpsim.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("911f51d6-7c49-11e1-8f00-028037ec0200")
public interface IProcessEngine {

    @objid ("911f51d7-7c49-11e1-8f00-028037ec0200")
    ProcessEngine startEngine();

    @objid ("911f51d9-7c49-11e1-8f00-028037ec0200")
    long startProcess(final String processName);
}
