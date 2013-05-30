/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Represents a spinner control
 *
 */
public class Spinner extends Control {

	/**
	 * Initializes a new instance of spinner
	 *  
	 */
	public Spinner(int refId, int windowId) {
		super(refId, windowId);
	}
	
	/**
	 * Gets the current value
	 * 
	 * @return Slider's current value
	 * @throws Exception 
	 */
	public double getValue() throws Exception {
		try {
			return Double.parseDouble(RemoteServer.instance().execute("getvalue", getRefId()));
		}
		catch(NumberFormatException e) {
			return -1;
		}
	}
	
	/**
	 * Sets the specified value to the spinner
	 * 
	 * @param value Value to set
	 * @throws Exception
	 */
	public void setValue(double value) throws Exception {
		RemoteServer.instance().execute("setvalue", getRefId(), String.format("%s", value));
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
