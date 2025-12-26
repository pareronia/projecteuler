package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

public class Problem0092 extends ProblemBase<Long, Long> {

    private Problem0092() {
    }

    public static Problem0092 create() {
        return new Problem0092();
    }

    @Override
    public Long solve(final Long input) {
    	long ans = 0;
    	for (int i = 2; i < input; i++) {
    		int n = i;
    		while (true) {
    			int ss = 0;
    			while (n > 0) {
    				final int d = n % 10;
    				ss += d * d;
    				n /= 10;
    			}
    			if (ss == 89) {
    				ans++;
    				break;
    			}
    			if (ss == 1) {
    				break;
    			}
    			n = ss;
    		}
		}
    	return ans;
    }

    public static void main(final String[] args) {
        lap("1E7", () -> Problem0092.create().solve(10_000_000L));
    }
}
