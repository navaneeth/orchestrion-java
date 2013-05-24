package com.thoughtworks.automation.orchestrion;

/**
 * Represents an item in the menu
 * 
 */
public class MenuItem extends UIItem {

	public MenuItem(int refId) {
		super(refId);
	}

	public MenuItem getMenuItem(String text) throws Exception {
		int id = RemoteServer.instance().executeAndGetId("getmenuitem",
				getRefId(), text);
		return new MenuItem(id);
	}

	/**
	 * Gets the child items.
	 * 
	 * @return MenuItems if child elements available, null otherwise
	 * @throws Exception
	 */
	public MenuItems getChildren() throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getchildren",
					getRefId());
			return new MenuItems(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}

}
