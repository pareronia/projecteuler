package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;
import static org.apache.commons.math3.util.CombinatoricsUtils.combinationsIterator;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Problem0009 extends ProblemBase<Long, Long> {

    private Problem0009() {
    }

    public static Problem0009 create() {
        return new Problem0009();
    }

    @Override
    public Long solve(final Long input) {
        final List<Integer> numbers = Stream.iterate(3, i -> i + 1).limit(input - 2).toList();
        final Iterator<int[]> combinations = combinationsIterator(numbers.size(), 3);
        while (combinations.hasNext()) {
            final int[] comb = combinations.next();
            final long a = numbers.get(comb[0]).longValue();
            final long b = numbers.get(comb[1]).longValue();
            final long c = numbers.get(comb[2]).longValue();
            if (a + b + c == input && a * a + b * b == c * c) {
//                System.out.println(List.of(a, b, c));
                return a * b * c;
            }
        }
        throw new IllegalStateException("Unsolvable");
    }

    public static void main(final String[] args) {
        assert Problem0009.create().solve(12L) == 60;

        lap("1000", () -> Problem0009.create().solve(1_000L));
        lap("2000", () -> Problem0009.create().solve(2_000L));
        lap("3000", () -> Problem0009.create().solve(3_000L));
    }
}
