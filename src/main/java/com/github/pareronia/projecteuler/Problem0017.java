package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.HashMap;
import java.util.Map;

public class Problem0017 extends ProblemBase<Long, Long> {

    private static final Map<Integer, Integer> MAP = new HashMap<>();

    static {
        MAP.put(1, "one".length());
        MAP.put(2, "two".length());
        MAP.put(3, "three".length());
        MAP.put(4, "four".length());
        MAP.put(5, "five".length());
        MAP.put(6, "six".length());
        MAP.put(7, "seven".length());
        MAP.put(8, "eight".length());
        MAP.put(9, "nine".length());
        MAP.put(10, "ten".length());
        MAP.put(11, "eleven".length());
        MAP.put(12, "twelve".length());
        MAP.put(13, "thirteen".length());
        MAP.put(14, "fourteen".length());
        MAP.put(15, "fifteen".length());
        MAP.put(16, "sixteen".length());
        MAP.put(17, "seventeen".length());
        MAP.put(18, "eighteen".length());
        MAP.put(19, "nineteen".length());
        MAP.put(20, "twenty".length());
        MAP.put(30, "thirty".length());
        MAP.put(40, "forty".length());
        MAP.put(50, "fifty".length());
        MAP.put(60, "sixty".length());
        MAP.put(70, "seventy".length());
        MAP.put(80, "eighty".length());
        MAP.put(90, "ninety".length());
        MAP.put(100, "hundred".length());
        MAP.put(1000, "thousand".length());
    }

    private Problem0017() {
    }

    public static Problem0017 create() {
        return new Problem0017();
    }

    @Override
    public Long solve(final Long input) {
    	long ans = 0L;
    	for (int n = 1; n <= input; n++) {
    		ans += subSolve(n);
		}
        return ans;
    }

	private long subSolve(final int n) {
		assert n < 10_000;
		long ans = 0L;
		final int t = n / 1000;
		int m = n % 1000;
		if (t > 0) {
			ans += MAP.get(t) + MAP.get(1000);
			if (m > 0) {
				ans += "and".length();
			}
		}
		final int h = m / 100;
		m = m % 100;
		if (h > 0) {
			ans += MAP.get(h) + MAP.get(100);
			if (m > 0) {
				ans += "and".length();
			}
		}
		if (m > 20) {
			final int o = m % 10;
			ans += MAP.get(m - o) + MAP.getOrDefault(o, 0);
		} else {
			ans += MAP.getOrDefault(m, 0);
		}
		return ans;
	}

    public static void main(final String[] args) {
        assert Problem0017.create().solve(5L) == 19;
        assert Problem0017.create().subSolve(100) == 10;
        assert Problem0017.create().subSolve(342) == 23;
        assert Problem0017.create().subSolve(115) == 20;
        lap("1000", () -> Problem0017.create().solve(1000L));
    }
}
