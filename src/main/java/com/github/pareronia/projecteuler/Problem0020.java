package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.stream.IntStream;

import com.github.pareronia.projecteuler.math.Factorials;

public class Problem0020 extends ProblemBase<Long, Long> {

    private Problem0020() {
    }

    public static Problem0020 create() {
        return new Problem0020();
    }

    @Override
    public Long solve(final Long input) {
        final String s = Factorials.get(input).toString();
        return IntStream.range(0, s.length()).mapToLong(i -> Long.parseLong(s, i, i + 1, 10)).sum();
    }

    public static void main(final String[] args) {
        assert Problem0020.create().solve(10L) == 27;
        lap("100", () -> Problem0020.create().solve(100L));
    }
}
