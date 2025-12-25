package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import com.github.pareronia.projecteuler.math.Fibonacci;
import com.github.pareronia.projecteuler.util.BinarySearch;

public class Problem0025 extends ProblemBase<Long, Long> {

    private Problem0025() {
    }

    public static Problem0025 create() {
        return new Problem0025();
    }

    @Override
    public Long solve(final Long input) {
        return 1
                + BinarySearch.search(
                        n -> Fibonacci.fib(n.intValue()).toString().length() < input);
    }

    public static void main(final String[] args) {
    	assert Problem0025.create().solve(3L) == 12;
        lap("1000", () -> Problem0025.create().solve(1000L));
    }
}
