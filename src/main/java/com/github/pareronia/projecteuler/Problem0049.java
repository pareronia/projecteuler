package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.pareronia.projecteuler.ProblemBase.NoInput;
import com.github.pareronia.projecteuler.itertools.Heap;
import com.github.pareronia.projecteuler.math.Primes;
import com.github.pareronia.projecteuler.util.NumberUtils;

public class Problem0049 extends ProblemBase<NoInput, Long> {

    private Problem0049() {}

    public static Problem0049 create() {
        return new Problem0049();
    }

    @Override
    public Long solve(final NoInput _input) {
        return Arrays.stream(Primes.primesUpTo(10000))
                .filter(n -> n > 1000)
                .mapToObj(this::primePermutationsSorted)
                .filter(p -> p.size() >= 3 && !p.contains(1487L))
                .distinct()
                .map(this::test)
                .filter(p -> !p.isEmpty())
                .map(p -> 100_000_000L * p.get(0) + 10_000 * p.get(1) + p.get(2))
                .findFirst().orElseThrow();
    }

    private List<Long> primePermutationsSorted(final long n) {
        final List<Integer> digits = NumberUtils.getDigits(n);
        if (digits.contains(0)) {
            return List.of();
        }
        final Set<Long> p = new HashSet<>();
        Heap.accept(
                new int[] {0, 1, 2, 3},
                t -> p.add(1000L * digits.get(t[0]) + 100L * digits.get(t[1])
                                + 10L * digits.get(t[2]) + digits.get(t[3])));
        return p.stream().filter(Primes::isPrime).sorted().toList();
    }

    private List<Long> test(final List<Long> nums) {
        for (int i1 = 0; i1 < nums.size(); i1++) {
            for (int i2 = i1 + 1; i2 < nums.size(); i2++) {
                for (int i3 = i2 + 1; i3 < nums.size(); i3++) {
                    if (nums.get(i2) - nums.get(i1) == nums.get(i3) - nums.get(i2)) {
                        return List.of(nums.get(i1), nums.get(i2), nums.get(i3));
                    }
                }
            }
        }
        return List.of();
    }

    public static void main(final String[] args) {
        lap("", () -> Problem0049.create().solve(null));
    }
}
