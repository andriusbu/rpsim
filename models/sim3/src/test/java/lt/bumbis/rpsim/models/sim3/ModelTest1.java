package lt.bumbis.rpsim.models.sim3;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.droolsjbpm.ProcessEngineGuvnor;
import lt.bumbis.rpsim.models.sim3.Configuration;

import org.junit.Test;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class ModelTest1 {

	@Test
	public void test2() {
		SimConfig conf = new Configuration();
		conf.configure();
		SimModel model = new SimModel(conf);

		ProcessEngineGuvnor procEngine = new ProcessEngineGuvnor(model);
		try {
			procEngine.addPackage(new URL("http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/lt.bumbis.rpsim.models.sim3/LATEST"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
				
		model.setProcessEngine(procEngine);
		Borrower borrower = new Borrower();
		borrower.setAge(16);
		borrower.setFirstName("Andrius");
		borrower.setLasName("Bumblauskas");
		
		Experiment exp = new Experiment("Simulation Experiment",true);
		model.connectToExperiment(exp);
		exp.setShowProgressBar(true);
	
		exp.stop(new TimeInstant(1, TimeUnit.DAYS));
		exp.start();
		exp.report();
		exp.finish();
	}
}
