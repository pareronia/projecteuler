package com.github.pareronia.projecteuler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.github.pareronia.projecteuler.math.Primes;

class PrimesTestCase {

    @Test
    public void findFactors() {
        assertThat(Primes.findFactors(1L)).containsExactly(1);
        assertThat(Primes.findFactors(2L)).containsExactly(2);
        assertThat(Primes.findFactors(9L)).containsExactly(3, 3);
        assertThat(Primes.findFactors(73L)).containsExactly(73);
//        assertThat(Primes.findFactors(3 * 73L)).containsExactly(3, 73);
        assertThat(Primes.findFactors(9_009L)).containsExactly(3, 3, 7, 11, 13);
        assertThat(Primes.findFactors(13_195L)).containsExactly(5, 7, 13, 15);
    }
    
    public void isPrime() {
        assertThat(Primes.isPrime(2L)).isTrue();
        assertThat(Primes.isPrime(3L)).isTrue();
        assertThat(Primes.isPrime(5L)).isTrue();
        assertThat(Primes.isPrime(7L)).isTrue();
        assertThat(Primes.isPrime(9L)).isFalse();
        assertThat(Primes.isPrime(73L)).isTrue();
    }
}
