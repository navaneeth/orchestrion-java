package com.thoughtworks.automation.orchestrion;

public class TextBox extends Control {

	/**
	 * Initializes a new instance of TextBox
	 * 
	 * @param refId
	 * @param windowId
	 */
	public TextBox(int refId, int windowId) {
		super(refId, windowId);
	}
	
	/**
	 * Gets the text from the textbox
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getText() throws Exception {
		return RemoteServer.instance().execute("gettext", getRefId());
	}
	
	/**
	 * Sets the specified text to textbox
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public String setText(String text) throws Exception {
		return RemoteServer.instance().execute("settext", getRefId(), text);
	}
	
	/**
	 * Gets a value indicating whether this textbox contents are readonly
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean isReadOnly() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("isreadonly", getRefId()));
	}
	

}
