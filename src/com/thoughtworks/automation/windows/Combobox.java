package com.thoughtworks.automation.windows;

public class Combobox {

	private final int refId;
	private final int windowId;
	private RemoteServer server;

	public Combobox(int refId, int windowId) {
		this.refId = refId;
		this.windowId = windowId;
		this.server = new RemoteServer();
	}
	
	public void selectText(String textToSelect) throws Exception {
		if (textToSelect == null) {
			throw new IllegalArgumentException(textToSelect);
		}
		
		server.execute("selecttext", refId, textToSelect);
	}
	
}
