/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

import java.util.ArrayList;

/**
 * Represents a keyboard attached to the UIItem
 *
 */
public final class Keyboard {
	
	private final int refId;
	private final ArrayList<SpecialKeys> heldKeys = new ArrayList<SpecialKeys>();

	Keyboard(int refId) {
		this.refId = refId;
	}
	
	/**
	 * Types the specified keys
	 * 
	 * @param keysToType Keys which needs to be typed 
	 * @throws Exception 
	 */
	public void enter(String keysToType) throws Exception {
		RemoteServer.instance().execute("enter", refId, keysToType);
	}
	
	/**
	 * Press the special key
	 * 
	 * @param key Key to press
	 * @throws Exception
	 */
	public void pressSpecialKey(SpecialKeys key) throws Exception {
		RemoteServer.instance().execute("pressspecialkey", refId, String.format("%d", key.getCode()));
	}
	
	/**
	 * Hold the specified key pressed
	 * 
	 * @param key
	 * @throws Exception
	 */
	public void holdSpecialKey(SpecialKeys key) throws Exception {
		RemoteServer.instance().execute("holdspecialkey", refId, String.format("%d", key.getCode()));
		heldKeys.add(key);
	}
	
	/**
	 * Releases the key
	 * 
	 * @param key
	 * @throws Exception
	 */
	public void releaseSpecialKey(SpecialKeys key) throws Exception {
		RemoteServer.instance().execute("releasespecialkey", refId, String.format("%d", key.getCode()));
		heldKeys.remove(key);
	}
	
	/**
	 * Releases all the keys which are in hold state
	 * 
	 * @throws Exception
	 */
	public void releaseAllKeys() throws Exception {
		while(heldKeys.size() > 0) {
			releaseSpecialKey(heldKeys.get(0));
		}
	}
	
	/**
	 * Gets a value indicating whether the caps lock is on
	 * 
	 * @return true if caps lock is on, false otherwise
	 * @throws Exception
	 */
	public boolean isCapsLockOn() throws Exception {
		return Boolean.parseBoolean(RemoteServer.instance().execute("iscapslockon", refId));
	}
	
	/**
	 * Turn on caps lock
	 * 
	 * @throws Exception
	 */
	public void setCapsLockOn() throws Exception {
		RemoteServer.instance().execute("changecapslock", refId, "true");
	}
	
	/**
	 * Turn caps lock off
	 * 
	 * @throws Exception
	 */
	public void setCapsLockOff() throws Exception {
		RemoteServer.instance().execute("changecapslock", refId, "false");
	}
}
