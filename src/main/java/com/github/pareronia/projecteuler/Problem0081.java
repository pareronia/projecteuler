package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import com.github.pareronia.projecteuler.geometry.Direction;
import com.github.pareronia.projecteuler.grid.Cell;
import com.github.pareronia.projecteuler.grid.IntGrid;
import com.github.pareronia.projecteuler.util.ProblemUtils;
import com.github.pareronia.projecteuler.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalLong;
import java.util.stream.Stream;

public class Problem0081 extends ProblemBase<String, Long> {

    private Problem0081() {}

    public static Problem0081 create() {
        return new Problem0081();
    }

    @Override
    public Long solve(final String input) {
        final IntGrid grid = IntGrid.from(StringUtils.toIntMatrix(input));
        return new MinPathSum(grid).get(Cell.at(0, 0));
    }

    private static final class MinPathSum {
        private final IntGrid grid;
        private final Map<Cell, Long> cache;

        MinPathSum(final IntGrid grid) {
            this.grid = grid;
            this.cache = new HashMap<>();
        }

        private Stream<Cell> adjacent(final Cell cell) {
            return Stream.of(Direction.RIGHT, Direction.DOWN)
                    .map(cell::at)
                    .filter(grid::isInBounds);
        }

        public long get(final Cell start) {
            if (!this.cache.containsKey(start)) {
                final OptionalLong min = this.adjacent(start).mapToLong(this::get).min();
                this.cache.put(start, this.grid.getValue(start) + min.orElse(0L));
            }
            return this.cache.get(start);
        }
    }

    public static void main(final String[] args) {
        final String test =
                """
                131,673,234,103,18
                201,96,342,965,150
                630,803,746,422,111
                537,699,497,121,956
                805,732,524,37,331
                """;
        assert Problem0081.create().solve(test) == 2427;
        final String matrix = ProblemUtils.readString("0081_matrix.txt");
        lap("matrix.txt", () -> Problem0081.create().solve(matrix));
    }
}
