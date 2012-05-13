package lt.bumbis.rpsim.droolsjbpm;

import org.drools.common.InternalKnowledgeRuntime;
import org.drools.process.instance.impl.DefaultWorkItemManager;

public class WorkItemManager extends DefaultWorkItemManager {

	public WorkItemManager(InternalKnowledgeRuntime kruntime) {
		super(kruntime);
	}
}
