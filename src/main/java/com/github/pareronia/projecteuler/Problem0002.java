package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

public class Problem0002 extends ProblemBase<Long, Long> {

    private Problem0002() {}

    public static Problem0002 create() {
        return new Problem0002();
    }

    public long solveOverview(final long input) {
        long sum = 2;
        long termN_2 = 0;
        long termN_1 = 2;
        long f = 4 * termN_1 + termN_2;
        while (f < input) {
            sum += f;
            termN_2 = termN_1;
            termN_1 = f;
            f = 4 * termN_1 + termN_2;
        }
        return sum;
    }

    public long solveAlt(final long input) {
        long sum = 0;
        long termN_2 = 1;
        long termN_1 = 1;
        long f = termN_2 + termN_1;
        while (f < input) {
            sum += f;
            termN_2 = termN_1 + f;
            termN_1 = f + termN_2;
            f = termN_2 + termN_1;
        }
        return sum;
    }

	private Long solveInitial(final Long input) {
		long sum = 0;
        long termN_2 = 0;
        long termN_1 = 1;
        long f = 1;
        while (f < input) {
            if (f % 2 == 0) {
                sum += f;
            }
            termN_2 = termN_1;
            termN_1 = f;
            f = termN_2 + termN_1;
        }
        return sum;
	}

    @Override
    public Long solve(final Long input) {
        return solveOverview(input);
    }

    public static void main(final String[] args) {
        assert Problem0002.create().solveInitial(100L) == 44;
        assert Problem0002.create().solveInitial(1_000L) == 798;
        assert Problem0002.create().solveInitial(4_000_000L) == 4_613_732;
        assert Problem0002.create().solveAlt(100L) == 44;
        assert Problem0002.create().solveAlt(1_000L) == 798;
        assert Problem0002.create().solveAlt(4_000_000L) == 4_613_732;
        assert Problem0002.create().solveOverview(100L) == 44;
        assert Problem0002.create().solveOverview(1_000L) == 798;
        assert Problem0002.create().solveOverview(4_000_000L) == 4_613_732;

        lap("4E6", () -> Problem0002.create().solveInitial(4_000_000L));
        lap("4E6 alt", () -> Problem0002.create().solveAlt(4_000_000L));
        lap("4E6 overview", () -> Problem0002.create().solveOverview(4_000_000L));
        lap("1E18", () -> Problem0002.create().solveInitial((long) 1e18));
        lap("1E18 alt", () -> Problem0002.create().solveAlt((long) 1e18));
        lap("1E18 overview", () -> Problem0002.create().solveOverview((long) 1e18));
    }
}
