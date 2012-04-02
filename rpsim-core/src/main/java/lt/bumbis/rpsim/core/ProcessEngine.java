package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("911f51d6-7c49-11e1-8f00-028037ec0200")
public interface ProcessEngine {

    @objid ("911f51d7-7c49-11e1-8f00-028037ec0200")
    ProcessEngine startEngine();

    @objid ("911f51d9-7c49-11e1-8f00-028037ec0200")
    long startProcess(final String processName);

    @objid ("9121b42e-7c49-11e1-8f00-028037ec0200")
    void setAdvanceTime(final long amount, final TimeUnit unit);

    /**
     * Sets process engine virtual time to <code>time</code>
     * @param time    engine time in TimeUnit.MILLISECONDS
     */
    @objid ("9121b433-7c49-11e1-8f00-028037ec0200")
    void setTime(final long amount);

}
