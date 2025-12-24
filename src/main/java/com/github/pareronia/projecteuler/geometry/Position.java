package com.github.pareronia.projecteuler.geometry;

public record Position(int x, int y) {

	public static Position of(final int x, final int y) {
		return new Position(x, y);
	}
}
