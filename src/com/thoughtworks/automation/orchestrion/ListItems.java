package com.thoughtworks.automation.orchestrion;


public class ListItems {
	
	private final int refId;

	public ListItems(int refId) {
		this.refId = refId;
	}
	
	public ListItem getSelectedItem() throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getselecteditem", refId);
			return new ListItem(id);
		}
		catch(RefIdNotAvailableException e) {
			return null;
		}
	}
	
	public ListItem get(int index) throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getitembyindex", refId, String.format("%d", index));
			return new ListItem(id);
		}
		catch(RefIdNotAvailableException e) {
			return null;
		}
	}
	
	public ListItem get(String text) throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getitembytext", refId, text);
			return new ListItem(id);
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