package com.github.pareronia.projecteuler;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Problem0030 extends ProblemBase {

    private final transient Integer input;

    private Problem0030(final Integer input) {
        this.input = input;
    }

    public static Problem0030 create(final Integer input) {
        return new Problem0030(input);
    }

    @Override
    public Long solve() {
        final int exp = this.input;
        int limit = 0;
        while (true) {
            limit++;
            final int na = IntStream.range(0, limit).map(i -> 9 * (int) Math.pow(10, i)).sum();
            final int ps = limit * (int) Math.pow(9, exp);
            if (na > ps) {
                break;
            }
        }
        return LongStream.range(2, (int) Math.pow(10, limit))
                .parallel()
                .filter(
                        i -> {
                            long num = i;
                            int ps = 0;
                            while (num > 0) {
                                ps += (int) Math.pow(num % 10, exp);
                                num /= 10;
                            }
                            return ps == i;
                        })
                .sum();
    }

    public static void main(final String[] args) {
        assert Problem0030.create(4).solve() == 19316;
        lap("5", () -> Problem0030.create(5).solve());
        lap("6", () -> Problem0030.create(6).solve());
    }
}
