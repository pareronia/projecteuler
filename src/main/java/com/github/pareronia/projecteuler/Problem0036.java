package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import com.github.pareronia.projecteuler.util.NumberUtils;

import java.util.BitSet;
import java.util.List;
import java.util.stream.LongStream;

public class Problem0036 extends ProblemBase<Long, Long> {

    private Problem0036() {}

    public static Problem0036 create() {
        return new Problem0036();
    }

    @Override
    public Long solve(final Long input) {
        long ans = 0;
        ans +=
                LongStream.iterate(1, i -> i + 1)
                        .map(i -> this.makePalindrome2(i, false))
                        .takeWhile(p -> p < input)
                        .filter(this::test10)
                        .sum();
        ans +=
                LongStream.iterate(1, i -> i + 1)
                        .map(i -> this.makePalindrome2(i, true))
                        .takeWhile(p -> p < input)
                        .filter(this::test10)
                        .sum();
        return ans;
    }

    private long makePalindrome2(long n, final boolean odd) {
        long ans = n;
        if (odd) {
            n >>= 1;
        }
        while (n > 0) {
            ans = (ans << 1) + (n & 1);
            n >>= 1;
        }
        return ans;
    }

    private Long solveInitial(final Long input) {
        return LongStream.iterate(1, i -> i < input, i -> i + 2)
                .parallel()
                .filter(this::test)
                .sum();
    }

    private boolean test(final long num) {
        if (!test10(num)) {
            return false;
        }
        final BitSet bits = BitSet.valueOf(new long[] {num});
        int i = 0;
        int j = bits.length() - 1;
        while (i < j) {
            if (bits.get(i) != bits.get(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean test10(final long num) {
        final List<Integer> digits10 = NumberUtils.getDigits(num);
        int i = 0;
        int j = digits10.size() - 1;
        while (i < j) {
            if (digits10.get(i) != digits10.get(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(final String[] args) {
        lap("1E6", () -> Problem0036.create().solveInitial(1_000_000L));
        lap("[overview] 1E6", () -> Problem0036.create().solve(1_000_000L));
        lap("1E8", () -> Problem0036.create().solveInitial(100_000_000L));
        lap("[overview] 1E8", () -> Problem0036.create().solve(100_000_000L));
    }
}
