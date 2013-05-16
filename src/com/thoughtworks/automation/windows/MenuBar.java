package com.thoughtworks.automation.windows;

public class MenuBar {
	
	private final int refId;
	private final int windowId;
	private RemoteServer server;

	public MenuBar(int refId, int windowId) {
		this.refId = refId;
		this.windowId = windowId;
		this.server = new RemoteServer();
	}
	
	public MenuItem getMenuItem(String text) throws Exception {
		int id = server.executeAndGetId("getmenuitem", refId, text);
		return new MenuItem(id);
	}

}
