package com.github.pareronia.projecteuler;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.math3.util.CombinatoricsUtils.combinationsIterator;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Problem0009 extends ProblemBase {
    
    private final transient Integer input;

    private Problem0009(final Integer input) {
    	this.input = input;
	}

	public static Problem0009 create(final Integer input) {
    	return new Problem0009(input);
    }
    
    @Override
    public Long solve() {
        final List<Integer> numbers = Stream.iterate(3, i -> i + 1)
                .limit(this.input - 2)
                .collect(toList());
        final Iterator<int[]> combinations
                = combinationsIterator(numbers.size(), 3);
        while (combinations.hasNext()) {
            final int[] comb = combinations.next();
            final long a = numbers.get(comb[0]).longValue();
            final long b = numbers.get(comb[1]).longValue();
            final long c = numbers.get(comb[2]).longValue();
            if (a + b + c == this.input && a * a + b * b == c * c) {
                System.out.println(List.of(a, b, c));
                return a * b * c;
            }
        }
        throw new IllegalStateException("Unsolvable");
    }

    public static void main(final String[] args) {
        assert Problem0009.create(12).solve().intValue() == 60;
        
        lap("1000", () -> Problem0009.create(1_000).solve());
        lap("2000", () -> Problem0009.create(2_000).solve());
        lap("3000", () -> Problem0009.create(3_000).solve());
    }
}
