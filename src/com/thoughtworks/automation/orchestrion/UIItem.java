package com.thoughtworks.automation.orchestrion;

public abstract class UIItem implements IUIItem {
	
	private final int refId;

	public UIItem(int refId) {
		this.refId = refId;
	}
	
	protected int getRefId() {
		return refId;
	}
	
	/**
	 * Gets a value indicating whether the user can interact with the control
	 */
	public boolean isEnabled() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("isenabled", getRefId()));
	}
	
	/**
	 * Gets a value indicating whether the user can see the control
	 */
	public boolean isVisible() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("isvisible", getRefId()));
	}
	
	/**
	 * Gets a value indicating if the item is off screen
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean isOffScreen() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("isoffscreen", getRefId()));
	}
	
	/**
	 * Gets a value indicating whether the element is focused
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean isFocused() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("isfocused", getRefId()));
	}
	
	/**
	 * Set this control as focused 
	 * 
	 * @throws Exception
	 */
	public void setFocus() throws Exception {
		RemoteServer.instance().execute("setfocus", getRefId());
	}
	
	/**
	 * Perform a mouse click
	 * 
	 */
	public void click() throws Exception {
		RemoteServer.instance().execute("click", getRefId());
	}
	
	/**
	 * Perform a double click
	 * 
	 */
	public void doubleClick() throws Exception {
		RemoteServer.instance().execute("doubleclick", getRefId());
	}
	
	/**
	 * Perform a right click
	 */
	public void rightClick() throws Exception {
		RemoteServer.instance().execute("rightclick", getRefId());
	}
	
	/**
	 * Gets the name of the item
	 */
	public String getName() throws Exception {
		return RemoteServer.instance().execute("getname", getRefId());
	}

}
