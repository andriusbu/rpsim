package lt.bumbis.rpsim.droolsjbpm;

import org.drools.common.InternalKnowledgeRuntime;
import org.drools.process.instance.WorkItemManager;
import org.drools.process.instance.impl.DefaultWorkItemManagerFactory;

public class CustomWorkItemManagerFactory extends DefaultWorkItemManagerFactory {

	public WorkItemManager createWorkItemManager(InternalKnowledgeRuntime kruntime) {
        return (WorkItemManager) new CustomWorkItemManager(kruntime);
    }
	
}
