package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import com.github.pareronia.projecteuler.geometry.Position;

public class Problem0091 extends ProblemBase<Long, Long> {

    private Problem0091() {
    }

    public static Problem0091 create() {
        return new Problem0091();
    }

    @Override
	public Long solve(final Long input) {
        final List<Position> positions = IntStream.rangeClosed(0, input.intValue()).boxed()
                .flatMap(x -> IntStream.rangeClosed(0, input.intValue())
                                .filter(y -> x != 0 || y != 0)
                                .mapToObj(y -> Position.of(x, y)))
                .toList();
        return IntStream.range(0, positions.size())
                .parallel()
                .mapToLong(i -> IntStream.range(i + 1, positions.size())
                                .filter(j -> this.check(positions.get(i), positions.get(j)))
                                .count())
                .sum();
	}

    private boolean check(final Position first, final Position second) {
        final long[] sides = {
                first.distanceSquaredTo(Position.ORIGIN),
                second.distanceSquaredTo(Position.ORIGIN),
                first.distanceSquaredTo(second)
        };
        Arrays.sort(sides);
        return sides[2] == sides[0] + sides[1];
    }

    public static void main(final String[] args) {
        assert Problem0091.create().solve(2L) == 14L;
        lap("50", () -> Problem0091.create().solve(50L));
    }
}
