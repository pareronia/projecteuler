package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.stream.LongStream;

public class Problem0045 extends ProblemBase<Long, Long> {

	private static final long LIMIT = 100_000;

    private Problem0045() {}

    public static Problem0045 create() {
        return new Problem0045();
    }

    @Override
    public Long solve(final Long input) {
    	for (long n = input + 1; n < LIMIT; n++) {
    		final long h = n * (2 * n - 1);
    		final double x = (1 + Math.sqrt(1 + 24 * h)) / 6;
    		if (x % 1 == 0) {
    			return h;
    		}
		}
    	throw new IllegalStateException("Unsolvable");
    }
    
    private long solveInitial(final Long input) {
    	return LongStream.iterate(input + 1, i -> i < LIMIT, i -> i+ 1)
    			.parallel()
    			.map(i -> i * (i + 1) / 2)
    			.filter(this::isHexagonal)
    			.filter(this::isPentagonal)
    			.findFirst()
    			.getAsLong();
    }

    private boolean isPentagonal(final long t) {
    	for (long n = 165; n < LIMIT; n++) {
			if (n * (3 * n - 1) / 2 == t) {
				return true;
			}
		}
    	return false;
    }

    private boolean isHexagonal(final long t) {
    	for (long n = 143; n < LIMIT; n++) {
    		if (n * (2 * n - 1) == t) {
    			return true;
    		}
    	}
    	return false;
    }

    public static void main(final String[] args) {
    	assert Problem0045.create().solve(142L) == 40755L;
    	lap("[initial] 285", () -> Problem0045.create().solveInitial(285L));
        lap("143", () -> Problem0045.create().solve(143L));
    }
}
