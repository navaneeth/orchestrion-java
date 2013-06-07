/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Exception thrown when some operation times out
 *
 */
public final class OperationTimedOutException extends Exception {


	private static final long serialVersionUID = -2631075079809730623L;


	/**
	 * @param operationName
	 */
	public OperationTimedOutException(String operationName, long milliseconds) {
		super(operationName + " timed out after waiting for " + milliseconds + "ms");
	}

}
