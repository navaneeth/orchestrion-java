package com.thoughtworks.automation.orchestrion;


public class Application {

	private final int refId;

	public static Application launch(String applicationPath) throws Exception {		
		int refId = RemoteServer.instance().executeAndGetId("launch", 0, applicationPath);
		return new Application(refId);
	}
	
	public static Application attach(int processId) throws Exception {
		int refId = RemoteServer.instance().executeAndGetId("attach", 0, String.format("%d", processId));
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
	
	/**
	 * Waits till the processing finishes
	 * 
	 *  This is useful when some methods are asynchronous and you need to wait till the operation 
	 *  completes.
	 * 
	 * @throws Exception
	 */
	public void waitWhileBusy() throws Exception {
		RemoteServer.instance().execute("waitwhilebusy", refId);
	}

}
