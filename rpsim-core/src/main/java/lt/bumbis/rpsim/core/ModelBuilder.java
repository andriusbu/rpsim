package lt.bumbis.rpsim.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;
import lt.bumbis.rpsim.core.entities.SvcProcessor;
import lt.bumbis.rpsim.core.entities.SvcProcessorExec;
import lt.bumbis.rpsim.core.entities.SvcReq;
import lt.bumbis.rpsim.core.simconfig.Distribution;
import lt.bumbis.rpsim.core.simconfig.ServiceProcessor;
import lt.bumbis.rpsim.core.simconfig.SimConfig;

public class ModelBuilder {

	public static void init(SimModel2 model, SimConfig config) {
		
		for (Distribution dist : ((HashMap<String, Distribution>)config.get(Distribution.class)).values())
				createDist(model, dist.getName(), dist.getDistClass(), dist.getDistParams(), dist.isShowInReport(), dist.isShowInTrace());
		
		for (ServiceProcessor svcProc: ((HashMap<String, ServiceProcessor>)config.get(ServiceProcessor.class)).values())
			createSvcProcessor(model, svcProc);
				
	}
	
	public static void doInitialSchedules(SimModel2 model, SimConfig config) {
		//TODO
	}
	
	private static ContDist createDist(SimModel2 model, String name, Class distClass, Object[] distParams,
			boolean showInReport, boolean showInTrace) {
		Class[] types = new Class [4+distParams.length];
		Object[] args = new Object[4+distParams.length];
		types[0] = Model.class;
		args[0] = model;
		types[1] = String.class;
		args[1] = name;
		int i;
		for (i=0; i<distParams.length; i++) {
			types[i+2] = double.class;
			args[i+2] = distParams[i];
		}
		i=i+2;
		types[i]=boolean.class;
		args[i++]=showInReport;
		types[i]=boolean.class;
		args[i]=showInTrace;		
		Constructor cons;
		ContDist dist = null;
		try {
			cons = distClass.getConstructor(types);
			dist = (ContDist)cons.newInstance(args);
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
		return dist;
	}
	
	private static void createSvcProcessor(SimModel model, ServiceProcessor cfg) {
		SvcProcessor svcProc = new SvcProcessor(model, cfg.getName(), cfg.isShowInReport());
		svcProc.setWaitQueue(new Queue<SvcReq>(model, cfg.getName()+"_WQ", cfg.isShowInReport(), cfg.isShowInTrace()));
		svcProc.setIdleQueue(new Queue<SvcProcessorExec>(model, cfg.getName()+"_IQ", cfg.isShowInReport(), cfg.isShowInTrace()));
		for (int i=0; i<cfg.getNumExec(); i++) {
			SvcProcessorExec svcReqExec = new SvcProcessorExec(model, cfg.getName()+"_Exec"+i, cfg.isShowInTrace());
			svcProc.getIdleQueue().insert(svcReqExec);
		}
		serviceTimeDist = model.getDist(distName);

	}
	
}
