package lt.bumbis.rpsim.models.sim3;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.droolsjbpm.ProcessEngine;
import lt.bumbis.rpsim.droolsjbpm.ProcessEngineImpl;
import lt.bumbis.rpsim.models.sim3.Configuration;

import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.TimeInstant;

public class ModelTest1 {
	
	final static Logger logger = LoggerFactory.getLogger(ProcessEngine.class);
	
	private static final String CHANGE_SET = "changeSet.xml";
	private static final String PROCESS_MODEL = "testProcess.bpmn";
	private static final String RULES_MODEL = "rule1.drl";

	@Test
	public void test1() {
		SimConfig conf = new Configuration();
		conf.configure();
		SimModel model = new SimModel(conf);

		ProcessEngineImpl procEngine = new ProcessEngineImpl(model);
		procEngine.addChangeSet(ResourceFactory.newClassPathResource(ModelTest1.CHANGE_SET));
		procEngine.setEnableRules(true);
		procEngine.enableLog();
		model.setProcessEngine(procEngine);
		
		Experiment exp = new Experiment("Simulation Experiment",true);
		model.connectToExperiment(exp);
		exp.setShowProgressBar(true);
	
		exp.stop(new TimeInstant(1, TimeUnit.DAYS));
		exp.start();
		exp.report();
		exp.finish();
	}
	
	@Test
	public void test2() {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(ModelTest1.PROCESS_MODEL), ResourceType.BPMN2);
		kbuilder.add(ResourceFactory.newClassPathResource(ModelTest1.RULES_MODEL), ResourceType.DRL);
		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors());
			assertTrue("Rules has errors", ! kbuilder.hasErrors());
		}
	}
}

