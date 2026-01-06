package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.stream.LongStream;

import com.github.pareronia.projecteuler.math.Divisors;

public class Problem0012 extends ProblemBase<Long, Long> {

    private Problem0012() {
    }

    public static Problem0012 create() {
        return new Problem0012();
    }

    @Override
    public Long solve(final Long input) {
    	return LongStream.iterate(1, n -> n + 1)
    			.parallel()
    			.map(n -> n * (n + 1) / 2)
    			.filter(t -> Divisors.divisors(t).size() > input.intValue())
    			.findFirst()
    			.orElseThrow();
    }

    public static void main(final String[] args) {
    	assert Problem0012.create().solve(5L) == 28L;
        lap("500", () -> Problem0012.create().solve(500L));
    }
}
