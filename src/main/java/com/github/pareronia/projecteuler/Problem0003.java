package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.math.Primes.isPrime;

public class Problem0003 extends ProblemBase {
    
    private final transient Long input;
    
    private Problem0003(final Long input) {
		this.input = input;
	}

    public static Problem0003 create(final Long input) {
    	return new Problem0003(input);
    }
    
    public int solveOverview() {
        long number = this.input;
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
            return (int) number;
        }
    }
    
    @Override
    public Long solve() {
        if (isPrime(this.input)) {
            return this.input;
        }
        final long start = (long) Math.floor(Math.sqrt(this.input));
        for (long i = start; i >= 2; i--) {
            if (isPrime(i) && this.input % i == 0) {
                return i;
            }
        }
        throw new IllegalStateException("Unsolvable");
    }

    public static void main(final String[] args) {
        assert Problem0003.create(13_195L).solve() == 29;
        assert Problem0003.create(100L).solve() == 5;
//        assert Problem0003.create(3 * 71L).solve() == 71;
        assert Problem0003.create(13_195L).solveOverview() == 29;
        assert Problem0003.create(100L).solveOverview() == 5;
        assert Problem0003.create(3 * 71L).solveOverview() == 71;
        
        lap("600851475143", () -> Problem0003.create(600_851_475_143L).solve());
        lap("600851475143 [overview]", () -> Problem0003.create(600_851_475_143L).solveOverview());
    }
}
