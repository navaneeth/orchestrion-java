package com.thoughtworks.automation.orchestrion;

public class Window extends UIItemContainer {

	public Window(int refId) {
		super(refId);
	}

	public String getTitle() throws Exception {
		return RemoteServer.instance().execute("gettitle", getRefId());
	}

	public void close() throws Exception {
		RemoteServer.instance().execute("close", getRefId());
	}

	/**
	 * Waits till the processing finishes
	 * 
	 * This is useful when some methods are asynchronous and you need to wait
	 * till the operation completes.
	 * 
	 * @throws Exception
	 */
	public void waitWhileBusy() throws Exception {
		RemoteServer.instance().execute("waitwhilebusy", getRefId());
	}

}
