package com.github.pareronia.projecteuler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import com.github.pareronia.projecteuler.math.Factorials;

public class Problem0015 extends ProblemBase {

    private final transient Integer input;

    private Problem0015(final Integer input) {
        this.input = input;
    }

    public static Problem0015 create(final Integer input) {
        return new Problem0015(input);
    }

    public long solveRecursive() {
        return new Recursive().count(this.input);
    }

    public long solveIterative() {
        return new Iterative(this.input).count();
    }

    public long solveOverview() {
        return LongStream.rangeClosed(1, this.input)
                .reduce(1L, (acc, n) -> acc * (this.input + n) / n);
    }

    @Override
    public Long solve() {
        return Factorials.get(2 * this.input).divide(Factorials.get(this.input).pow(2)).longValue();
    }

    public static void main(final String[] args) {
        assert Problem0015.create(2).solve() == 6;
        assert Problem0015.create(2).solveRecursive() == 6;
        assert Problem0015.create(2).solveIterative() == 6;
        assert Problem0015.create(2).solveOverview() == 6;
        lap("20", () -> Problem0015.create(20).solve());
        lap("20 [recursive]", () -> Problem0015.create(20).solveRecursive());
        lap("20 [iterative]", () -> Problem0015.create(20).solveIterative());
        lap("20 [overview]", () -> Problem0015.create(20).solveOverview());
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
