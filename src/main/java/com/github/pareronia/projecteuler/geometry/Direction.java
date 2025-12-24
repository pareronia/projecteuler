package com.github.pareronia.projecteuler.geometry;

public enum Direction {

    NONE(0, 0),
    UP(0, 1),
    RIGHT_AND_UP(1, 1),
    RIGHT(1, 0),
    RIGHT_AND_DOWN(1, -1),
    DOWN(0, -1),
    LEFT_AND_DOWN(-1, -1),
    LEFT(-1, 0),
    LEFT_AND_UP(-1, 1);

    private final int x;
    private final int y;

    Direction(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
