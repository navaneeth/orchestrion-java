package com.thoughtworks.automation.orchestrion;

/**
 * Represents a collection of menu item
 * 
 */
public final class MenuItems extends UIItemCollection<MenuItem> {

	public MenuItems(int refId) {
		super(refId);
	}

	@Override
	protected MenuItem createInstance(int refId) {
		return new MenuItem(refId);
	}

}
