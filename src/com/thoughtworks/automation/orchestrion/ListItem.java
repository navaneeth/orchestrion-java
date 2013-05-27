package com.thoughtworks.automation.orchestrion;


public class ListItem extends UIItem {

	public ListItem(int refId) {
		super(refId);
	}
	
	/**
	 * Marks this item as checked if it has checkboxes 
	 * 
	 * @throws Exception
	 */
	public void check() throws Exception {
		RemoteServer.instance().execute("check", getRefId());
	}
	
	/**
	 * Uncheck this item
	 * 
	 * @throws Exception
	 */
	public void uncheck() throws Exception {
		RemoteServer.instance().execute("uncheck", getRefId());
	}
	
	/**
	 * Gets a value indicating whether this item is checked currently
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean isChecked() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("ischecked", getRefId()));		
	}
	
	/**
	 * Selects this list item. This will change list control's selected item
	 * 
	 * @throws Exception
	 */
	public void select() throws Exception {
		RemoteServer.instance().execute("select", getRefId());
	}
	
	/**
	 * Gets a value indicating whether a controls is selected
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean isSelected() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("isselected", getRefId()));
	}
	
	/**
	 * Gets the list item text
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getText() throws Exception {
		return RemoteServer.instance().execute("gettext", getRefId());
	}

}
