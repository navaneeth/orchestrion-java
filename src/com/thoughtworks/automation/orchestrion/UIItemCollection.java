/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Base class for all UIItem collections
 * 
 */
public abstract class UIItemCollection<T extends UIItem> {

	private final int refId;

	public UIItemCollection(int refId) {
		this.refId = refId;		
	}

	public T get(int index) throws Exception {
		try {
			int id = RemoteServer.instance().executeAndGetId("getitembyindex",
					refId, String.format("%d", index));
			return createInstance(id);
		} catch (RefIdNotAvailableException e) {
			return null;
		}
	}

	public int count() throws Exception {
		String result = RemoteServer.instance().execute("getitemscount", refId);
		return Integer.parseInt(result);
	}

	protected abstract T createInstance(int refId);

	protected int getRefId() {
		return refId;
	}

}
