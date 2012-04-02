package lt.bumbis.rpsim.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

@objid ("1202bbc2-7ce9-11e1-a49d-028037ec0200")
public class ServiceRequest extends Entity {
    @objid ("1202bbc3-7ce9-11e1-a49d-028037ec0200")
    private long processInstanceId;

    @objid ("1202bbc4-7ce9-11e1-a49d-028037ec0200")
    private String activityId;


    @objid ("1202bbc5-7ce9-11e1-a49d-028037ec0200")
    public ServiceRequest(final Model arg0, final String arg1, final boolean arg2) {
        super(arg0, arg1, arg2);
    }

    @objid ("1202bbca-7ce9-11e1-a49d-028037ec0200")
    public long getProcessInstanceId() {
        return processInstanceId;
    }

    @objid ("12051e1a-7ce9-11e1-a49d-028037ec0200")
    public ServiceRequest setProcessInstanceId(final long processInstanceId) {
        this.processInstanceId = processInstanceId;
        return this;
    }

    @objid ("12051e1f-7ce9-11e1-a49d-028037ec0200")
    public String getActivityId() {
        return activityId;
    }

    @objid ("12051e23-7ce9-11e1-a49d-028037ec0200")
    public ServiceRequest setActivityId(final String activityId) {
        this.activityId = activityId;
        return this;
    }

}
