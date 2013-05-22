package com.thoughtworks.automation.orchestrion;

public final class MenuItems {
	
	private final int refId;
	private final int windowId;

	public MenuItems(int refId, int windowId) {
		this.refId = refId;
		this.windowId = windowId;
	}
	
	public MenuItem get(int index) throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getitembyindex", refId, String.format("%d", index));
			return new MenuItem(id, windowId);
		}
		catch(RefIdNotAvailableException e) {
			return null;
		}
	}
	
	public int count() throws Exception {
		String result = RemoteServer.instance().execute("getitemscount", refId);
		return Integer.parseInt(result);
	}

}
