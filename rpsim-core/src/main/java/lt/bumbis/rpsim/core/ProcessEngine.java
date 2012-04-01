package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("f34dca7c-7a97-11e1-9a4b-028037ec0200")
public interface ProcessEngine {

    @objid ("689474dc-7a9a-11e1-9a4b-028037ec0200")
    ProcessEngine startEngine();

    @objid ("273975e4-7aa6-11e1-9a4b-028037ec0200")
    long startProcess(final String processName);

    @objid ("deb1cff7-7bf9-11e1-8f30-028037ec0200")
    void setAdvanceTime(final long amount, final TimeUnit unit);

    /**
     * Sets process engine virtual time to <code>time</code>
     * @param time	engine time in TimeUnit.MILLISECONDS
     */
    @objid ("38bcbb04-7c00-11e1-8f30-028037ec0200")
    void setTime(final long amount);

}
