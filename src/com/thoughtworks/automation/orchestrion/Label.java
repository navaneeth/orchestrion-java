package com.thoughtworks.automation.orchestrion;

public class Label extends Control {

	public Label(int refId, int windowId) {
		super(refId, windowId);
	}
	
	public String getText() throws Exception {
		return RemoteServer.instance().execute("gettext", getRefId());
	}

}
