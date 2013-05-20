package com.thoughtworks.automation.windows.controls;

import com.thoughtworks.automation.windows.RefIdNotAvailableException;
import com.thoughtworks.automation.windows.RemoteServer;

/**
 * 
 * Represents a list control
 *
 */
public class ListControl extends Control {

	public ListControl(int refId, int windowId) {
		super(refId, windowId);
	}
	
	/**
	 * Gets the currently selected item. null if there is no selection
	 * 
	 * @return
	 * @throws Exception
	 */
	public ListItem getSelectedItem() throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getselecteditem", getRefId());
			return new ListItem(id);
		}
		catch(RefIdNotAvailableException e) {
			return null;
		}
	}
	
	/**
	 * Gets all the items in this list
	 * 
	 * @return	list of items or null if there are no items
	 * @throws Exception
	 */
	public ListItems getItems() throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getlistitems", getRefId());
			return new ListItems(id);
		}
		catch(RefIdNotAvailableException e) {
			return null;
		}
		
	}

}
