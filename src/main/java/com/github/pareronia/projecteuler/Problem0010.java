package com.github.pareronia.projecteuler;

import java.util.stream.LongStream;

import com.github.pareronia.projecteuler.math.Primes;

public class Problem0010 extends ProblemBase {

    private final transient Integer input;

    private Problem0010(final Integer input) {
        this.input = input;
    }

    public static Problem0010 create(final Integer input) {
        return new Problem0010(input);
    }

    @Override
    public Long solve() {
        return 2
                + LongStream.iterate(3, i -> i <= this.input, i -> i + 2)
                        .parallel()
                        .filter(Primes::isPrime)
                        .sum();
    }

    public static void main(final String[] args) {
        assert Problem0010.create(10).solve().intValue() == 17;

        lap("2E6", () -> Problem0010.create(2_000_000).solve());
    }
}
