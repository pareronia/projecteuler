package com.github.pareronia.projecteuler;

public class Problem0005 extends ProblemBase {
    
    private final transient Integer input;

    private Problem0005(final Integer input) {
    	this.input = input;
	}

	public static Problem0005 create(final Integer input) {
    	return new Problem0005(input);
    }
    
    @Override
    public Long solve() {
        throw new IllegalStateException("Unsolvable");
    }

    public static void main(final String[] args) {
        assert Problem0005.create(10).solve() == 2_520;
        assert Problem0005.create(20).solve() == 232_792_560;
        
        lap("20", () -> Problem0005.create(20).solve());
    }
}
