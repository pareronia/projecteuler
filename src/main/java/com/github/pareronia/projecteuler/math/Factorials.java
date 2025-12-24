package com.github.pareronia.projecteuler.math;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Factorials {

    private static final Map<Long, BigInteger> FACTORIALS = new HashMap<>();

    static {
        FACTORIALS.put(0L, BigInteger.valueOf(1));
        FACTORIALS.put(1L, BigInteger.valueOf(1));
        FACTORIALS.put(2L, BigInteger.valueOf(2));
        FACTORIALS.put(3L, BigInteger.valueOf(6));
        FACTORIALS.put(4L, BigInteger.valueOf(24));
        FACTORIALS.put(5L, BigInteger.valueOf(120));
        FACTORIALS.put(6L, BigInteger.valueOf(720));
        FACTORIALS.put(7L, BigInteger.valueOf(5_040));
        FACTORIALS.put(8L, BigInteger.valueOf(40_320));
        FACTORIALS.put(9L, BigInteger.valueOf(362_880));
        FACTORIALS.put(10L, BigInteger.valueOf(3_628_800));
        FACTORIALS.put(11L, BigInteger.valueOf(39_916_800));
        FACTORIALS.put(12L, BigInteger.valueOf(479_001_600));
        FACTORIALS.put(13L, BigInteger.valueOf(6_227_020_800L));
        FACTORIALS.put(14L, BigInteger.valueOf(87_178_291_200L));
        FACTORIALS.put(15L, BigInteger.valueOf(1_307_674_368_000L));
        FACTORIALS.put(16L, BigInteger.valueOf(20_922_789_888_000L));
        FACTORIALS.put(17L, BigInteger.valueOf(355_687_428_096_000L));
        FACTORIALS.put(18L, BigInteger.valueOf(6_402_373_705_728_000L));
        FACTORIALS.put(19L, BigInteger.valueOf(121_645_100_408_832_000L));
        FACTORIALS.put(20L, BigInteger.valueOf(2_432_902_008_176_640_000L));
    }

    public static BigInteger get(final long n) {
    	if (FACTORIALS.containsKey(n)) {
    		return FACTORIALS.get(n);
    	}
    	final BigInteger ans = BigInteger.valueOf(n).multiply(get(n - 1));
    	FACTORIALS.put(n, ans);
    	return ans;
    }
}
