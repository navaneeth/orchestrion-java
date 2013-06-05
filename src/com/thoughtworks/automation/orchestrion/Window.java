package com.thoughtworks.automation.orchestrion;

/**
 * Represents a Window
 *
 */
public class Window extends UIItemContainer {

	Window(int refId) {
		super(refId);
	}

	/**
	 * Gets the window title
	 * 
	 * @return title
	 * @throws Exception
	 */
	public String getTitle() throws Exception {
		return RemoteServer.instance().execute("gettitle", getRefId());
	}

	/**
	 * Closes the window
	 * 
	 * @throws Exception
	 */
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
	
	/**
	 * Gets the message box matching the title
	 * @param title Title of the message box
	 * @return MessageBox if available, null otherwise
	 * @throws Exception
	 * @see MessageBox
	 */
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
	
	/**
	 * Gets the modal window associated to this window. 
	 * 
	 * @param by search criteria to locate the window
	 * @return Window if modal window is present, null otherwise
	 * @throws Exception
	 * @see By
	 */
	public Window getModalWindow(By by) throws Exception {
		if (by == null) {
			throw new IllegalArgumentException("by");
		}

		int id = RemoteServer.instance().executeAndGetId("getmodalwindow",
				getRefId(), by, by.getValue());
		return new Window(id);
	}

	/**
	 * Gets all the modal windows available.
	 * 
	 * @return list of windows if available, null otherwise
	 * @throws Exception
	 * @see Windows
	 */
	public Windows getModalWindows() throws Exception {		
		try {
			int id = RemoteServer.instance().executeAndGetId("getmodalwindows", getRefId());
			return new Windows(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}

}
