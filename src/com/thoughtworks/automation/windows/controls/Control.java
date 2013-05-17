package com.thoughtworks.automation.windows.controls;

import com.thoughtworks.automation.windows.UIItem;

public abstract class Control extends UIItem {

	private final int windowId;

	public Control(int refId, int windowId) {
		super(refId);
		this.windowId = windowId;
	}
	
	protected int getWindowId() {
		return windowId;
	}

}
