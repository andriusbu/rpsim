package lt.bumbis.rpsim.core.elements;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import lt.bumbis.rpsim.core.SimModel;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Model;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Distribution {
	private String name;
	private Class distClass;
	private Object[] distParams;
	private boolean showInReport;
	private boolean showInTrace;
	
	private ContDist dist;

	public Distribution(String name, Class distClass, Object[] distParams,
			boolean showInReport, boolean showInTrace) {
		super();
		this.name = name;
		this.distClass = distClass;
		this.distParams = distParams;
		this.showInReport = showInReport;
		this.showInTrace = showInTrace;
	}
	
	public void init(SimModel model) {
		dist = getDist(model, name+"_dist", distClass, distParams, showInReport, showInTrace);
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
	
	public ContDist getDist() {
		return dist;
	}
}
