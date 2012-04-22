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
	private Object[] distParams;
	private TimeUnit serviceTimeUnit;
	private boolean showInReport; 
	private boolean showInTrace;
	
	private Queue<SvcReq> waitQueue;
	private Queue<SvcReqExec> idleQueue;
	private ContDist serviceTimeDist;
	
	/**
	 * Constructor
	 * @param name				- ServiceProcessor name in model
	 * @param numReqExec		- Number of service request executors
	 * @param distClass			- Service time distribution class (subclass of DistClass)
	 * @param distParams		- Parameters for distribution
	 * @param serviceTimeUnit	- TimeUnit for service time
	 * @param showInReport		- Show in report
	 * @param showInTrace		- Show in trace
	 */
	public ServiceProcessor(String name, int numReqExec, Class distClass,
			Object[] distParams, TimeUnit serviceTimeUnit, boolean showInReport, boolean showInTrace) {
		super();
		this.name = name;
		this.numReqExec = numReqExec;
		this.distClass = distClass;
		this.distParams = distParams;
		this.serviceTimeUnit = serviceTimeUnit;
		this.showInReport = showInReport;
		this.showInTrace = showInTrace;
	}
	
	/**
	 * Instantiate all static model components like queues and distributions
	 * @param model - model which is owner of the service processor
	 */
	public void init(SimModel model) {
		waitQueue = new Queue<SvcReq>(model, name, showInReport, showInTrace); 
		idleQueue = new Queue<SvcReqExec>(model, name, showInReport, showInTrace);
		for (int i=0; i<numReqExec; i++) {
			SvcReqExec svcReqExec = new SvcReqExec(model, name+"_exec_"+i, showInTrace);
			idleQueue.insert(svcReqExec);
		}
		serviceTimeDist = getDist(model, name+"_dist", distClass, distParams, showInReport, showInTrace);
	}
	
	private static ContDist getDist(SimModel model, String name, Class distClass, Object[] distParams,
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

	public Queue<SvcReq> getWaitQueue() {
		return waitQueue;
	}

	public Queue<SvcReqExec> getIdleQueue() {
		return idleQueue;
	}

	public ContDist getServiceTimeDist() {
		return serviceTimeDist;
	}

	public TimeUnit getServiceTimeUnit() {
		return serviceTimeUnit;
	}
}
