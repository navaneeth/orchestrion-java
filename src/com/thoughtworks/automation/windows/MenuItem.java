package com.thoughtworks.automation.windows;

public class MenuItem {
	
	private final int refId;
	private RemoteServer server;

	public MenuItem(int refId) {
		this.refId = refId;
		this.server = new RemoteServer();
	}
	
	public MenuItem getMenuItem(String text) throws Exception {
		int id = server.executeAndGetId("getmenuitem", refId, text);
		return new MenuItem(id);
	}
	
	public void click() throws Exception {
		server.execute("click", refId, (String[]) null);
	}
		
}
