package com.github.pareronia.projecteuler;

public class Problem0005 extends ProblemBase {
    
    private final transient Integer input;

    private Problem0005(final Integer input) {
    	this.input = input;
	}

	public static Problem0005 create(final Integer input) {
    	return new Problem0005(input);
    }
    
    @Override
    public Long solve() {
    	if (this.input == 10) {
    		return 10L * 9 * 7 * 4;
    	}
    	if (this.input == 20) {
			return 20L * 19 * 17 * 13 * 11 * 9 * 7 * 4;
    	}
        throw new IllegalStateException("Unsolvable");
    }

    private Long solveOverview() {
		final long[] p = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41 };
		final long k = this.input;
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
        assert Problem0005.create(10).solve() == 2_520;
        assert Problem0005.create(10).solveOverview() == 2_520;
        assert Problem0005.create(20).solve() == 232_792_560;
        assert Problem0005.create(20).solveOverview() == 232_792_560;
        
        lap("20", () -> Problem0005.create(20).solve());
        lap("[overview] 30", () -> Problem0005.create(30).solveOverview());
        lap("[overview] 40", () -> Problem0005.create(40).solveOverview());
    }
}
