package com.github.pareronia.projecteuler.grid;

import com.github.pareronia.projecteuler.geometry.Direction;

public record Cell(int row, int col) {

	public static Cell at(final int row, final int col) {
		return new Cell(row, col);
	}

	public Cell at(final Direction direction) {
		return Cell.at(this.row - direction.getY(), this.col + direction.getX());
	}
}