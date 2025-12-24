package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.math.Primes.isPrime;

public class Problem0004 extends ProblemBase {
    
    private final transient Integer input;

    private Problem0004(final Integer input) {
    	this.input = input;
	}

	public static Problem0004 create(final Integer input) {
    	return new Problem0004(input);
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
    public Long solve() {
        if (this.input > 5) {
            throw new UnsupportedOperationException("Expected <= 5");
        }
        final int max = (int) (Math.pow(10, this.input) - 1);
        final int min = (int) Math.pow(10, this.input - 1);
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
        assert Problem0004.create(2).solve() == 9_009;
        assert Problem0004.create(3).solve() == 906_609;
        
        lap("3", () -> Problem0004.create(3).solve());
        lap("4", () -> Problem0004.create(4).solve());
        lap("5", () -> Problem0004.create(5).solve());
    }
}
