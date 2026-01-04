package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.stream.LongStream;

import com.github.pareronia.projecteuler.ProblemBase.NoInput;
import com.github.pareronia.projecteuler.math.Factorials;
import com.github.pareronia.projecteuler.util.NumberUtils;
import com.github.pareronia.projecteuler.util.NumberUtils.DigitsIterator;

public class Problem0034 extends ProblemBase<NoInput, Long> {

    private Problem0034() {}

    public static Problem0034 create() {
        return new Problem0034();
    }

    @Override
    public Long solve(final NoInput _input) {
    	return LongStream.range(3, 7 * Factorials.get(9).longValue())
    			.parallel()
    			.filter(this::test)
    			.sum();
    }

    private boolean test(final long n) {
    	long sum = 0L;
    	final DigitsIterator digits = NumberUtils.digitsIterator(n);
    	while (digits.hasNext()) {
    		sum += Factorials.get(digits.next()).longValue();
    	}
    	return sum == n;
    }

    public static void main(final String[] args) {
        lap("", () -> Problem0034.create().solve(null));
    }
}
