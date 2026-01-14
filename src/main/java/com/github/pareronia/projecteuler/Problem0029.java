package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Problem0029 extends ProblemBase<Long, Long> {

    private static final Set<Long> SQUARES = Set.of(4L, 9L, 16L, 25L, 36L, 49L, 64L, 81L, 100L);
    private static final Set<Long> CUBES = Set.of(8L, 27L, 64L);
    private static final Set<Long> POWER4S = Set.of(16L, 81L);
    private static final Set<Long> POWER5S = Set.of(32L);
    private static final Set<Long> POWER6S = Set.of(64L);

    private Problem0029() {
    }

    public static Problem0029 create() {
        return new Problem0029();
    }

    @Override
    public Long solve(final Long input) {
        final Set<BigInteger> ans = new HashSet<>();
        for (int i = 2; i <= input; i++) {
            final BigInteger a = BigInteger.valueOf(i);
            for (int j = 2; j <= input; j++) {
                ans.add(a.pow(j));
            }
        }
        return (long) ans.size();
    }
    
    private long solveBis(final long input) {
        long ans = (input - 1) * (input - 1);
        ans -= SQUARES.stream().filter(x -> x <= input).mapToLong(x -> input / 2 - 1).sum();
        ans -= CUBES.stream().filter(x -> x <= input).mapToLong(x -> input / 3 - 1).sum();
        ans -= POWER4S.stream().filter(x -> x <= input).mapToLong(x -> input / 4 - 1).sum();
        ans -= POWER5S.stream().filter(x -> x <= input).mapToLong(x -> input / 5 - 1).sum();
        ans -= POWER6S.stream().filter(x -> x <= input).mapToLong(x -> input / 6 - 1).sum();
        return ans;
    }

    public static void main(final String[] args) {
    	assert Problem0029.create().solveBis(5L) == 15L;
    	assert Problem0029.create().solveBis(6L) == 23L;
    	assert Problem0029.create().solveBis(100L) == 9182L;
    	assert Problem0029.create().solve(5L) == 15L;
        lap("100", () -> Problem0029.create().solve(100L));
    }
}
