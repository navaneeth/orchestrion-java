package com.thoughtworks.automation.orchestrion;


public class MenuItem extends Control {
	
	public MenuItem(int refId, int windowId) {
		super(refId, windowId);
	}
	
	public MenuItem getMenuItem(String text) throws Exception {
		int id = RemoteServer.instance().executeAndGetId("getmenuitem", getRefId(), text);
		return new MenuItem(id, getWindowId());
	}	
	
	public MenuItems getChildren() throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getchildren", getRefId());
			return new MenuItems(id, getWindowId());
		}
		catch(RefIdNotAvailableException e) {
			return null;
		}
	}
	
}
