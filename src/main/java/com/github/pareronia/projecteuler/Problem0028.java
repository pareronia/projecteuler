package com.github.pareronia.projecteuler;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.LongStream;

import com.github.pareronia.projecteuler.geometry.Direction;
import com.github.pareronia.projecteuler.geometry.Position;
import com.github.pareronia.projecteuler.grid.Cell;
import com.github.pareronia.projecteuler.grid.IntGrid;

public class Problem0028 extends ProblemBase {

    private final transient Integer input;

    private Problem0028(final Integer input) {
        this.input = input;
    }

    public static Problem0028 create(final Integer input) {
        return new Problem0028(input);
    }

    @Override
    public Long solve() {
        final int n = this.input;
        final int[][] values = new int[n][n];
        values[n / 2][n / 2] = 1;
        int i = 2;
        final CoordinateSupplier coords = new CoordinateSupplier();
        while (i <= n * n) {
            final Position nxt = coords.get();
            values[nxt.y() + n / 2][nxt.x() + n / 2] = i;
            i++;
        }
        final IntGrid grid = IntGrid.from(values);
        return getDiagonalDownValues(grid).sum() + getDiagonalUpValues(grid).sum() - 1;
    }

    private LongStream getDiagonalUpValues(final IntGrid grid) {
        return grid.getDiagonalUpValues(Cell.at(grid.getHeight(), -1))
                .mapToLong(Integer::longValue);
    }

    private LongStream getDiagonalDownValues(final IntGrid grid) {
        return grid.getDiagonalDownValues(Cell.at(-1, -1)).mapToLong(Integer::longValue);
    }

    public static void main(final String[] args) {
        assert Problem0028.create(5).solve() == 101;
        lap("1001", () -> Problem0028.create(1001).solve());
    }

    record DirectionAndPeriod(Direction direction, int period) {
        public static DirectionAndPeriod of(final Direction direction, final int period) {
            return new DirectionAndPeriod(direction, period);
        }
    }

    private static class CoordinateSupplier implements Supplier<Position> {
        private final Function<Integer, DirectionAndPeriod> directionsAndPeriods =
                new Function<>() {
                    private final List<Direction> directions =
                            List.of(
                                    Direction.RIGHT, Direction.DOWN,
                                    Direction.LEFT, Direction.UP);
                    private final int[] periods = {1, 1, 2, 2};

                    @Override
                    public DirectionAndPeriod apply(final Integer t) {
                        final int idx = t % 4;
                        final int period = periods[idx];
                        periods[idx] = period + 2;
                        final Direction direction = directions.get(idx);
                        return DirectionAndPeriod.of(direction, period);
                    }
                };
        private int x;
        private int y;
        private int k;
        private DirectionAndPeriod directionAndPeriod = directionsAndPeriods.apply(k);
        private int j;

        @Override
        public Position get() {
            if (j == directionAndPeriod.period) {
                k++;
                directionAndPeriod = directionsAndPeriods.apply(k);
                j = 0;
            }
            x += directionAndPeriod.direction.getX();
            y += directionAndPeriod.direction.getY();
            j++;
            return Position.of(x, y);
        }
    }
}
