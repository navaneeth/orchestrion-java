package com.thoughtworks.automation.orchestrion;

import java.util.List;

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
	
	/**
	 * Press keyboard shortcuts
	 * <p>
	 * This method will hold all the keys but last and press the last key. 
	 * <p>
	 * <pre>
	 * {@code
	 * window.pressKeyboardShortcut(new Keys(SpecialKeys.ALT), new Keys('o', 'f'))
	 * }
	 * </pre>
	 * The above code will hold ALT key and presses 'o' and 'f'
	 * 
	 * @param keys List of keys which needs to be pressed
	 * 
	 * @throws Exception
	 * @see Keys
	 */
	public void pressKeyboardShortcut(Keys... keys) throws Exception {
		Keyboard keyBoard = getKeyBoard();
		try {
			for (int i = 0; i < keys.length; i++) {
				final Keys thisKeys = keys[i];
				final boolean isLastItem = (i + 1) == keys.length;
				if (isLastItem) {
					holdAllKeysAndPressLastKey(thisKeys, keyBoard);
				}
				else {
					holdAllKeys(thisKeys, keyBoard);
				}
			}
		}
		finally {
			keyBoard.releaseAllKeys();
		}
	}

	private void holdAllKeys(Keys keys, Keyboard keyBoard) throws Exception {
		if (keys.isSpecialKey()) {
			for (SpecialKeys k : keys.getSpecialKeys()) {
				keyBoard.holdSpecialKey(k);
				waitWhileBusy();
			}
		}
		else {
			for (Character c : keys.getRegularKeys()) {
				keyBoard.enter(c.toString());
				waitWhileBusy();
			}
		}
	}

	private void holdAllKeysAndPressLastKey(Keys keys, Keyboard keyBoard) throws Exception {
		if (keys.isSpecialKey()) {
			final List<SpecialKeys> specialKeys = keys.getSpecialKeys(); 
			for(int i = 0; i < specialKeys.size(); i++) {
				final boolean lastKey = (i + 1) == specialKeys.size();
				if (lastKey) {
					keyBoard.pressSpecialKey(specialKeys.get(i));
					waitWhileBusy();
				}
				else {
					keyBoard.holdSpecialKey(specialKeys.get(i));
					waitWhileBusy();
				}
			}
		}
		else {
			for(Character c : keys.getRegularKeys()) {
				keyBoard.enter(c.toString());
				waitWhileBusy();
			}
		}
	}

}
