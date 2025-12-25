package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Problem0030 extends ProblemBase<Long, Long> {

    private Problem0030() {
    }

    public static Problem0030 create() {
        return new Problem0030();
    }

    @Override
    public Long solve(final Long input) {
        final int exp = input.intValue();
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
        assert Problem0030.create().solve(4L) == 19316;
        lap("5", () -> Problem0030.create().solve(5L));
        lap("6", () -> Problem0030.create().solve(6L));
    }
}
