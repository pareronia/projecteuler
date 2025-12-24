package com.github.pareronia.projecteuler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Problem0067 extends ProblemBase {

    private final transient int[][] input;

    private Problem0067(final Stream<String> input) {
        this.input =
                input.map(
                                row ->
                                        Arrays.stream(row.split(" "))
                                                .mapToInt(Integer::parseInt)
                                                .toArray())
                        .toArray(int[][]::new);
    }

    public static Problem0067 create(final Stream<String> input) {
        return new Problem0067(input);
    }

    @Override
    public Long solve() {
        return new MaxPathSum(this.input).get(0, 0);
    }

    public static void main(final String[] args) {
        final String test =
                """
                3
                7 4
                2 4 6
                8 5 9 3
                """;
        assert Problem0067.create(Arrays.stream(test.split("\\r?\\n"))).solve() == 23;
        lap("triangle.txt", () -> Problem0067.create(lines("0067_triangle.txt")).solve());
    }

    private static final class MaxPathSum {
        private final Map<Integer, Long> cache = new HashMap<>();
        private final int[][] triangle;

        public MaxPathSum(final int[][] triangle) {
            assert triangle.length < 1000;
            this.triangle = triangle;
        }

        public long get(final int row, final int col) {
            final int key = 1000 * row + col;
            if (this.cache.containsKey(key)) {
                return this.cache.get(key);
            }
            long ans;
            final int val = this.triangle[row][col];
            if (row == this.triangle.length - 1) {
                ans = val;
            } else {
                ans = val + Math.max(this.get(row + 1, col), this.get(row + 1, col + 1));
            }
            this.cache.put(key, ans);
            return ans;
        }
    }
}
