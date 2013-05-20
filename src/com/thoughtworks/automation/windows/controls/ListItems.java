package com.thoughtworks.automation.windows.controls;

import com.thoughtworks.automation.windows.RefIdNotAvailableException;
import com.thoughtworks.automation.windows.RemoteServer;

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

	

}
