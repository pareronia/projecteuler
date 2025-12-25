package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

public class Problem0005 extends ProblemBase<Long, Long> {
    
    private Problem0005() {
	}

	public static Problem0005 create() {
    	return new Problem0005();
    }
    
    @Override
    public Long solve(final Long input) {
    	return solveOverview(input);
    }

	private Long solveInitial(final Long input) {
		if (input == 10) {
    		return 10L * 9 * 7 * 4;
    	}
    	if (input == 20) {
			return 20L * 19 * 17 * 13 * 11 * 9 * 7 * 4;
    	}
        throw new IllegalStateException("Unsolvable");
	}

    private Long solveOverview(final long input) {
		final long[] p = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41 };
		final long k = input;
		long N = 1;
		int i = 0;
		boolean check = true;
		final long limit = (long) Math.ceil(Math.sqrt(k));
		while (p[i] <= k) {
			int a = 1;
			if (check) {
				if (p[i] <= limit) {
					a = (int) Math.floor(Math.log(k) / Math.log(p[i]));
				} else {
					check = false;
				}
			}
			N = (long) (N * Math.pow(p[i], a));
			i++;
		}
		return N;
    }

    public static void main(final String[] args) {
        assert Problem0005.create().solveInitial(10L) == 2_520;
        assert Problem0005.create().solveOverview(10) == 2_520;
        assert Problem0005.create().solveInitial(20L) == 232_792_560;
        assert Problem0005.create().solveOverview(20) == 232_792_560;
        
        lap("20", () -> Problem0005.create().solveInitial(20L));
        lap("[overview] 30", () -> Problem0005.create().solveOverview(30));
        lap("[overview] 40", () -> Problem0005.create().solveOverview(40));
    }
}
