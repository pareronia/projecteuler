package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;
import static java.util.stream.Collectors.toSet;

import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import com.github.pareronia.projecteuler.ProblemBase.NoInput;
import com.github.pareronia.projecteuler.math.Divisors;
import com.github.pareronia.projecteuler.math.Primes;

public class Problem0023 extends ProblemBase<NoInput, Long> {

    private static final int LIMIT = 20161;

    private Problem0023() {}

    public static Problem0023 create() {
        return new Problem0023();
    }

    @Override
    public Long solve(final NoInput _input) {
        final long[] a = abundantNumbers();
        final Set<Long> lst = IntStream.range(0, a.length).boxed()
                .parallel()
                .flatMapToLong(i -> IntStream.range(i, a.length).mapToLong(j -> a[i] + a[j]).filter(n -> n <= LIMIT))
                .boxed().collect(toSet());
        return LongStream.rangeClosed(1, LIMIT)
                .parallel()
                .filter(n -> !lst.contains(n))
                .sum();
    }

    private long[] abundantNumbers() {
        return LongStream.rangeClosed(1, LIMIT)
                .filter(n -> !Primes.isPrime(n))
                .filter(n -> Divisors.divisors(n).stream()
                                .mapToLong(Long::longValue)
                                .filter(d -> d != n)
                                .sum()
                            > n)
                .toArray();
    }

    public static void main(final String[] args) {
        lap("", () -> Problem0023.create().solve(null));
    }
}
