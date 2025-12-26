package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import com.github.pareronia.projecteuler.math.Primes;

public class Problem0035 extends ProblemBase<Long, Long> {

    private Problem0035() {}

    public static Problem0035 create() {
        return new Problem0035();
    }

    @Override
    public Long solve(final Long input) {
        return 13
                + LongStream.range(100, input)
                        .parallel()
                        .filter(Primes::isPrime)
                        .filter(this::test)
                        .count();
    }

    private boolean test(final long num) {
        final List<Integer> digits = getDigits(num);
        if (digits.stream().anyMatch(d -> d == 5 || (d & 1) == 0)) {
            return false;
        }
        final long pow = (long) Math.pow(10, digits.size() - 1);
        long rot = num;
        for (int i = 0; i < digits.size() - 1; i++) {
            final long ones = rot % 10;
            rot = ones * pow + rot / 10;
            if (!Primes.isPrime(rot)) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> getDigits(final long num) {
        final List<Integer> digits = new ArrayList<>();
        long n = num;
        while (n > 0) {
            digits.add((int) n % 10);
            n /= 10;
        }
        return digits;
    }

    public static void main(final String[] args) {
        assert Problem0035.create().solve(100L) == 13;
        lap("1E6", () -> Problem0035.create().solve(1_000_000L));
    }
}
