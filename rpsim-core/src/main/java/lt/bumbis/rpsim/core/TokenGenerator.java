package lt.bumbis.rpsim.core;

import java.util.concurrent.TimeUnit;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import desmoj.core.dist.ContDist;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

@objid ("912678e0-7c49-11e1-8f00-028037ec0200")
public class TokenGenerator extends ExternalEvent {
    @objid ("12078077-7ce9-11e1-a49d-028037ec0200")
    private String processName;

    @objid ("12078078-7ce9-11e1-a49d-028037ec0200")
    private ContDist dist;

    @objid ("12078079-7ce9-11e1-a49d-028037ec0200")
    private TimeUnit timeUnit;


    @objid ("912678e1-7c49-11e1-8f00-028037ec0200")
    public TokenGenerator(final Model arg0, final String arg1, final boolean arg2) {
        super(arg0, arg1, arg2);
    }

    @Override
    @objid ("912678e9-7c49-11e1-8f00-028037ec0200")
    public void eventRoutine() {
        SimulationEngine simEngine = (SimulationEngine)getModel();
        simEngine.syncTime();
        simEngine.getProcessEngine().startProcess(processName);
        schedule(new TimeSpan(dist.sample(), timeUnit));
    }

    @objid ("1207807a-7ce9-11e1-a49d-028037ec0200")
    public TokenGenerator setProcessName(final String processName) {
        this.processName = processName;
        return this;
    }

    @objid ("1207807f-7ce9-11e1-a49d-028037ec0200")
    public TokenGenerator setDistribution(final ContDist dist) {
        this.dist = dist;
        return this;
    }

    @objid ("12078084-7ce9-11e1-a49d-028037ec0200")
    public TokenGenerator setTimeUnit(final TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    @objid ("12078089-7ce9-11e1-a49d-028037ec0200")
    public TokenGenerator settings(final String processName, final ContDist dist, final TimeUnit timeUnit) {
        setProcessName(processName);
        setDistribution(dist);
        setTimeUnit(timeUnit);
        return this;
    }

}
