/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Represents a radiobutton
 * 
 */
public class RadioButton extends Control {

	/**
	 * Initializes a new RadioButton
	 * 
	 * @param refId
	 * @param windowId
	 */
	public RadioButton(int refId, int windowId) {
		super(refId, windowId);
	}

	/**
	 * Selects this radio button
	 * 
	 * @throws Exception
	 */
	public void select() throws Exception {
		RemoteServer.instance().execute("select", getRefId());
	}

	/**
	 * Gets a value indicating whether the radio button is selected
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean isSelected() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute(
				"isselected", getRefId()));
	}

}
