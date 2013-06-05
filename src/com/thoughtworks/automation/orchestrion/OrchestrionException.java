/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * General exception thrown when something fails on Orchestrion
 *
 */
public class OrchestrionException extends Exception {
		
	private static final long serialVersionUID = -6142082344342680041L;

	public OrchestrionException(String message, Throwable cause) {
		super(message, cause);
	}

}
