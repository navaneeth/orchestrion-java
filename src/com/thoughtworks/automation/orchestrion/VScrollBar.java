package com.thoughtworks.automation.orchestrion;

public final class VScrollBar extends ScrollBar {

	public VScrollBar(int refId) {
		super(refId);
	}

	public void scrollUp() throws Exception {
		RemoteServer.instance().execute("scrollup", getRefId());
	}

	public void scrollDown() throws Exception {
		RemoteServer.instance().execute("scrolldown", getRefId());
	}	

}
