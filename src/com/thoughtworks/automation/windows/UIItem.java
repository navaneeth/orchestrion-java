package com.thoughtworks.automation.windows;

public abstract class UIItem implements IUIItem {
	
	private final int refId;

	public UIItem(int refId) {
		this.refId = refId;
	}
	
	protected int getRefId() {
		return refId;
	}
	
	public boolean isEnabled() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("isenabled", getRefId()));
	}
	
	public boolean isOffScreen() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("isoffscreen", getRefId()));
	}
	
	public void click() throws Exception {
		RemoteServer.instance().execute("click", getRefId());
	}
	
	public void doubleClick() throws Exception {
		RemoteServer.instance().execute("doubleclick", getRefId());
	}

}
