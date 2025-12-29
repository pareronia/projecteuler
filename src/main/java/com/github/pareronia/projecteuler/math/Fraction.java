package com.github.pareronia.projecteuler.math;

import java.math.BigInteger;

public record Fraction(BigInteger numerator, BigInteger denominator) {

    public enum Operation {
        INVERT,
        ADD
    }

    public Fraction(final long numerator, final long denominator) {
    	this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    public Fraction invert() {
        return new Fraction(this.denominator, this.numerator);
    }

    public Fraction add(final Fraction other) {
        final BigInteger lcm = LCM.lcm(this.denominator, other.denominator);
        return new Fraction(
                        this.numerator
                                .multiply(lcm.divide(this.denominator))
                                .add(other.numerator.multiply(lcm.divide(other.denominator))),
                        lcm)
                .reduce();
    }

    private Fraction reduce() {
        final BigInteger gcd = this.numerator.gcd(this.denominator);
        if (gcd.equals(BigInteger.ONE)) {
            return this;
        }
        return new Fraction(this.numerator.divide(gcd), this.denominator.divide(gcd));
    }
}
