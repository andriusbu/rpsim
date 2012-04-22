package lt.bumbis.rpsim.core;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Model;

public class TestDist extends ContDist {
	public TestDist(Model arg0, String arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	@Override
	public Double sample() {
		return 33.0;
	}

}
