package com.github.pareronia.projecteuler.math;

import java.math.BigInteger;

public final class LCM {

    public static BigInteger lcm(final BigInteger first, final BigInteger second) {
    	return first.abs().multiply(second.abs().divide(first.gcd(second)));
    }
}
