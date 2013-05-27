/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Represents a collection of windows
 *
 */
public class Windows extends UIItemCollection<Window> {

	/**
	 * Initializes collection of windows
	 */
	public Windows(int refId) {
		super(refId);
	}
	
	@Override
	protected Window createInstance(int refId) {
		return new Window(refId);
	}

}
