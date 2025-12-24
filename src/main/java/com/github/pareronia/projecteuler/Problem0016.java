package com.github.pareronia.projecteuler;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Problem0016 extends ProblemBase {

    private final transient Integer input;

    private Problem0016(final Integer input) {
        this.input = input;
    }

    public static Problem0016 create(final Integer input) {
        return new Problem0016(input);
    }

    @Override
    public Long solve() {
        final String s = BigInteger.TWO.pow(this.input).toString();
        return IntStream.range(0, s.length()).mapToLong(i -> Long.parseLong(s, i, i + 1, 10)).sum();
    }

    public static void main(final String[] args) {
        assert Problem0016.create(15).solve() == 26;
        lap("1000", () -> Problem0016.create(1000).solve());
    }
}
