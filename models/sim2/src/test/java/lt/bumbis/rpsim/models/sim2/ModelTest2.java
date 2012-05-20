package lt.bumbis.rpsim.models.sim2;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.droolsjbpm.ProcessEngineGuvnor;

import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class ModelTest2 {

	@Test
	public void test2() {
		SimConfig conf = new Configuration();
		conf.configure();
		SimModel model = new SimModel(conf);

		ProcessEngineGuvnor procEngine = new ProcessEngineGuvnor(model);
		try {
			procEngine.addPackage(new URL("http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/lt.bumbis.rpsim.models.sim2/LATEST"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		model.setProcessEngine(procEngine);
		
		Experiment exp = new Experiment("Simulation Experiment",true);
		model.connectToExperiment(exp);
		exp.setShowProgressBar(true);
	
		exp.stop(new TimeInstant(30, TimeUnit.DAYS));
		exp.start();
		exp.report();
		exp.finish();
	}
}
