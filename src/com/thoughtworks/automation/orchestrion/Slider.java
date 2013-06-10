/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Represents a slider
 * 
 */
public class Slider extends Control {

	/**
	 * Initializes a new slider instance
	 * 
	 * @param refId
	 * @param windowId
	 */
	public Slider(int refId, int windowId) {
		super(refId, windowId);
	}

	/**
	 * Gets the maximum value for this slider
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getMaxValue() throws Exception {
		return RemoteServer.instance().executeAndGetId("getmaxvalue",
				getRefId());
	}

	/**
	 * Gets the minimum value for this slider
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getMinValue() throws Exception {
		return RemoteServer.instance().executeAndGetId("getminvalue",
				getRefId());
	}

	/**
	 * Gets the current value
	 * 
	 * @return Slider's current value
	 * @throws Exception
	 */
	public double getValue() throws Exception {
		try {
			return Double.parseDouble(RemoteServer.instance().execute(
					"getvalue", getRefId()));
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * Increments the slider position
	 * 
	 * @throws Exception
	 */
	public void increment() throws Exception {
		RemoteServer.instance().execute("increment", getRefId());
	}

	/**
	 * Decrements the slider position
	 * 
	 * @throws Exception
	 */
	public void decrement() throws Exception {
		RemoteServer.instance().execute("decrement", getRefId());
	}

}
