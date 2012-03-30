package lt.bumbis.rpsim;

import java.util.concurrent.TimeUnit;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

@objid ("b33ac24a-7a89-11e1-9a4b-028037ec0200")
public class TokenGenerator extends ExternalEvent {

    @objid ("7d7a3c92-7a8b-11e1-9a4b-028037ec0200")
    public TokenGenerator(final Model arg0, final String arg1, final boolean arg2) {
        super(arg0, arg1, arg2);
        // TODO Auto-generated constructor stub
    }

    @Override
    @objid ("7d7a3c97-7a8b-11e1-9a4b-028037ec0200")
    public void eventRoutine() {
        // TODO Auto-generated method stub
        SimulationModel model = (SimulationModel)getModel();
        model.startProcess();
        schedule(new TimeSpan(10, TimeUnit.MINUTES));
        // TODO Call Process Engine to start process instance
    }
}
