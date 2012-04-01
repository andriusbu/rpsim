package lt.bumbis.rpsim;

import java.util.concurrent.TimeUnit;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.io.Resource;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.runtime.process.ProcessInstance;
import org.drools.time.impl.PseudoClockScheduler;

@objid ("a281fb2e-7a95-11e1-9a4b-028037ec0200")
public class ProcessEngineImpl implements ProcessEngine {
    @objid ("07aa1ec0-7a99-11e1-9a4b-028037ec0200")
    private static String resAgentName = "ProcessEngineAgent";

    @objid ("a815b86a-7a99-11e1-9a4b-028037ec0200")
    private KnowledgeAgent kagent;

    @objid ("a815b86b-7a99-11e1-9a4b-028037ec0200")
    private StatefulKnowledgeSession ksession;

    @objid ("c4dace48-7c00-11e1-8f30-028037ec0200")
    private PseudoClockScheduler clock;


    @objid ("07aa1ec7-7a99-11e1-9a4b-028037ec0200")
    public ProcessEngineImpl() {
        init();
    }

    @objid ("6a203b38-7a9a-11e1-9a4b-028037ec0200")
    public ProcessEngine startEngine() {
        KnowledgeBase kbase = kagent.getKnowledgeBase();
        KnowledgeSessionConfiguration conf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
        conf.setOption( ClockTypeOption.get("pseudo"));
        ksession = kbase.newStatefulKnowledgeSession(conf, null);
        clock = ksession.getSessionClock();
        return this;
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

    @objid ("0cb3303f-7aa1-11e1-9a4b-028037ec0200")
    public StatefulKnowledgeSession getKnowledgeSession() {
        return this.ksession;
    }

    @objid ("2900d70a-7aa6-11e1-9a4b-028037ec0200")
    public long startProcess(final String processName) {
        ProcessInstance process = ksession.startProcess(processName);
        if (process == null)
            return 0;
            else return process.getId();
    }

    @objid ("e1607536-7bf9-11e1-8f30-028037ec0200")
    public void setAdvanceTime(final long amount, final TimeUnit unit) {
        clock.advanceTime(amount, unit);
    }

    @objid ("3989c540-7c00-11e1-8f30-028037ec0200")
    public void setTime(final long time) {
        long currentTime = clock.getCurrentTime();
        long advanceTime = time - currentTime;
        if (advanceTime > 0) clock.advanceTime(advanceTime, TimeUnit.MILLISECONDS);
    }

}
