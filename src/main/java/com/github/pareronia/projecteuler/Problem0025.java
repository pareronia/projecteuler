package com.github.pareronia.projecteuler;

import com.github.pareronia.projecteuler.math.Fibonacci;
import com.github.pareronia.projecteuler.util.BinarySearch;

public class Problem0025 extends ProblemBase {

    private final transient Integer input;

    private Problem0025(final Integer input) {
        this.input = input;
    }

    public static Problem0025 create(final Integer input) {
        return new Problem0025(input);
    }

    @Override
    public Long solve() {
        return 1
                + BinarySearch.search(
                        n -> Fibonacci.fib(n.intValue()).toString().length() < this.input);
    }

    public static void main(final String[] args) {
    	assert Problem0025.create(3).solve() == 12;
        lap("1000", () -> Problem0025.create(1000).solve());
    }
}
