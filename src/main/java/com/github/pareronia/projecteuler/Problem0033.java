package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import com.github.pareronia.projecteuler.ProblemBase.NoInput;
import com.github.pareronia.projecteuler.math.Fraction;
import com.github.pareronia.projecteuler.util.NumberUtils;

public class Problem0033 extends ProblemBase<NoInput, Long> {

    private Problem0033() {}

    public static Problem0033 create() {
        return new Problem0033();
    }

    @Override
    public Long solve(final NoInput _input) {
        return IntStream.rangeClosed(11, 98)
                .filter(n -> n % 10 != 0).boxed()
                .flatMap(n -> IntStream.rangeClosed(n + 1, 99)
                            .filter(d -> d % 10 != 0)
                            .filter(d -> this.test(n, d))
                            .mapToObj(d -> new Fraction(n, d)))
                .reduce(Fraction::multiply).orElseThrow()
                .denominator().longValue();
    }

    private boolean test(final int n, final int d) {
        final Set<Integer> digitsN = digits(n);
        final Set<Integer> digitsD = digits(d);
        if (digitsN.equals(digitsD)) {
            return false;
        }
        final List<Integer> c = digitsN.stream().filter(g -> digitsD.contains(g)).toList();
        if (c.isEmpty()) {
            return false;
        }
        final Fraction f = new Fraction(n, d);
        final int newN = digitToKeep(digitsN, c.getFirst());
        final int newD = digitToKeep(digitsD, c.getFirst());
        return f.reduce().equals(new Fraction(newN, newD).reduce());
    }

    private Set<Integer> digits(final long num) {
        final Set<Integer> ans = new HashSet<>();
        NumberUtils.digitsIterator(num).forEachRemaining(ans::add);
        return ans;
    }

    private int digitToKeep(final Set<Integer> digits, final int remove) {
        if (digits.size() == 1) {
            return digits.iterator().next();
        } else {
            return digits.stream().filter(g -> g != remove).findFirst().orElseThrow();
        }
    }

    public static void main(final String[] args) {
        lap("", () -> Problem0033.create().solve(null));
    }
}
