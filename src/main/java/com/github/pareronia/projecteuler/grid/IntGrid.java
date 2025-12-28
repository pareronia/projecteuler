package com.github.pareronia.projecteuler.grid;

import java.util.Arrays;

public final class IntGrid implements Grid<Integer> {

    private final int[][] values;

    private IntGrid(final int[][] values) {
        this.values = values;
    }

    public static IntGrid from(final int[][] values) {
    	if (Arrays.stream(values).mapToInt(row -> row.length).distinct().count() > 1) {
    		throw new IllegalArgumentException("Expected all rows to be equal size");
    	}
        return new IntGrid(Arrays.stream(values).map(int[]::clone).toArray(int[][]::new));
    }

    @Override
    public Integer getValue(final Cell cell) {
        return this.values[cell.row()][cell.col()];
    }

    @Override
    public int getHeight() {
        return this.values.length;
    }

    @Override
    public int getWidth() {
        return this.values[0].length;
    }
}
