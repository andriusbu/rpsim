package lt.bumbis.rpsim.droolsjbpm;

import java.net.MalformedURLException;
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
	
	private List<String> modelURLs;
	private List<String> kpackageURLs;
	
	public ProcessEngineGuvnor() {
		super();
	}
	
	public ProcessEngineGuvnor(ISimEngine simEngine) {
		super(simEngine);
	}
	
	public void addModelURL(String url) {
		modelURLs.add(url);
	}

	public void addPackageURL(String url) {
		kpackageURLs.add(url);
	}
	
	@Override
	protected void init() {
		modelURLs = new ArrayList<String>();
		kpackageURLs = new ArrayList<String>();
	}

	@Override
	protected KnowledgeBase newKnowledgeBase() {
		List<URL> urls; 
		urls = new ArrayList<URL>();
		for (Iterator<String> i = modelURLs.iterator(); i.hasNext(); ) {
			URL url;
			try {
				url = new URL(i.next());
				urls.add(url);
			} catch (MalformedURLException e) {
				//TODO implement exception handling
			}
		}
		URL[] urlArray = new URL[urls.size()];
		urls.toArray(urlArray);
		URLClassLoader customURLCLassLoader = new URLClassLoader(urlArray);
		
		KnowledgeBuilderConfiguration kbuilderConfig = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration(null, customURLCLassLoader);
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(kbuilderConfig);
		
		urls.clear();
		for (Iterator<String> i = kpackageURLs.iterator(); i.hasNext(); ) {
			kbuilder.add(ResourceFactory.newUrlResource(i.next()), ResourceType.PKG);
//			kbuilder.add(ResourceFactory.newClassPathResource(i.next(), customURLCLassLoader), ResourceType.PKG);
		}
		
		KnowledgeBaseConfiguration kbaseConfig = KnowledgeBaseFactory.newKnowledgeBaseConfiguration(null, customURLCLassLoader);
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kbaseConfig);
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}
}
