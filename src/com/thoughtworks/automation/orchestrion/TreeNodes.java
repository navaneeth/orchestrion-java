/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Represents a collection of tree nodes
 * 
 */
public class TreeNodes extends UIItemCollection<TreeNode> {

	/**
	 * Initializes TreeNodes
	 * 
	 * @param refId
	 * @param windowId
	 */
	public TreeNodes(int refId) {
		super(refId);
	}

	@Override
	protected TreeNode createInstance(int refId) {
		return new TreeNode(refId);
	}

}
