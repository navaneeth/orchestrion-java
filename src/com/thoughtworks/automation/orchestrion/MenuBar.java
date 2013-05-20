package com.thoughtworks.automation.orchestrion;


public class MenuBar extends Control {
	
	public MenuBar(int refId, int windowId) {
		super(refId, windowId);
	}
	
	public MenuItem getMenuItem(String text) throws Exception {
		int id = RemoteServer.instance().executeAndGetId("getmenuitem", getRefId(), text);
		return new MenuItem(id, getWindowId());
	}

}
