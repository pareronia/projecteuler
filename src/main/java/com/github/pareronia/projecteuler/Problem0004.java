package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.math.Primes.isPrime;
import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

public class Problem0004 extends ProblemBase<Long, Long> {
    
    private Problem0004() {
	}

	public static Problem0004 create() {
    	return new Problem0004();
    }
    
    private boolean isPalindrome(final Long number) {
        final String string = number.toString();
        for (int i = 0; i < string.length() / 2; i++) {
            if (string.charAt(i) != string.charAt(string.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public Long solve(final Long input) {
        if (input > 5) {
            throw new UnsupportedOperationException("Expected <= 5");
        }
        final int max = (int) (Math.pow(10, input) - 1);
        final int min = (int) Math.pow(10, input - 1);
        for (int p = max * max; p >= min * min; p--) {
            if (isPalindrome((long) p) && !isPrime((long) p)) {
                for (int d = max; d >= min; d--) {
                    final int other = p / d;
                    if (p % d == 0 && other <= max) {
                        return (long) p;
                    }
                }
            }
        }
        throw new IllegalStateException("Unsolvable");
    }

    public static void main(final String[] args) {
        assert Problem0004.create().solve(2L) == 9_009;
        assert Problem0004.create().solve(3L) == 906_609;
        
        lap("3", () -> Problem0004.create().solve(3L));
        lap("4", () -> Problem0004.create().solve(4L));
        lap("5", () -> Problem0004.create().solve(5L));
    }
}
