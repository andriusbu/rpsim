package lt.bumbis.rpsim.exceptions;

import lt.bumbis.rpsim.core.simconfig.ConfigElement;

public class SimModelConfigurationException extends RuntimeException {

	private static final long serialVersionUID = -780079667695819588L;
	
	public SimModelConfigurationException(ConfigElement<?> configElement, String message, Throwable e) {
		super(
				configElement.getClass().getName()
				+ ":" + configElement.getName()
				+ ":" + message, e);
	}

}
