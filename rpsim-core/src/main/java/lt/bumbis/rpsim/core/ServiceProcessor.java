package lt.bumbis.rpsim.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.entities.SvcReq;
import lt.bumbis.rpsim.core.entities.SvcReqExec;
import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.Queue;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ServiceProcessor {
	
	private String name;
	private int numReqExec;
	private Class distClass;
	private TimeUnit serviceTimeUnit;
	private boolean showInTrace;
	
	private Queue<SvcReq> waitQueue;
	private Queue<SvcReqExec> idleQueue;
	private ContDist serviceTimeDist;
	
	
	public ServiceProcessor() {
		
	}
	
	/**
	 * Constructor
	 * @param name				- ServiceProcessor name in model
	 * @param numReqExec		- Number of service request executors
	 * @param distClass			- Service time distribution class (subclass of DistClass)
	 * @param serviceTimeUnit	- TimeUnit for service time
	 * @param showInTrace		- Show in trace
	 */
	public ServiceProcessor(String name, int numReqExec, Class distClass,
			TimeUnit serviceTimeUnit, boolean showInTrace) {
		super();
		this.name = name;
		this.numReqExec = numReqExec;
		this.distClass = distClass;
		this.serviceTimeUnit = serviceTimeUnit;
		this.showInTrace = showInTrace;
	}
	
	/**
	 * Instantiate all static model components like queues and distributions
	 * @param model - model which is owner of the service processor
	 */
	public void init(SimModel model) {
		waitQueue = new Queue<SvcReq>(model, name, false, false); //TODO use variable 
		idleQueue = new Queue<SvcReqExec>(model, name, false, false); //TODO use variable
		for (int i=0; i<numReqExec; i++) {
			SvcReqExec svcReqExec = new SvcReqExec(model, name+"_exec_"+i, showInTrace);
			idleQueue.insert(svcReqExec);
		}
		//TODO instantiate Distribution
		serviceTimeDist = getDist(model, distClass, name+"_dist");
	}
	
	private static ContDist getDist(SimModel model, Class distClass, String name) {
		Class[] types = new Class[] {Model.class, String.class, Boolean.class, Boolean.class};
		Object[] args = new Object[] {model, name, false, false}; //TODO use variable
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
}
