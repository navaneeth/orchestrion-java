package com.thoughtworks.automation.windows;

public abstract class UIItem implements IUIItem {
	
	private final int refId;

	public UIItem(int refId) {
		this.refId = refId;
	}
	
	protected int getRefId() {
		return refId;
	}
	
	public void click() throws Exception {
		RemoteServer.instance().execute("click", getRefId());
	}

}
