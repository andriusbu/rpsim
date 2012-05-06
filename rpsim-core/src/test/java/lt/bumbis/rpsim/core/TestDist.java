package lt.bumbis.rpsim.core;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Model;

public class TestDist extends ContDist {
	
		private double num;
        
		public TestDist(Model arg0, String arg1, boolean arg2, boolean arg3) {
                super(arg0, arg1, arg2, arg3);
                this.num = 33.0;
        }
		
		public TestDist(Model model, String name, double num, boolean showInReport, boolean showInTrace) {
			super(model, name, showInReport, showInTrace);
			this.num = num;
		}
			
        @Override
        public Double sample() {
                return this.num;
        }
}