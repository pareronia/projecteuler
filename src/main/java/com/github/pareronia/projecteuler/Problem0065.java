package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import com.github.pareronia.projecteuler.math.Fraction;
import com.github.pareronia.projecteuler.math.Fraction.Operation;
import com.github.pareronia.projecteuler.util.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Problem0065 extends ProblemBase<Long, Long> {

    private final int constant;
    private final Supplier<Integer> divisors;

    private Problem0065(final int constant, final Supplier<Integer> divisors) {
        this.constant = constant;
        this.divisors = divisors;
    }

    public static Problem0065 create() {
        return new Problem0065(2, new EulerSupplier());
    }

    public static Problem0065 create(final int constant, final Supplier<Integer> divisors) {
        return new Problem0065(constant, divisors);
    }

    @Override
    public Long solve(final Long input) {
        final InfiniteContinuedFraction icf =
                InfiniteContinuedFraction.create(this.constant, this.divisors, input.intValue());
        final Fraction ans = icf.solve();
        return NumberUtils.getDigits(ans.numerator()).sum();
    }

    public static void main(final String[] args) {
        assert Problem0065.create(1, () -> 2).solve(10L) == 15;
        assert Problem0065.create().solve(10L) == 17;
        lap("e, 100", () -> Problem0065.create().solve(100L));
    }

    record InfiniteContinuedFraction(int start, List<Step> steps) {

        record Step(Fraction.Operation operation, Fraction... operands) {

            public Fraction apply(final Fraction fraction) {
                return switch (this.operation) {
                    case ADD -> fraction.add(this.operands[0]);
                    case INVERT -> fraction.invert();
                };
            }
        }

        public static InfiniteContinuedFraction create(
                final int constant, final Supplier<Integer> divisors, final int count) {

            final List<Integer> dd = Stream.generate(divisors).limit(count - 1).toList();
            final int start = dd.getLast();
            final List<Step> steps = new ArrayList<>();
            for (int i = dd.size() - 2; i >= 0; i--) {
                final int d = dd.get(i);
                steps.add(new Step(Operation.INVERT));
                steps.add(new Step(Operation.ADD, new Fraction(d, 1)));
            }
            steps.add(new Step(Operation.INVERT));
            steps.add(new Step(Operation.ADD, new Fraction(constant, 1)));
            return new InfiniteContinuedFraction(start, steps);
        }

        public Fraction solve() {
            Fraction ans = new Fraction(this.start, 1);
            for (final Step step : this.steps) {
                ans = step.apply(ans);
            }
            return ans;
        }
    }

    private static class EulerSupplier implements Supplier<Integer> {
        private int i = 3;

        @Override
        public Integer get() {
            int ans;
            final int m = i % 3;
            if (m == 0 || m == 2) {
                ans = 1;
            } else {
                ans = (i / 3) * 2;
            }
            i++;
            return ans;
        }
    }
}
