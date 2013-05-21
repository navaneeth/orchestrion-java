package com.thoughtworks.automation.orchestrion;

public final class HScrollBar extends ScrollBar {

	public HScrollBar(int refId) {
		super(refId);
	}

	public void scrollLeft() throws Exception {
		RemoteServer.instance().execute("scrollleft", getRefId());
	}

	public void scrolRight() throws Exception {
		RemoteServer.instance().execute("scrollright", getRefId());
	}	

}
