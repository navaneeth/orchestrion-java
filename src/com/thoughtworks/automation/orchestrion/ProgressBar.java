/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Represents a progress bar
 *
 */
public class ProgressBar extends UIItem {

	/**
	 * Initializes a new progress bar
	 */
	public ProgressBar(int refId) {
		super(refId);
	}
	
	/**
	 * Gets the maximum value for this progress bar
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getMaxValue() throws Exception {
		return RemoteServer.instance().executeAndGetId("getmaxvalue", getRefId());
	}
	
	/**
	 * Gets the minimum value for this progress bar
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getMinValue() throws Exception {
		return RemoteServer.instance().executeAndGetId("getminvalue", getRefId());
	}
	
	/**
	 * Gets the current value
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getValue() throws Exception {
		return RemoteServer.instance().executeAndGetId("getvalue", getRefId());
	}

}
