package com.thoughtworks.automation.orchestrion;

/**
 * Represents x and y coordinates pair
 *
 */
public final class Point {
	
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

}
