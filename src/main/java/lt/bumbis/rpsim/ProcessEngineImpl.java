package lt.bumbis.rpsim;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.drools.KnowledgeBase;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.io.Resource;
import org.drools.runtime.StatefulKnowledgeSession;

@objid ("a281fb2e-7a95-11e1-9a4b-028037ec0200")
public class ProcessEngineImpl implements ProcessEngine {
    @objid ("07aa1ec0-7a99-11e1-9a4b-028037ec0200")
    private static String resAgentName = "ProcessEngineAgent";

    @objid ("a815b86a-7a99-11e1-9a4b-028037ec0200")
    private KnowledgeAgent kagent;

    @objid ("a815b86b-7a99-11e1-9a4b-028037ec0200")
    private StatefulKnowledgeSession ksession;


    @objid ("07aa1ec7-7a99-11e1-9a4b-028037ec0200")
    public ProcessEngineImpl() {
        init();
    }

    @objid ("6a203b38-7a9a-11e1-9a4b-028037ec0200")
    public void startEngine() {
    }

    @objid ("fe18ac9a-7a9b-11e1-9a4b-028037ec0200")
    public void addChangeSet(final Resource res) {
        kagent.applyChangeSet(res);
    }

    @objid ("fe18ac9e-7a9b-11e1-9a4b-028037ec0200")
    private void init() {
        kagent = KnowledgeAgentFactory.newKnowledgeAgent( resAgentName );
    }

    @objid ("d194c3e1-7a9c-11e1-9a4b-028037ec0200")
    public KnowledgeBase getKnowledgeBase() {
        return kagent.getKnowledgeBase();
    }

}
