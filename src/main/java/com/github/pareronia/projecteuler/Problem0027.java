package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import com.github.pareronia.projecteuler.math.Primes;

public class Problem0027 extends ProblemBase<Long, Long> {

    private Problem0027() {}

    public static Problem0027 create() {
        return new Problem0027();
    }

    @Override
    public Long solve(final Long input) {
        final long[] bs = Primes.primesUpTo(input);
        final int limit =
                ((input.intValue() - 1) & 1) == 0 ? input.intValue() - 2 : input.intValue() - 1;
        int best = 0;
        long ans = 0;
        for (long a = -limit; a <= limit; a += 2) {
            for (final long b : bs) {
                final int p = this.consecutivePrimes(a, b);
                if (p > best) {
                    best = p;
                    ans = a * b;
                }
            }
        }
        return ans;
    }

    private int consecutivePrimes(final long a, final long b) {
        int ans = 0;
        int n = 0;
        while (true) {
            if (Primes.isPrime((n + a) * n + b)) {
                ans++;
            } else {
                break;
            }
            n++;
        }
        return ans;
    }

    public static void main(final String[] args) {
        lap("1000", () -> Problem0027.create().solve(1000L));
    }
}
