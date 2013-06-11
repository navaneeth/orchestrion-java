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
	 * Gets a value indicating whether this window is a modal window
	 * 
	 * @return true if it is a modal window, false otherwise.
	 * @throws Exception
	 */
	public boolean isModal() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("ismodal",
				getRefId()));
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
	 * 
	 * @param title
	 *            Title of the message box
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
	 * Gets the modal window associated to this window
	 * 
	 * @param title
	 *            Modal window's title
	 * @return a Window instance representing the modal window, null otherwise
	 * @throws Exception
	 */
	public Window getModalWindow(String title) throws Exception {
		if (title == null || title.isEmpty())
			throw new NullPointerException("title");

		try {
			int id = RemoteServer.instance().executeAndGetId("getmodalwindow",
					getRefId(), title);
			return new Window(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}

	}

	/**
	 * Gets the modal window associated to this window.
	 * 
	 * @param by
	 *            search criteria to locate the window
	 * @return a Window instance representing the modal window, null otherwise
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
			int id = RemoteServer.instance().executeAndGetId("getmodalwindows",
					getRefId());
			return new Windows(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}

	/**
	 * Press keyboard shortcuts
	 * <p>
	 * This method will hold all the keys except the last and press the last
	 * key. Only special keys will be put on hold, all other keys will be
	 * pressed
	 * <p>
	 * 
	 * <pre>
	 * {@code
	 * window.pressKeyboardShortcut(Key.ALT, Key.from('o'), Key.from('f'))
	 * }
	 * </pre>
	 * 
	 * The above code will hold ALT key and presses 'o' and 'f'
	 * 
	 * @param keys
	 *            List of keys which needs to be pressed
	 * 
	 * @throws Exception
	 * @see Keys
	 */
	public void pressKeyboardShortcut(Key... keys) throws Exception {
		Keyboard keyBoard = getKeyBoard();
		try {
			for (int i = 0; i < keys.length; i++) {
				final Key thisKey = keys[i];
				final boolean isLastItem = (i + 1) == keys.length;
				if (isLastItem) {
					keyBoard.pressKey(thisKey);
					waitWhileBusy();
				} else {
					if (thisKey.isSpecialKey()) {
						keyBoard.holdSpecialKey(thisKey);
					} else {
						keyBoard.pressKey(thisKey);
						waitWhileBusy();
					}
				}
			}
		} finally {
			keyBoard.releaseAllKeys();
		}
	}

}
