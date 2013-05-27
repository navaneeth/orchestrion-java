/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Represents a checkbox
 * 
 */
public class CheckBox extends Control {

	public CheckBox(int refId, int windowId) {
		super(refId, windowId);
	}

	/**
	 * Checks this checkbox
	 * 
	 * @throws Exception
	 */
	public void check() throws Exception {
		RemoteServer.instance().execute("check", getRefId());
	}

	/**
	 * Unchecks this checkbox
	 * 
	 * @throws Exception
	 */
	public void unCheck() throws Exception {
		RemoteServer.instance().execute("uncheck", getRefId());
	}

	/**
	 * Gets a value indicating whether this checkbox is checked
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean isChecked() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute(
				"ischecked", getRefId()));
	}

}
