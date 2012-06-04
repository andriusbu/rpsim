package lt.bumbis.rpsim.demo;

import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Model;

public class SimpleDist extends ContDist {
	
		private double num;
        
		public SimpleDist(Model arg0, String arg1, boolean arg2, boolean arg3) {
                super(arg0, arg1, arg2, arg3);
                this.num = 1;
        }
		
		public SimpleDist(Model model, String name, double num, boolean showInReport, boolean showInTrace) {
			super(model, name, showInReport, showInTrace);
			this.num = num;
		}
			
        @Override
        public Double sample() {
                return this.num;
        }
}