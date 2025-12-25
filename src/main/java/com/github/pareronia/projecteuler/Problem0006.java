package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.math.BigInteger;

public class Problem0006 extends ProblemBase<Long, BigInteger> {
    
    private Problem0006() {
	}

    public static Problem0006 create() {
    	return new Problem0006();
    }
    
    public BigInteger solveOverview(final long input) {
        final BigInteger sumOfSquares = BigInteger.valueOf(input)
                .multiply(BigInteger.TWO).add(BigInteger.ONE)
                .multiply(BigInteger.valueOf(input + 1))
                .multiply(BigInteger.valueOf(input))
                .divide(BigInteger.valueOf(6L));
        final BigInteger squareOfSum = BigInteger.valueOf(input)
                .multiply(BigInteger.valueOf(input + 1)).divide(BigInteger.TWO)
                .pow(2);
        return squareOfSum.subtract(sumOfSquares);
    }
    
	private BigInteger solveInitial(final Long input) {
		BigInteger sumOfSquares = BigInteger.ZERO;
        for (int i = 1; i <= input; i++) {
            sumOfSquares = sumOfSquares.add(BigInteger.valueOf(i).pow(2));
        }
        final BigInteger squareOfSum = BigInteger.valueOf(input)
                .multiply(BigInteger.valueOf(input + 1)).divide(BigInteger.TWO)
                .pow(2);
        return squareOfSum.subtract(sumOfSquares);
	}

    @Override
    public BigInteger solve(final Long input) {
        return solveOverview(input);
    }

    public static void main(final String[] args) {
        assert Problem0006.create().solveInitial(10L).intValue() == 2_640;
        assert Problem0006.create().solveInitial(100L).intValue() == 25_164_150;
        
        lap("100", () -> Problem0006.create().solveInitial(100L));
        lap("1E5", () -> Problem0006.create().solveInitial(100_000L));
        lap("1E8", () -> Problem0006.create().solveInitial(100_000_000L));
        lap("[overview] 100", () -> Problem0006.create().solveOverview(100L));
        lap("[overview] 1E5", () -> Problem0006.create().solveOverview(100_000L));
        lap("[overview] 1E8", () -> Problem0006.create().solveOverview(100_000_000L));
        lap("[overview] 1E11", () -> Problem0006.create().solveOverview(100_000_000_000L));
    }
}
