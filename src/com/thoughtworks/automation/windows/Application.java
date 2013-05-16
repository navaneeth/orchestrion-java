package com.thoughtworks.automation.windows;

public class Application {

	private final int refId;
	private RemoteServer server;

	public static Application Launch(String applicationPath) throws Exception {		
		RemoteServer s = new RemoteServer();
		int refId = s.executeAndGetId("launch", 0, applicationPath);
		return new Application(refId);
	}
	
	public Application(int refId) {
		this.refId = refId;
		this.server = new RemoteServer();
	}
	
	public Window getWindow(String windowTitle) throws Exception {
		int id = server.executeAndGetId("getwindow", refId, windowTitle);
		return new Window(id);
	}
	
	public void close() throws Exception {
		server.execute("close", refId, null);
	}

}
