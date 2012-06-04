package lt.bumbis.rpsim.tests;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lt.bumbis.rpsim.core.IDataProvider;

public class TestDataProvider implements IDataProvider {
	
	private Class clazz;

	public void init() {
		System.out.println("asdfasdf");
		List<URL> loaderURLs = new ArrayList<URL>();
		try {
			loaderURLs.add(new URL("http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/lt.bumbis.rpsim2/LATEST"));
			loaderURLs.add(new URL("http://localhost:8080/drools-guvnor/org.drools.guvnor.Guvnor/package/lt.bumbis.rpsim2/LATEST/MODEL"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<URL> urls; 
		urls = new ArrayList<URL>();
		URL[] urlArray = new URL[loaderURLs.size()];
		loaderURLs.toArray(urlArray);
		URLClassLoader customURLCLassLoader = new URLClassLoader(urlArray);
		
		try {
			clazz = customURLCLassLoader.loadClass("lt.bumbis.rpsim2.TestObject1");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getData() {
		Map<String, Object> data = new HashMap<String, Object>();
		Object var;
		try {
			var = clazz.newInstance();
			Method method = clazz.getMethod("setStrField", new Class[] { String.class });
			method.invoke(var, new Object[] { "Andrius" });
			data.put("var1", var);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public void prpareData() {
		// TODO Auto-generated method stub
		
	}

	public Map<String, Object> getProcessData() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getContextData() {
		// TODO Auto-generated method stub
		return null;
	}

}
