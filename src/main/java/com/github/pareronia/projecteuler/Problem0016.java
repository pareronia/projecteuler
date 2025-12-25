package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Problem0016 extends ProblemBase<Long, Long> {

    private Problem0016() {
    }

    public static Problem0016 create() {
        return new Problem0016();
    }

    @Override
    public Long solve(final Long input) {
        final String s = BigInteger.TWO.pow(input.intValue()).toString();
        return IntStream.range(0, s.length()).mapToLong(i -> Long.parseLong(s, i, i + 1, 10)).sum();
    }

    public static void main(final String[] args) {
        assert Problem0016.create().solve(15L) == 26;
        lap("1000", () -> Problem0016.create().solve(1000L));
    }
}
