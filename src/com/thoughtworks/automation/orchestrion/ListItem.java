package com.thoughtworks.automation.orchestrion;


public class ListItem extends UIItem {

	public ListItem(int refId) {
		super(refId);
	}
	
	public void check() throws Exception {
		RemoteServer.instance().execute("checklistitem", getRefId());
	}
	
	public void uncheck() throws Exception {
		RemoteServer.instance().execute("unchecklistitem", getRefId());
	}
	
	public boolean isChecked() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("ischecked", getRefId()));		
	}
	
	public void select() throws Exception {
		RemoteServer.instance().execute("selectlistitem", getRefId());
	}
	
	public boolean isSelected() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("isselected", getRefId()));
	}
	
	public String getText() throws Exception {
		return RemoteServer.instance().execute("gettext", getRefId());
	}

}
