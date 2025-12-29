package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import com.github.pareronia.projecteuler.ProblemBase.NoInput;
import com.github.pareronia.projecteuler.math.Primes;

import java.util.stream.LongStream;

public class Problem0046 extends ProblemBase<NoInput, Long> {

    private Problem0046() {}

    public static Problem0046 create() {
        return new Problem0046();
    }

    @Override
    public Long solve(final NoInput _input) {
        return LongStream.iterate(35, i -> i + 2)
                .filter(i -> !Primes.isPrime(i))
                .filter(this::test)
                .findFirst()
                .orElseThrow();
    }

    private boolean test(final long num) {
        return LongStream.range(1, (long) Math.sqrt(num))
                .map(n -> 2 * n * n)
                .noneMatch(n2 -> Primes.isPrime(num - n2));
    }

    public static void main(final String[] args) {
        lap("", () -> Problem0046.create().solve(null));
    }
}
