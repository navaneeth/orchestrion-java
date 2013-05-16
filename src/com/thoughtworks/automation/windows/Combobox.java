package com.thoughtworks.automation.windows;

/**
 * Represents a combobox.
 *
 */
public class Combobox {

	private final int refId;
	private final int windowId;
	private RemoteServer server;

	/**
	 * Creates a new combobox instance
	 * 
	 * @param refId		Ref id for this object
	 * @param windowId	Ref id of the window where this control belongs to
	 */
	public Combobox(int refId, int windowId) {
		this.refId = refId;
		this.windowId = windowId;
		this.server = new RemoteServer();
	}
	
	/**
	 * Selects the specified text from the list  
	 * 
	 * @param textToSelect	Text to be selected
	 * @throws Exception
	 */
	public void selectText(String textToSelect) throws Exception {
		if (textToSelect == null) {
			throw new IllegalArgumentException(textToSelect);
		}
		
		server.execute("selecttext", refId, textToSelect);
	}
	
	/**
	 * Checks whether this combobox can be edited
	 * 
	 * @return				true if combobox can be edited, false otherwise
	 * @throws Exception
	 */
	public boolean isEditable() throws Exception {
		String result = server.execute("iseditable", refId);
		return Boolean.parseBoolean(result);
	}
	
}
