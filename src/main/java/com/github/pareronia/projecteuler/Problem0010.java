package com.github.pareronia.projecteuler;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "create")
public class Problem0010 extends ProblemBase {
    
    private final transient Integer input;
    
    @Override
    public Long solve() {
        return Stream.iterate(3, i -> i <= this.input, i -> i + 2)
                .map(Integer::longValue)
                .filter(Primes::isPrime)
                .reduce(2L, (a, b) -> a + b);
    }

    public static void main(final String[] args) {
        assert Problem0010.create(10).solve().intValue() == 17;
        
        lap("2E6", () -> Problem0010.create(2_000_000).solve());
    }
}
