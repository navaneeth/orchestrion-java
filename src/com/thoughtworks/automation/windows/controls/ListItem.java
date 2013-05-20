package com.thoughtworks.automation.windows.controls;

import com.thoughtworks.automation.windows.RemoteServer;
import com.thoughtworks.automation.windows.UIItem;

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

}
