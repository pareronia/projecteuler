package com.github.pareronia.projecteuler.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public final class NumberUtils {

    public static long asLong(final int[] p) {
        long ans = 0;
        for (int i = 0; i < p.length; i++) {
            ans += Math.pow(10, p.length - 1 - i) * p[i];
        }
        return ans;
    }

    public static List<Integer> getDigits(final long num) {
        final List<Integer> digits = new ArrayList<>();
        new DigitsIterator(num).forEachRemaining(digits::add);
        return digits;
    }

    public static LongStream getDigits(final BigInteger num) {
    	final char[] chars = num.toString().toCharArray();
    	return IntStream.range(0, chars.length).mapToLong(i -> chars[i] - '0');
    }

    public static DigitsIterator digitsIterator(final long num) {
        return new DigitsIterator(num);
    }

    public static final class DigitsIterator implements Iterator<Integer> {
        long n;

        DigitsIterator(final long num) {
            this.n = num;
        }

        @Override
        public boolean hasNext() {
            return n > 0;
        }

        @Override
        public Integer next() {
            final int nxt = (int) n % 10;
            n /= 10;
            return nxt;
        }
    }
}
