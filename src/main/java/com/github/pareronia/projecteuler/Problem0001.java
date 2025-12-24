package com.github.pareronia.projecteuler;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.LongStream;
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
        return LongStream.range(0, this.input)
                .parallel()
                .filter(i -> i % FIRST == 0 || i % SECOND == 0)
                .sum();
    }

    @Override
    public Long solve() {
        final Deque<Integer> threes = new ArrayDeque<>();
        threes.push(FIRST);
        while (threes.peek() + FIRST < this.input) {
            threes.push(threes.peek() + FIRST);
        }
        final Deque<Integer> fives = new ArrayDeque<>();
        fives.push(SECOND);
        while (fives.peek() + SECOND < this.input) {
            fives.push(fives.peek() + SECOND);
        }
        return Stream.concat(threes.stream(), fives.stream())
                .distinct()
                .mapToLong(Integer::longValue)
                .sum();
    }

    public static void main(final String[] args) {
        assert Problem0001.create(10).solve() == 23;
        assert Problem0001.create(10).solveAlt() == 23;
        assert Problem0001.create(10).solveOverview() == 23;
        assert Problem0001.create(1_000).solve() == 233_168;
        assert Problem0001.create(1_000).solveAlt() == 233_168;
        assert Problem0001.create(1_000_000).solveAlt() == 233_333_166_668L;
        assert Problem0001.create(1_000_000).solveOverview() == 233_333_166_668L;

        lap("1E3", () -> Problem0001.create(1_000).solve());
        lap("1E3 alt", () -> Problem0001.create(1_000).solveAlt());
        lap("1E3 overview", () -> Problem0001.create(1_000).solveOverview());
        lap("1E6", () -> Problem0001.create(1_000_000).solve());
        lap("1E9 alt", () -> Problem0001.create(1_000_000_000).solveAlt());
        lap("1E9 overview", () -> Problem0001.create(1_000_000_000).solveOverview());
    }
}
