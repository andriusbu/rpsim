package lt.bumbis.rpsim.demo;

import static org.junit.Assert.*;

import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.junit.Test;

public class DroolsConfigTest {
	
	private static final String CHANGE_SET = "changeSet.xml";
	
	@Test
	public void test() {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(CHANGE_SET), ResourceType.CHANGE_SET);
		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors());
			assertTrue("Rules has errors", ! kbuilder.hasErrors());
		}
	}

}
