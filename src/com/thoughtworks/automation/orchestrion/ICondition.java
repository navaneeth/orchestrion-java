/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Provides an interface to wait conditions
 *
 */
public interface ICondition {
	
	/**
	 * Tests the condition
	 * 
	 * @return true if condition is satisfied, false otherwise
	 */
	public boolean test() throws Exception;
	
	/**
	 * Gets a user friendly name for this condition
	 * 
	 * @return
	 */
	public String getName();

}
