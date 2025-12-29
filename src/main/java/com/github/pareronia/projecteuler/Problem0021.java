package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.HashSet;
import java.util.Set;

import com.github.pareronia.projecteuler.math.Divisors;

public class Problem0021 extends ProblemBase<Long, Long> {

    private Problem0021() {}

    public static Problem0021 create() {
        return new Problem0021();
    }

    @Override
    public Long solve(final Long input) {
        long ans = 0;
        for (long a = 2; a < input; a++) {
            final long b = this.sumOfProperDivisors(a);
            if (a < b && this.sumOfProperDivisors(b) == a) {
                ans += a + b;
            }
        }
        return ans;
    }

    private long solveInitial(final Long input) {
        final Set<Long> amicable = new HashSet<>();
        for (long a = 2; a < input; a += 2) {
            final long b = this.sumOfProperDivisors(a);
            if (a == b) {
                continue;
            }
            final long d2 = this.sumOfProperDivisors(b);
            if (d2 == a) {
                amicable.add(a);
                amicable.add(b);
            }
        }
        return amicable.stream().mapToLong(Long::longValue).sum();
    }

    private long sumOfProperDivisors(final long num) {
        long ans = 0;
        for (final long d : Divisors.divisors(num)) {
            if (d != num) {
                ans += d;
            }
        }
        return ans;
    }

    public static void main(final String[] args) {
    	lap("10000", () -> Problem0021.create().solveInitial(10_000L));
        lap("[overview] 10000", () -> Problem0021.create().solve(10_000L));
    }
}
