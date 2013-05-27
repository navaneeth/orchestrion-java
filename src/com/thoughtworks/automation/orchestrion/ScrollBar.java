package com.thoughtworks.automation.orchestrion;

public abstract class ScrollBar {
	
	private final int refId;

	public ScrollBar(int refId) {
		this.refId = refId;
	}
	
	/**
	 * Gets the current value
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getValue() throws Exception {
		return RemoteServer.instance().executeAndGetId("getvalue", refId);
	}
	
	/**
	 * Gets the maximum value
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getMaxValue() throws Exception {
		return RemoteServer.instance().executeAndGetId("getmaxvalue", refId);
	}
	
	/**
	 * Gets the minimum value
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getMinValue() throws Exception {
		return RemoteServer.instance().executeAndGetId("getminvalue", refId);
	}
	
	/**
	 * Gets a value indicating whether this scroll bar can be scrolled
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean canScroll() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("canscroll", refId));
	}	
	
	protected int getRefId() {
		return refId;
	}	

}
