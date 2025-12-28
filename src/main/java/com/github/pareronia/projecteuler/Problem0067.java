package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.HashMap;
import java.util.Map;

import com.github.pareronia.projecteuler.util.ProblemUtils;
import com.github.pareronia.projecteuler.util.StringUtils;

public class Problem0067 extends ProblemBase<String, Long> {

    private Problem0067() {
    }

    public static Problem0067 create() {
        return new Problem0067();
    }

    @Override
    public Long solve(final String input) {
        final int[][] triangle = StringUtils.toIntMatrix(input);
        return new MaxPathSum(triangle).get(0, 0);
    }

    public static void main(final String[] args) {
        final String test =
                """
                3
                7 4
                2 4 6
                8 5 9 3
                """;
        assert Problem0067.create().solve(test) == 23;
        final String triangle = ProblemUtils.readString("0067_triangle.txt");
        lap("triangle.txt", () -> Problem0067.create().solve(triangle));
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
