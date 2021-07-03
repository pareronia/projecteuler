package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.Primes.isPrime;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "create")
public class Problem0007 extends ProblemBase {
    
    private final transient Integer input;
    
    @Override
    public Long solve() {
        if (this.input == 1) {
            return 2L;
        }
        if (this.input == 2) {
            return 3L;
        }
        int cnt = 2;
        long prime = 3L;
        while (cnt < this.input) {
            prime += 2;
            if (isPrime(prime)) {
                cnt++;
            }
        }
        return prime;
    }

    public static void main(final String[] args) {
        assert Problem0007.create(6).solve().intValue() == 13;
        
        lap("10001", () -> Problem0007.create(10_001).solve());
        lap("1E5", () -> Problem0007.create(100_000).solve());
    }
}
