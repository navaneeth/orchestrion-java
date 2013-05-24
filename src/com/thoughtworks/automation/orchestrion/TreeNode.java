/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Represents a tree node
 * 
 */
public class TreeNode extends UIItem {

	/**
	 * Initializes a new tree node
	 * 
	 * @param refId
	 */
	public TreeNode(int refId) {
		super(refId);
	}

	/**
	 * Expands this tree node
	 * 
	 * @throws Exception
	 */
	public void expand() throws Exception {
		RemoteServer.instance().execute("expand", getRefId());
	}

	/**
	 * Collapse this tree node
	 * 
	 * @throws Exception
	 */
	public void collapse() throws Exception {
		RemoteServer.instance().execute("collapse", getRefId());
	}

	/**
	 * Gets a value indicating whether the this node is expanded
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean isExpanded() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute(
				"isexpanded", getRefId()));
	}

	/**
	 * Selects this node
	 * 
	 * @throws Exception
	 */
	public void select() throws Exception {
		RemoteServer.instance().execute("selecttreenode", getRefId());
	}

	/**
	 * Deselects this node
	 * 
	 * @throws Exception
	 */
	public void deselect() throws Exception {
		RemoteServer.instance().execute("deselecttreenode", getRefId());
	}

	/**
	 * Gets a value indicating whether this node is selected
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean isSelected() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute(
				"isselected", getRefId()));
	}

	/**
	 * Gets this node's text
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getText() throws Exception {
		return RemoteServer.instance().execute("gettext", getRefId());
	}

	/**
	 * Gets the child items.
	 * 
	 * @return TreeNodes if child elements available, null otherwise
	 * @throws Exception
	 */
	public TreeNodes getChildren() throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getchildren",
					getRefId());
			return new TreeNodes(id);
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
		try {
			this.expand();
		} catch (Exception e) {
			// ignoring the exception as this could be the last element in the
			// tree
		}

		TreeNodes children = getChildren();
		if (children == null) {
			return;
		}

		for (int i = 0; i < children.count(); i++) {
			TreeNode child = children.get(i);
			child.expandAll();
		}
	}

	/**
	 * Collapses all the child elements
	 * 
	 * @throws Exception
	 */
	public void collapseAll() throws Exception {
		TreeNodes children = getChildren();
		if (children != null) {
			for (int i = 0; i < children.count(); i++) {
				TreeNode child = children.get(i);
				child.collapseAll();
			}
		}

		try {
			this.collapse();
		} catch (Exception e) {
			// ignoring the exception as this could be the last element in the
			// tree
		}
	}

}
