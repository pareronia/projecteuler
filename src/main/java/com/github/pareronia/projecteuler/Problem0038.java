package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;

import com.github.pareronia.projecteuler.ProblemBase.NoInput;
import com.github.pareronia.projecteuler.util.NumberUtils;

public class Problem0038 extends ProblemBase<NoInput, Long> {

    private Problem0038() {}

    public static Problem0038 create() {
        return new Problem0038();
    }

    @Override
    public Long solve(final NoInput _input) {
        return LongStream.rangeClosed(9183, 9876)
                .filter(this::test)
                .map(n -> n * 100000 + 2 * n)
                .max()
                .getAsLong();
    }

    private boolean test(final long n) {
        final List<Integer> digits = NumberUtils.getDigits(n);
        final Set<Integer> s = new HashSet<>(digits);
        if (s.contains(0) || digits.size() != s.size()) {
            return false;
        }
        final List<Integer> digits2 = NumberUtils.getDigits(2 * n);
        final Set<Integer> s2 = new HashSet<>(digits2);
        if (s2.contains(0) || digits2.size() != s2.size()) {
            return false;
        }
        return s2.stream().noneMatch(ss -> s.contains(ss));
    }

    public static void main(final String[] args) {
        lap("", () -> Problem0038.create().solve(null));
    }
}
