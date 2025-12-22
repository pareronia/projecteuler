package com.github.pareronia.projecteuler;

import static java.util.stream.Collectors.summingLong;
import static java.util.stream.Collectors.toCollection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;

public class Problem0001 extends ProblemBase {
    
    private static final int SECOND = 5;
    private static final int FIRST = 3;
    
    private final transient Integer input;

    private Problem0001(final Integer input) {
    	this.input = input;
	}

	public static Problem0001 create(final Integer input) {
    	return new Problem0001(input);
    }

    private Long sumDivisibleBy(final Integer target, final Integer divisor) {
        final long temp = (target - 1) / divisor;
        return divisor * (temp * (temp + 1)) / 2L;
    }
    
    public Long solveOverview() {
        return sumDivisibleBy(this.input, FIRST)
                + sumDivisibleBy(this.input, SECOND)
                - sumDivisibleBy(this.input, FIRST * SECOND);
    }
    
    public Long solveAlt() {
        return Stream.iterate(0, i -> i < this.input, i -> i + 1)
                .filter(i -> i % FIRST == 0 || i % SECOND == 0)
                .collect(summingLong(Integer::valueOf));
    }
   
    @Override
    public Long solve() {
        final Stack<Integer> threes = new Stack<>();
        threes.push(FIRST);
        while (threes.peek() + FIRST < this.input) {
            threes.push(threes.peek() + FIRST);
        }
        final Stack<Integer> fives = new Stack<>();
        fives.push(SECOND);
        while (fives.peek() + SECOND < this.input) {
            fives.push(fives.peek() + SECOND);
        }
        final Set<Integer> multiples = new HashSet<>();
        Arrays.stream(threes.toArray(i -> new Integer[threes.size()]))
                .collect(toCollection(() -> multiples));
        Arrays.stream(fives.toArray(i -> new Integer[fives.size()]))
                .collect(toCollection(() -> multiples));
        return multiples.stream()
                .collect(summingLong(Integer::valueOf));
    }

    public static void main(final String[] args) {
        assert Problem0001.create(10).solve() == 23;
        assert Problem0001.create(10).solveAlt() == 23;
        assert Problem0001.create(10).solveOverview() == 23;
        assert Problem0001.create(1_000).solveAlt() == 233_168;
        assert Problem0001.create(1_000_000).solveAlt() == 233_333_166_668L;
        assert Problem0001.create(1_000_000).solveOverview() == 233_333_166_668L;
        
        lap("1E3", () -> Problem0001.create(1_000).solve());
        lap("1E3 alt", () -> Problem0001.create(1_000).solveAlt());
        lap("1E3 overview", () -> Problem0001.create(1_000).solveOverview());
        lap("1E9 alt", () -> Problem0001.create(1_000_000_000).solveAlt());
        lap("1E9 overview", () -> Problem0001.create(1_000_000_000).solveOverview());
    }
}
