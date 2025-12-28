package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Problem0001 extends ProblemBase<Long, Long> {

    private static final int SECOND = 5;
    private static final int FIRST = 3;

    private Problem0001() {
    }

    public static Problem0001 create() {
        return new Problem0001();
    }

    private long sumDivisibleBy(final long target, final long divisor) {
        final long temp = (target - 1) / divisor;
        return divisor * (temp * (temp + 1)) / 2L;
    }

    public long solveOverview(final long input) {
        return sumDivisibleBy(input, FIRST)
                + sumDivisibleBy(input, SECOND)
                - sumDivisibleBy(input, FIRST * SECOND);
    }

    public long solveAlt(final long input) {
        return LongStream.range(0, input)
                .parallel()
                .filter(i -> i % FIRST == 0 || i % SECOND == 0)
                .sum();
    }

    private long solveInitial(final long input) {
        final Deque<Integer> threes = new ArrayDeque<>();
        threes.push(FIRST);
        while (threes.peek() + FIRST < input) {
            threes.push(threes.peek() + FIRST);
        }
        final Deque<Integer> fives = new ArrayDeque<>();
        fives.push(SECOND);
        while (fives.peek() + SECOND < input) {
            fives.push(fives.peek() + SECOND);
        }
        return Stream.concat(threes.stream(), fives.stream())
                .distinct()
                .mapToLong(Integer::longValue)
                .sum();
    }

    @Override
	public Long solve(final Long input) {
		return solveOverview(input);
	}

    public static void main(final String[] args) {
        assert Problem0001.create().solveInitial(10L) == 23;
        assert Problem0001.create().solveAlt(10) == 23;
        assert Problem0001.create().solveOverview(10) == 23;
        assert Problem0001.create().solveInitial(1_000L) == 233_168;
        assert Problem0001.create().solveAlt(1_000) == 233_168;
        assert Problem0001.create().solveAlt(1_000_000) == 233_333_166_668L;
        assert Problem0001.create().solveOverview(1_000_000) == 233_333_166_668L;

        lap("1E3", () -> Problem0001.create().solveInitial(1_000L));
        lap("1E3 alt", () -> Problem0001.create().solveAlt(1_000));
        lap("1E3 overview", () -> Problem0001.create().solveOverview(1_000));
        lap("1E6", () -> Problem0001.create().solveInitial(1_000_000L));
        lap("1E9 alt", () -> Problem0001.create().solveAlt(1_000_000_000));
        lap("1E9 overview", () -> Problem0001.create().solveOverview(1_000_000_000));
    }
}
