package com.github.pareronia.projecteuler;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "create")
public class Problem0005 extends ProblemBase {
    
    private final transient Integer input;
    
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
