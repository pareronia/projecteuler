package com.github.pareronia.projecteuler.grid;

import java.util.Arrays;

public final class IntGrid implements Grid<Integer> {

    private final int[][] values;

    private IntGrid(final int[][] values) {
        this.values = values;
    }

    public static IntGrid from(final int[][] values) {
        return new IntGrid(Arrays.stream(values).map(int[]::clone).toArray(int[][]::new));
    }

    public static IntGrid from(final String[][] values) {
        return new IntGrid(
                Arrays.stream(values)
                        .map(row -> Arrays.stream(row).mapToInt(Integer::parseInt).toArray())
                        .toArray(int[][]::new));
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
