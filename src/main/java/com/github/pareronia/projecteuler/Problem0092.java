package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.stream.IntStream;

public class Problem0092 extends ProblemBase<Long, Long> {

    private Problem0092() {}

    public static Problem0092 create() {
        return new Problem0092();
    }

    @Override
    public Long solve(final Long input) {
        return IntStream.range(2, input.intValue()).parallel().filter(this::test).count();
    }

    private boolean test(final int i) {
        int n = i;
        while (true) {
            int ss = 0;
            while (n > 0) {
                final int d = n % 10;
                ss += d * d;
                n /= 10;
            }
            if (ss == 89) {
                return true;
            }
            if (ss == 1) {
                return false;
            }
            n = ss;
        }
    }

    public static void main(final String[] args) {
        lap("1E7", () -> Problem0092.create().solve(10_000_000L));
    }
}
