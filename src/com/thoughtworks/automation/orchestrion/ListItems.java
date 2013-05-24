package com.thoughtworks.automation.orchestrion;

/**
 * Represents a collection of list items
 *
 */
public class ListItems extends UIItemCollection<ListItem> {
	
	public ListItems(int refId) {
		super(refId);
	}
	
	/**
	 * Gets the selected list item from the collection of all list items
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
	 * Gets the item based on the text
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public ListItem get(String text) throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getitembytext", getRefId(), text);
			return new ListItem(id);
		}
		catch(RefIdNotAvailableException e) {
			return null;
		}
	}

	@Override
	protected ListItem createInstance(int refId) {
		return new ListItem(refId);
	}

}
