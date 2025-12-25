package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.stream.LongStream;

import com.github.pareronia.projecteuler.math.Primes;

public class Problem0010 extends ProblemBase<Long, Long> {

    private Problem0010() {
    }

    public static Problem0010 create() {
        return new Problem0010();
    }

    @Override
    public Long solve(final Long input) {
        return 2
                + LongStream.iterate(3, i -> i <= input, i -> i + 2)
                        .parallel()
                        .filter(Primes::isPrime)
                        .sum();
    }

    public static void main(final String[] args) {
        assert Problem0010.create().solve(10L) == 17;

        lap("2E6", () -> Problem0010.create().solve(2_000_000L));
    }
}
