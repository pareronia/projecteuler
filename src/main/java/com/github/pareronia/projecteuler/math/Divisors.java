package com.github.pareronia.projecteuler.math;

import java.util.ArrayList;
import java.util.List;

public final class Divisors {

    public static List<Long> divisors(final long num) {
        final List<Long> divisors = new ArrayList<>();
        for (long i = 1; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                divisors.add(i);
                if (i * i != num) {
                    divisors.add(num / i);
                }
            }
        }
        return divisors;
    }
}
