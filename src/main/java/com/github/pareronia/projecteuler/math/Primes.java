package com.github.pareronia.projecteuler.math;

import java.util.ArrayList;
import java.util.List;

public class Primes {
   
    public static boolean isPrime(final Long number) {
        final long start = (long) Math.floor(Math.sqrt(number));
        for (long i = start; i >= 2; i--) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static List<Integer> findFactors(final Long input) {
        final List<Integer> factors = new ArrayList<>();
        
        long number = input;
        int lastFactor;
        if (number % 2 == 0) {
            lastFactor = 2;
            factors.add(2);
            number /= 2;
            while (number % 2 == 0) {
                factors.add(2);
                number /= 2;
            }
        } else {
            lastFactor = 1;
        }
        int factor = 3;
        long maxFactor = (long) Math.floor(Math.sqrt(number));
        while (number > 1 && factor <= maxFactor) {
            if (number % factor == 0) {
                factors.add(factor);
                lastFactor = factor;
                number /= factor;
                while (number % factor == 0) {
                    factors.add(factor);
                    number /= factor;
                }
                maxFactor = (long) Math.floor(Math.sqrt(number));
            }
            factor += 2;
        }
        if (lastFactor == 1) {
            factors.add((int) number);
        } else if (number != 1) {
            factors.add(factor);
        }
        
        return factors;
    }
}
