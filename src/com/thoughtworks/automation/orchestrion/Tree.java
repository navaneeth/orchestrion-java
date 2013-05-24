/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Represents a Tree control
 * 
 */
public class Tree extends Control {

	/**
	 * Initializes a new Tree control
	 * 
	 * @param refId
	 * @param windowId
	 */
	public Tree(int refId, int windowId) {
		super(refId, windowId);
	}

	/**
	 * Gets all the nodes
	 * 
	 * @return
	 * @throws Exception
	 */
	public TreeNodes getNodes() throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getnodes",
					getRefId());
			return new TreeNodes(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}

	/**
	 * Gets the selected node, null if there are no selections
	 * 
	 * @return
	 * @throws Exception
	 */
	public TreeNode getSelectedNode() throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getselectednode",
					getRefId());
			return new TreeNode(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}

	/**
	 * Gets a node which has the given text
	 * 
	 * @param nodeText
	 * @return
	 * @throws Exception
	 */
	public TreeNode getNode(String nodeText) throws Exception {
		int id = RemoteServer.instance().executeAndGetId("getnode", getRefId(),
				nodeText);
		return new TreeNode(id);
	}

	/**
	 * Expands all the child nodes
	 * 
	 * @throws Exception
	 */
	public void expandAll() throws Exception {
		TreeNodes allNodes = getNodes();
		if (allNodes == null) {
			return;
		}

		for (int i = 0; i < allNodes.count(); i++) {
			TreeNode treeNode = allNodes.get(i);
			treeNode.expandAll();
		}
	}

	/**
	 * Collapse all the child nodes
	 * 
	 * @throws Exception
	 */
	public void collapseAll() throws Exception {
		TreeNodes allNodes = getNodes();
		if (allNodes == null) {
			return;
		}

		for (int i = 0; i < allNodes.count(); i++) {
			TreeNode treeNode = allNodes.get(i);
			treeNode.collapseAll();
		}
	}

}
