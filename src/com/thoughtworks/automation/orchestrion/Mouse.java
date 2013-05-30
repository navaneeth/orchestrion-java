/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Represents a mouse and actions
 * 
 */
public final class Mouse {

	private final int refId;

	Mouse(int refId) {
		this.refId = refId;
	}

	/**
	 * Performs a click
	 * 
	 * @throws Exception
	 */
	public void click() throws Exception {
		RemoteServer.instance().execute("click", refId);
	}

	/**
	 * Performs a right click
	 * 
	 * @throws Exception
	 */
	public void rightClick() throws Exception {
		RemoteServer.instance().execute("rightclick", refId);
	}

	/**
	 * Drags an items and drops it to a different item
	 * 
	 * @param itemToDrag
	 *            Item to drag
	 * @param destination
	 *            Control where the dragged item will be dropped to
	 * @throws Exception
	 */
	public void dragAndDrop(UIItem itemToDrag, UIItem destination)
			throws Exception {
		RemoteServer.instance().execute("draganddrop", refId,
				String.format("%d", itemToDrag.getRefId()),
				String.format("%d", destination.getRefId()));
	}

	/**
	 * Gets the current mouse position
	 * 
	 * @return
	 * @throws Exception
	 */
	public Point getCurrentPosition() throws Exception {
		String result = RemoteServer.instance().execute("getcurrentposition",
				refId);
		if (result.isEmpty())
			return null;

		String[] parts = result.split(",");
		if (parts.length != 2)
			return null;

		try {
			int x = Integer.parseInt(parts[0]);
			int y = Integer.parseInt(parts[1]);
			return new Point(x, y);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * Sets the mouse position to the specified point
	 * 
	 * @param point
	 *            Position to set the value to
	 * @throws Exception
	 */
	public void setPosition(Point point) throws Exception {
		RemoteServer.instance().execute("setposition", refId,
				String.format("%d", point.getX()),
				String.format("%d", point.getY()));
	}

}
