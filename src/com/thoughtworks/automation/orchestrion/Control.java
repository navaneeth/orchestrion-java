package com.thoughtworks.automation.orchestrion;

public abstract class Control extends UIItem {

	private final int windowId;

	public Control(int refId, int windowId) {
		super(refId);
		this.windowId = windowId;
	}

	protected int getWindowId() {
		return windowId;
	}

	public Window getParentWindow() throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId(
					"getwindowfromrefid", getWindowId());
			return new Window(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}

}
