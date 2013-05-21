package com.thoughtworks.automation.orchestrion;

public abstract class ScrollBar {
	
	private final int refId;

	public ScrollBar(int refId) {
		this.refId = refId;
	}
	
	public int getMaxValue() throws Exception {
		return RemoteServer.instance().executeAndGetId("getmaxvalue", refId);
	}
	
	public int getMinValue() throws Exception {
		return RemoteServer.instance().executeAndGetId("getminvalue", refId);
	}
	
	public boolean canScroll() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("canscroll", refId));
	}
	
	protected int getRefId() {
		return refId;
	}

}
