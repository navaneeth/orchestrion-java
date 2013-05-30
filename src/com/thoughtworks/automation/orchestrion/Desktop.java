/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Represents the Desktop.
 * 
 */
public final class Desktop extends UIItemContainer {

	Desktop(int refId) {
		super(refId);
	}

	/**
	 * Gets the current desktop.
	 * 
	 * 
	 * @return current desktop
	 * @throws Exception
	 */
	public static Desktop getCurrent() throws Exception {

		try {
			int id = RemoteServer.instance().executeAndGetId("getdesktop", 0);
			return new Desktop(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}

	/**
	 * Gets all the icons which are available in the desktop
	 * 
	 * @return list of icons if available, null otherwise
	 * @throws Exception
	 * @see ListItems
	 */
	public ListItems getIcons() throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("geticons",
					getRefId());
			return new ListItems(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}

	/**
	 * Gets all the windows which are open in the desktop
	 * 
	 * @return list of windows if available, null otherwise
	 * @throws Exception
	 */
	public Windows getAllWindows() throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getwindows",
					getRefId());
			return new Windows(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}

}
