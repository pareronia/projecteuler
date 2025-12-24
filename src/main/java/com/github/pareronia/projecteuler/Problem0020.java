package com.github.pareronia.projecteuler;

import java.util.stream.IntStream;

import com.github.pareronia.projecteuler.math.Factorials;

public class Problem0020 extends ProblemBase {

    private final transient Integer input;

    private Problem0020(final Integer input) {
        this.input = input;
    }

    public static Problem0020 create(final Integer input) {
        return new Problem0020(input);
    }

    @Override
    public Long solve() {
        final String s = Factorials.get(this.input).toString();
        return IntStream.range(0, s.length()).mapToLong(i -> Long.parseLong(s, i, i + 1, 10)).sum();
    }

    public static void main(final String[] args) {
        assert Problem0020.create(10).solve() == 27;
        lap("100", () -> Problem0020.create(100).solve());
    }
}
