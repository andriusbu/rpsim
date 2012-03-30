package lt.bumbis.rpsim;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("f34dca7c-7a97-11e1-9a4b-028037ec0200")
public interface ProcessEngine {

    @objid ("689474dc-7a9a-11e1-9a4b-028037ec0200")
    ProcessEngine startEngine();

    @objid ("273975e4-7aa6-11e1-9a4b-028037ec0200")
    long startProcess(final String processName);

}
