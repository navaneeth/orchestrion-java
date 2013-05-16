package com.thoughtworks.automation.windows;

public class Button {
	
	private final int refId;
	private final int windowId;
	private RemoteServer server;

	public Button(int refId, int windowId) {
		this.refId = refId;
		this.windowId = windowId;
		this.server = new RemoteServer();
	}
	
	public void click() throws Exception {
		server.execute("click", refId);
	}

}
