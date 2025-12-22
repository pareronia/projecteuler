package com.github.pareronia.projecteuler;

import java.math.BigInteger;

public class Problem0006 extends ProblemBase {
    
    private final transient Long input;
    
    private Problem0006(final Long input) {
		this.input = input;
	}

    public static Problem0006 create(final Long input) {
    	return new Problem0006(input);
    }
    
    public BigInteger solveOverview() {
        final BigInteger sumOfSquares = BigInteger.valueOf(this.input)
                .multiply(BigInteger.TWO).add(BigInteger.ONE)
                .multiply(BigInteger.valueOf(this.input + 1))
                .multiply(BigInteger.valueOf(this.input))
                .divide(BigInteger.valueOf(6L));
        final BigInteger squareOfSum = BigInteger.valueOf(this.input)
                .multiply(BigInteger.valueOf(this.input + 1)).divide(BigInteger.TWO)
                .pow(2);
        return squareOfSum.subtract(sumOfSquares);
    }
    
    @Override
    public BigInteger solve() {
        BigInteger sumOfSquares = BigInteger.ZERO;
        for (int i = 1; i <= this.input; i++) {
            sumOfSquares = sumOfSquares.add(BigInteger.valueOf(i).pow(2));
        }
        final BigInteger squareOfSum = BigInteger.valueOf(this.input)
                .multiply(BigInteger.valueOf(this.input + 1)).divide(BigInteger.TWO)
                .pow(2);
        return squareOfSum.subtract(sumOfSquares);
    }

    public static void main(final String[] args) {
        assert Problem0006.create(10L).solve().intValue() == 2_640;
        assert Problem0006.create(100L).solve().intValue() == 25_164_150;
        
        lap("100", () -> Problem0006.create(100L).solve());
        lap("1E5", () -> Problem0006.create(100_000L).solve());
        lap("1E8", () -> Problem0006.create(100_000_000L).solve());
        lap("[overview] 100", () -> Problem0006.create(100L).solveOverview());
        lap("[overview] 1E5", () -> Problem0006.create(100_000L).solveOverview());
        lap("[overview] 1E8", () -> Problem0006.create(100_000_000L).solveOverview());
        lap("[overview] 1E11", () -> Problem0006.create(100_000_000_000L).solveOverview());
    }
}
