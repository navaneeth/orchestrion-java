/**
 * 
 */
package com.thoughtworks.automation.orchestrion;

/**
 * Represents a single Key. This also contains a list of special keys
 * 
 */
public final class Key {

	public static final Key SHIFT = new Key(0x10);
	public static final Key CONTROL = new Key(0x11);
	public static final Key ALT = new Key(0x12);
	public static final Key LEFT_ALT = new Key(0xA4);
	public static final Key RIGHT_ALT = new Key(0xA5);
	public static final Key RETURN = new Key(0x0D);
	public static final Key RIGHT = new Key(0x27);
	public static final Key BACKSPACE = new Key(0x08);
	public static final Key LEFT = new Key(0x25);
	public static final Key ESCAPE = new Key(0x1B);
	public static final Key TAB = new Key(0x09);
	public static final Key HOME = new Key(0x24);
	public static final Key END = new Key(0x23);
	public static final Key UP = new Key(0x26);
	public static final Key DOWN = new Key(0x28);
	public static final Key INSERT = new Key(0x2D);
	public static final Key DELETE = new Key(0x2E);
	public static final Key CAPS = new Key(0x14);
	public static final Key F1 = new Key(0x70);
	public static final Key F2 = new Key(0x71);
	public static final Key F3 = new Key(0x72);
	public static final Key F4 = new Key(0x73);
	public static final Key F5 = new Key(0x74);
	public static final Key F6 = new Key(0x75);
	public static final Key F7 = new Key(0x76);
	public static final Key F8 = new Key(0x77);
	public static final Key F9 = new Key(0x78);
	public static final Key F10 = new Key(0x79);
	public static final Key F11 = new Key(0x7A);
	public static final Key F12 = new Key(0x7B);
	public static final Key F13 = new Key(0x7C);
	public static final Key F14 = new Key(0x7D);
	public static final Key F15 = new Key(0x7E);
	public static final Key F16 = new Key(0x7F);
	public static final Key F17 = new Key(0x80);
	public static final Key F18 = new Key(0x81);
	public static final Key F19 = new Key(0x82);
	public static final Key F20 = new Key(0x83);
	public static final Key F21 = new Key(0x84);
	public static final Key F22 = new Key(0x85);
	public static final Key F23 = new Key(0x86);
	public static final Key F24 = new Key(0x87);
	public static final Key PAGEUP = new Key(0x21);
	public static final Key PAGEDOWN = new Key(0x22);
	public static final Key PRINT = new Key(0x2A);
	public static final Key PRINTSCREEN = new Key(0x2C);
	public static final Key SPACE = new Key(0x20);
	public static final Key NUMLOCK = new Key(0x90);
	public static final Key SCROLL = new Key(0x91);
	public static final Key LWIN = new Key(0x5B);
	public static final Key RWIN = new Key(0x5C);

	/**
	 * Gets a Key instance from a character
	 * 
	 * @param c
	 *            character
	 * @return
	 */
	public static Key get(Character c) {
		return new Key(c);
	}

	private int specialKeyCode = -1;
	private boolean isSpecialKey = false;
	private Character c = null;

	private Key(int code) {
		this.specialKeyCode = code;
		this.isSpecialKey = true;
	}

	private Key(Character c) {
		this.c = c;
	}

	/**
	 * Gets a value indicating whether this instance represents a special key.
	 * 
	 * @return true if it is a special key, false otherwise
	 */
	public boolean isSpecialKey() {
		return isSpecialKey;
	}

	/**
	 * Gets the key code for the special key. represents a special key
	 * 
	 * @return
	 */
	public int getSpecialKey() {
		return specialKeyCode;
	}

	/**
	 * Gets the key string constructed using {@link Key#get(Character)}
	 * 
	 * @return
	 */
	public String getKeyString() {
		return c.toString();
	}

}
