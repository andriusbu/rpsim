package lt.bumbis.rpsim;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

@objid ("961eae68-7a86-11e1-9a4b-028037ec0200")
public class SimulationModel extends Model {

    @objid ("9aa664c6-7a88-11e1-9a4b-028037ec0200")
    public SimulationModel(final Model arg0, final String arg1, final boolean arg2, final boolean arg3) {
        super(arg0, arg1, arg2, arg3);
        // TODO Auto-generated constructor stub
    }

    @Override
    @objid ("9aa664ce-7a88-11e1-9a4b-028037ec0200")
    public String description() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @objid ("9aa664d3-7a88-11e1-9a4b-028037ec0200")
    public void doInitialSchedules() {
        TokenGenerator tokenGenerator = new TokenGenerator(this, "Token Generator", true);
        tokenGenerator.schedule(new TimeSpan(0));
    }

    @Override
    @objid ("9aa664d6-7a88-11e1-9a4b-028037ec0200")
    public void init() {
        // TODO Auto-generated method stub
    }

    @objid ("6ca96526-7a8d-11e1-9a4b-028037ec0200")
    public void startProcess() {
    }

}
