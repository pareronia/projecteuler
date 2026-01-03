package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class Problem0055 extends ProblemBase<Long, Long> {

    private Problem0055() {}

    public static Problem0055 create() {
        return new Problem0055();
    }

    @Override
    public Long solve(final Long input) {
    	return LongStream.range(0, input).filter(this::isLychrel).count();
    }

    private boolean isLychrel(final long n) {
		StringBuilder num = new StringBuilder(String.valueOf(n));
		for (int i = 0; i < 50; i++) {
			final BigInteger sum = new BigInteger(num.toString())
									.add(new BigInteger(num.reverse().toString()));
			final StringBuilder newNum = new StringBuilder(sum.toString());
			if (newNum.toString().equals(newNum.reverse().toString())) {
				return false;
			} else {
				num = new StringBuilder(newNum.reverse().toString());
			}
		}
		return true;
    }

    public static void main(final String[] args) {
        lap("10000", () -> Problem0055.create().solve(10_000L));
    }
}
