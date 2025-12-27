package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import com.github.pareronia.projecteuler.math.Primes;

public class Problem0007 extends ProblemBase<Long, Long> {

    private Problem0007() {}

    public static Problem0007 create() {
        return new Problem0007();
    }

    @Override
    public Long solve(final Long input) {
        return Primes.PRIMES.stream().sorted().skip(input - 1).findFirst().orElseThrow();
    }

    public static void main(final String[] args) {
        assert Problem0007.create().solve(6L) == 13;

        lap("10001", () -> Problem0007.create().solve(10_001L));
        lap("1E5", () -> Problem0007.create().solve(100_000L));
    }
}
