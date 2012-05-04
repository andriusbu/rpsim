package lt.bumbis.rpsim.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import lt.bumbis.rpsim.core.entities.SvcProcessor;
import lt.bumbis.rpsim.core.entities.SvcProcessorExec;
import lt.bumbis.rpsim.core.entities.SvcReq;
import lt.bumbis.rpsim.core.events.NewProcessToken;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.ServiceProcessor;
import lt.bumbis.rpsim.core.simconfig.SimConfig;
import lt.bumbis.rpsim.core.simconfig.TokenGenerator;

public class ModelBuilder {

	public static void init(SimModel model, SimConfig config) {
		for (Distribution dist : config.getDists().values()) createDist(model, dist);
		for (ServiceProcessor svcProc: config.getSvcProcs().values()) createSvcProcessor(model, svcProc);
		for (TokenGenerator tokenGen: config.getTokenGens().values()) createTokenGenerator(model, tokenGen);
	}
	
	public static void doInitialSchedules(SimModel model, SimConfig config) {
		//TODO
	}
	
	@SuppressWarnings("rawtypes")
	private static void createDist(SimModel model, Distribution dist) {
		Class[] types = new Class [4+dist.getDistParams().length];  
		Object[] args = new Object[4+dist.getDistParams().length];
		types[0] = Model.class;
		args[0] = model;
		types[1] = String.class;
		args[1] = dist.getName();
		int i;
		for (i=0; i<dist.getDistParams().length; i++) {
			types[i+2] = double.class;
			args[i+2] = dist.getDistParams()[i];
		}
		i=i+2;
		types[i]=boolean.class;
		args[i++]=dist.isShowInReport();
		types[i]=boolean.class;
		args[i]=dist.isShowInTrace();		
		Constructor cons;
		ContDist contDist = null;
		try {
			cons = dist.getDistClass().getConstructor(types);
			contDist = (ContDist)cons.newInstance(args);
			model.addDist(dist.getName(), contDist);
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void createSvcProcessor(SimModel model, ServiceProcessor cfg) {
		SvcProcessor svcProc = new SvcProcessor(model, cfg.getName(), cfg.isShowInReport());
		svcProc.setWaitQueue(new Queue<SvcReq>(model, cfg.getName()+"_WQ", cfg.isShowInReport(), cfg.isShowInTrace()));
		svcProc.setIdleQueue(new Queue<SvcProcessorExec>(model, cfg.getName()+"_IQ", cfg.isShowInReport(), cfg.isShowInTrace()));
		for (int i=0; i<cfg.getNumExec(); i++) {
			SvcProcessorExec svcReqExec = new SvcProcessorExec(model, cfg.getName()+"_Exec"+i, cfg.isShowInTrace());
			svcProc.getIdleQueue().insert(svcReqExec);
		}
		svcProc.setServiceTimeDist(model.getDist(cfg.getDistName()));
		svcProc.setServiceTimeUnit(cfg.getTimeUnit());
	}
	
	private static void createTokenGenerator(SimModel model, TokenGenerator cfg) {
		NewProcessToken tokenGen = new NewProcessToken(model, cfg.getName(), cfg.isShowInReport());
		tokenGen.setProcessName(cfg.getProcessName());
		tokenGen.setDist(model.getDist(cfg.getDistName()));
		tokenGen.setTimeUnit(cfg.getTimeUnit());
	}
	
}
