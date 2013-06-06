/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of SpecialKeys/normal keys
 *
 */
public final class Keys {
	
	private final ArrayList<SpecialKeys> specialKeys = new ArrayList<SpecialKeys>();
	private boolean isSpecialKey = false;
	private final ArrayList<Character> regularKeys = new ArrayList<Character>();

	/**
	 * Initializes Keys instance from a special keys
	 * 
	 * @param keys One or more special keys
	 */
	public Keys(SpecialKeys... keys) {
		this.isSpecialKey = true;
		for (SpecialKeys key : keys) {
			specialKeys.add(key);
		}
	}
	
	/**
	 * Initializes Keys instance from the supplied keys
	 * 
	 * @param keys
	 */
	public Keys(char... keys) {
		for (char c : keys) {
			regularKeys.add(c);
		}
	}
	
	/**
	 * Gets a value indicating whether this key is a special key
	 * 
	 * @return
	 */
	public boolean isSpecialKey() {
		return isSpecialKey;
	}
	
	public List<SpecialKeys> getSpecialKeys() {
		return specialKeys;
	}
	
	public List<Character> getRegularKeys() {
		return regularKeys;
	}

}
