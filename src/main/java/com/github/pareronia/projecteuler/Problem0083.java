package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import com.github.pareronia.projecteuler.geometry.Direction;
import com.github.pareronia.projecteuler.grid.Cell;
import com.github.pareronia.projecteuler.grid.IntGrid;
import com.github.pareronia.projecteuler.util.ProblemUtils;
import com.github.pareronia.projecteuler.util.StringUtils;

public class Problem0083 extends ProblemBase<String, Long> {

    private Problem0083() {}

    public static Problem0083 create() {
        return new Problem0083();
    }

    @Override
    public Long solve(final String input) {
        final IntGrid grid = IntGrid.from(StringUtils.toIntMatrix(input));
        final Map<Cell, Integer> distances = new MinPathSum(grid).getDistances(Cell.at(0, 0));
        return (long) distances.get(Cell.at(grid.getHeight() - 1, grid.getWidth() - 1));
    }

    private static final class MinPathSum {
        private final IntGrid grid;
    	
        MinPathSum(final IntGrid grid) {
            this.grid = grid;
        }

        public Map<Cell, Integer> getDistances(final Cell start) {
			final PriorityQueue<State> q = new PriorityQueue<>();
			q.add(new State(start, this.grid.getValue(start)));
			final Map<Cell, Integer> best = new HashMap<>();
			best.put(start, this.grid.getValue(start));
			while (!q.isEmpty()) {
				final State state = q.poll();
				final int cost = best.getOrDefault(state.cell, Integer.MAX_VALUE);
				Stream.of(Direction.RIGHT, Direction.DOWN, Direction.UP, Direction.LEFT)
						.map(state.cell::at)
						.filter(this.grid::isInBounds)
						.forEach(nxt -> {
							final int newCost = cost + this.grid.getValue(nxt);
							if (newCost < best.getOrDefault(nxt, Integer.MAX_VALUE)) {
								best.put(nxt, newCost);
								q.add(new State(nxt, newCost));
							}
						});
			}
			return best;
        }

		private record State(Cell cell, int cost) implements Comparable<State> {

			@Override
			public int compareTo(final State other) {
				return Integer.compare(this.cost, other.cost);
			}
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
        assert Problem0083.create().solve(test) == 2297;
        final String matrix = ProblemUtils.readString("0081_matrix.txt");
        lap("matrix.txt", () -> Problem0083.create().solve(matrix));
    }
}
