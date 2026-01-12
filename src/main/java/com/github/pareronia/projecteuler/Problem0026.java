package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;
import static java.util.Comparator.comparing;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;

public class Problem0026 extends ProblemBase<Long, Long> {

    private Problem0026() {
    }

    public static Problem0026 create() {
        return new Problem0026();
    }

    @Override
    public Long solve(final Long input) {
        return LongStream.iterate(3, n -> n < input, n -> n += 2).boxed()
                .parallel()
                .max(comparing(n -> divide(BigInteger.ONE, BigInteger.valueOf(n)).period))
                .get();
    }

    public static void main(final String[] args) {
    	assert Problem0026.create().solve(10L) == 7L;
        lap("1000", () -> Problem0026.create().solve(1000L));
    }

    private Result divide(final BigInteger numerator, final BigInteger denominator) {
        String result = numerator.divide(denominator).toString() + ".";
        BigInteger carry = numerator.mod(denominator).multiply(BigInteger.TEN);
        int digitCount = 0;
        final Map<BigInteger, Integer> carries = new HashMap<>();
        while (!carries.containsKey(carry)) {
            if (carry.signum() == 0) {
                if (result.endsWith(".")) {
                    result = result.substring(0, result.length() - 1);
                }
                return new Result(result, 0);
            }
            carries.put(carry, digitCount++);
            if (carry.compareTo(denominator) < 0) {
                result += "0";
                carry = carry.multiply(BigInteger.TEN);
            } else {
                result += carry.divide(denominator).toString();
                carry = carry.mod(denominator).multiply(BigInteger.TEN);
            }
        }
        return new Result(result, digitCount - carries.getOrDefault(carry, 0));
    }

    private record Result(String result, int period) {}
}
