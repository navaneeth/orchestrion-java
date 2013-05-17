package com.thoughtworks.automation.windows;

import com.thoughtworks.automation.windows.controls.Window;

public class Application {

	private final int refId;

	public static Application Launch(String applicationPath) throws Exception {		
		int refId = RemoteServer.instance().executeAndGetId("launch", 0, applicationPath);
		return new Application(refId);
	}
	
	public Application(int refId) {
		this.refId = refId;
	}
	
	public Window getWindow(String windowTitle) throws Exception {
		int id = RemoteServer.instance().executeAndGetId("getwindow", refId, windowTitle);
		return new Window(id);
	}
	
	public void close() throws Exception {
		RemoteServer.instance().execute("close", refId);
	}

}
