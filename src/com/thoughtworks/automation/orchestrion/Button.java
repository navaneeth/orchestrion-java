package com.thoughtworks.automation.orchestrion;

public class Button extends Control {
	
	public Button(int refId, int windowId) {
		super(refId, windowId);
	}
	
	public void toggle() throws Exception {
		RemoteServer.instance().execute("toggle", getRefId());
	}

}
