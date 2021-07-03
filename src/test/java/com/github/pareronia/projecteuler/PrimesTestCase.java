package com.github.pareronia.projecteuler;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class PrimesTestCase {

    @Test
    public void findFactors() {
        assertThat(Primes.findFactors(1L), is(List.of(1)));
        assertThat(Primes.findFactors(2L), is(List.of(2)));
        assertThat(Primes.findFactors(9L), is(List.of(3, 3)));
        assertThat(Primes.findFactors(73L), is(List.of(73)));
//        assertThat(Primes.findFactors(3 * 73L), is(List.of(3, 73)));
        assertThat(Primes.findFactors(9_009L), is(List.of(3, 3, 7, 11, 13)));
        assertThat(Primes.findFactors(13_195L), is(List.of(5, 7, 13, 15)));
    }
    
    public void isPrime() {
        assertThat(Primes.isPrime(2L), is(TRUE));
        assertThat(Primes.isPrime(3L), is(TRUE));
        assertThat(Primes.isPrime(5L), is(TRUE));
        assertThat(Primes.isPrime(7L), is(TRUE));
        assertThat(Primes.isPrime(9L), is(FALSE));
        assertThat(Primes.isPrime(73L), is(TRUE));
    }
}
