package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import com.github.pareronia.projecteuler.math.Factorials;

public class Problem0015 extends ProblemBase<Long, Long> {

    private Problem0015() {
    }

    public static Problem0015 create() {
        return new Problem0015();
    }

    public long solveRecursive(final long input) {
        return new Recursive().count((int) input);
    }

    public long solveIterative(final long input) {
        return new Iterative((int) input).count();
    }

    public long solveOverview(final long input) {
        return LongStream.rangeClosed(1, input)
                .reduce(1L, (acc, n) -> acc * (input + n) / n);
    }

    @Override
    public Long solve(final Long input) {
        return Factorials.get(2 * input).divide(Factorials.get(input).pow(2)).longValue();
    }

    public static void main(final String[] args) {
        assert Problem0015.create().solve(2L) == 6;
        assert Problem0015.create().solveRecursive(2) == 6;
        assert Problem0015.create().solveIterative(2) == 6;
        assert Problem0015.create().solveOverview(2) == 6;
        lap("20", () -> Problem0015.create().solve(20L));
        lap("20 [recursive]", () -> Problem0015.create().solveRecursive(20));
        lap("20 [iterative]", () -> Problem0015.create().solveIterative(20));
        lap("20 [overview]", () -> Problem0015.create().solveOverview(20));
    }

    private static final class Recursive {
        private final Map<Integer, Long> cache = new HashMap<>();

        public long count(final int size) {
            assert size < 100;
            return this.count(size, size);
        }

        private long count(final int m, final int n) {
            if (m == 0 || n == 0) {
                return 1;
            }
            final int key = 100 * m + n;
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            final long ans = count(m - 1, n) + count(m, n - 1);
            cache.put(key, ans);
            return ans;
        }
    }

    private static final class Iterative {
        private final long[][] dp;

        public Iterative(final int size) {
            this.dp = new long[size + 1][size + 1];
            IntStream.rangeClosed(0, size).forEach(m -> this.dp[m][0] = 1);
            IntStream.rangeClosed(0, size).forEach(n -> this.dp[0][n] = 1);
        }

        public long count() {
            for (int m = 1; m < this.dp.length; m++) {
                for (int n = 1; n < this.dp[m].length; n++) {
                    this.dp[m][n] = this.dp[m - 1][n] + this.dp[m][n - 1];
                }
            }
            return this.dp[this.dp.length - 1][this.dp[0].length - 1];
        }
    }
}
