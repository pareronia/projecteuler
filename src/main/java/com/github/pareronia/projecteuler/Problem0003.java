package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.math.Primes.isPrime;
import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

public class Problem0003 extends ProblemBase<Long, Long> {
    
    private Problem0003() {
	}

    public static Problem0003 create() {
    	return new Problem0003();
    }
    
    public long solveOverview(final long input) {
        long number = input;
        int lastFactor;
        if (number % 2 == 0) {
            lastFactor = 2;
            number /= 2;
            while (number % 2 == 0) {
                number /= 2;
            }
        } else {
            lastFactor = 1;
        }
        int factor = 3;
        long maxFactor = (long) Math.floor(Math.sqrt(number));
        while (number > 1 && factor <= maxFactor) {
            if (number % factor == 0) {
                lastFactor = factor;
                number /= factor;
                while (number % factor == 0) {
                    number /= factor;
                }
                maxFactor = (long) Math.floor(Math.sqrt(number));
            }
            factor += 2;
        }
        if (number == 1) {
            return lastFactor;
        } else {
            return number;
        }
    }
    
    @Override
    public Long solve(final Long input) {
    	return solveOverview(input);
    }

    private long solveInitial(final Long input) {
        if (isPrime(input)) {
            return input;
        }
        final long start = (long) Math.floor(Math.sqrt(input));
        for (long i = start; i >= 2; i--) {
            if (isPrime(i) && input % i == 0) {
                return i;
            }
        }
        throw new IllegalStateException("Unsolvable");
    }

    public static void main(final String[] args) {
        assert Problem0003.create().solveInitial(13_195L) == 29;
        assert Problem0003.create().solveInitial(100L) == 5;
//        assert Problem0003.create().solve(3 * 71L) == 71;
        assert Problem0003.create().solveOverview(13_195L) == 29;
        assert Problem0003.create().solveOverview(100L) == 5;
        assert Problem0003.create().solveOverview(3 * 71L) == 71;
        
        lap("600851475143", () -> Problem0003.create().solveInitial(600_851_475_143L));
        lap("600851475143 [overview]", () -> Problem0003.create().solve(600_851_475_143L));
    }
}
