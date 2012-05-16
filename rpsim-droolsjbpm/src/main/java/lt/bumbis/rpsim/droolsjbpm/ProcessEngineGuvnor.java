package lt.bumbis.rpsim.droolsjbpm;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lt.bumbis.rpsim.core.ISimEngine;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

public class ProcessEngineGuvnor extends ProcessEngine {
	
	private List<URL> loaderURLs;
	private List<String> kpackageURLs;
	
	public ProcessEngineGuvnor() {
		super();
	}
	
	public ProcessEngineGuvnor(ISimEngine simEngine) {
		super(simEngine);
	}
	
	public void addLoaderURLs(URL url) {
		this.loaderURLs.add(url);
	}

	public void addPackage(String url) {
		kpackageURLs.add(url);
	}
	
	@Override
	protected void init() {
		loaderURLs = new ArrayList<URL>();
		kpackageURLs = new ArrayList<String>();
	}

	@Override
	protected KnowledgeBase newKnowledgeBase() {
		List<URL> urls; 
		urls = new ArrayList<URL>();
		URL[] urlArray = new URL[loaderURLs.size()];
		loaderURLs.toArray(urlArray);
		URLClassLoader customURLCLassLoader = new URLClassLoader(urlArray);
		
		KnowledgeBuilderConfiguration kbuilderConfig = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration(null, customURLCLassLoader);
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(kbuilderConfig);
		
		urls.clear();
		for (Iterator<String> i = kpackageURLs.iterator(); i.hasNext(); ) {
			kbuilder.add(ResourceFactory.newClassPathResource(i.next(), customURLCLassLoader), ResourceType.PKG);
		}
		
		KnowledgeBaseConfiguration kbaseConfig = KnowledgeBaseFactory.newKnowledgeBaseConfiguration(null, customURLCLassLoader);
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kbaseConfig);
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}
}