package com.github.pareronia.projecteuler;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "create")
public class Problem0002 extends ProblemBase {
    
    private final transient Long input;
    
    public Long solveOverview() {
        long sum = 2;
        long termN_2 = 0;
        long termN_1 = 2;
        long f = 4 * termN_1 + termN_2;
        while (f < this.input) {
            sum += f;
            termN_2 = termN_1;
            termN_1 = f;
            f = 4 * termN_1 + termN_2;
        }
        return sum;
    }
    
    public Long solveAlt() {
        long sum = 0;
        long termN_2 = 1;
        long termN_1 = 1;
        long f = termN_2 + termN_1;
        while (f < this.input) {
            sum += f;
            termN_2 = termN_1 + f;
            termN_1 = f + termN_2;
            f = termN_2 + termN_1;
        }
        return sum;
    }
    
    @Override
    public Long solve() {
        long sum = 0;
        long termN_2 = 0;
        long termN_1 = 1;
        long f = 1;
        while (f < this.input) {
            if (f % 2 == 0) {
                sum += f;
            }
            termN_2 = termN_1;
            termN_1 = f;
            f = termN_2 + termN_1;
        }
        return sum;
    }

    public static void main(final String[] args) {
        assert Problem0002.create(100L).solve() == 44;
        assert Problem0002.create(1_000L).solve() == 798;
        assert Problem0002.create(4_000_000L).solve() == 4_613_732;
        assert Problem0002.create(100L).solveAlt() == 44;
        assert Problem0002.create(1_000L).solveAlt() == 798;
        assert Problem0002.create(4_000_000L).solveAlt() == 4_613_732;
        assert Problem0002.create(100L).solveOverview() == 44;
        assert Problem0002.create(1_000L).solveOverview() == 798;
        assert Problem0002.create(4_000_000L).solveOverview() == 4_613_732;
        
        lap("4E6", () -> Problem0002.create(4_000_000L).solve());
        lap("4E6 alt", () -> Problem0002.create(4_000_000L).solveAlt());
        lap("4E6 overview", () -> Problem0002.create(4_000_000L).solveOverview());
        lap("1E18", () -> Problem0002.create((long) 1e18).solve());
        lap("1E18 alt", () -> Problem0002.create((long) 1e18).solveAlt());
        lap("1E18 overview", () -> Problem0002.create((long) 1e18).solveOverview());
    }
}
