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
	
	public MessageBox getMessageBox(String title) throws Exception {
		if (title == null) {
			throw new IllegalArgumentException("title");
		}

		try {
			int id = RemoteServer.instance().executeAndGetId("getmessagebox",
					getRefId(), title);
			return new MessageBox(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}
	
	/**
	 * Returns the default menu bar associated within this window
	 * 
	 * @return
	 * @throws Exception
	 */
	public MenuBar getMenuBar() throws Exception {
		int id = RemoteServer.instance().executeAndGetId("getmenubar",
				getRefId());
		return new MenuBar(id, getRefId());
	}
	
	public Window getModalWindow(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getmodalwindow",
				getRefId(), by, by.getValue());
		return new Window(id);
	}

	public Windows getModalWindows() throws Exception {		
		try {
			int id = RemoteServer.instance().executeAndGetId("getmodalwindows", getRefId());
			return new Windows(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}

}
