package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.Arrays;

import com.github.pareronia.projecteuler.util.ProblemUtils;

public class Problem0018 extends ProblemBase<String, Long> {

    private Problem0018() {
    }

    public static Problem0018 create() {
        return new Problem0018();
    }

    private long maxPathSum(final int[][] triangle, final int row, final int col) {
        final int val = triangle[row][col];
        if (row == triangle.length - 1) {
            return val;
        }
        return val
                + Math.max(
                        this.maxPathSum(triangle, row + 1, col),
                        this.maxPathSum(triangle, row + 1, col + 1));
    }

    @Override
    public Long solve(final String input) {
        final int[][] triangle =
                Arrays.stream(input.split("\\r?\\n"))
                        .map(
                                row ->
                                        Arrays.stream(row.split(" "))
                                                .mapToInt(Integer::parseInt)
                                                .toArray())
                        .toArray(int[][]::new);

        return this.maxPathSum(triangle, 0, 0);
    }

    public static void main(final String[] args) {
        final String test =
                """
                3
                7 4
                2 4 6
                8 5 9 3
                """;
        assert Problem0018.create().solve(test) == 23;
        final String triangle = ProblemUtils.readString("0018_triangle.txt");
        lap("triangle.txt", () -> Problem0018.create().solve(triangle));
    }
}
