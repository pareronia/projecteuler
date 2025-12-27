package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import com.github.pareronia.projecteuler.ProblemBase.NoInput;
import com.github.pareronia.projecteuler.itertools.Heap;

public class Problem0032 extends ProblemBase<NoInput, Long> {

    private Problem0032() {}

    public static Problem0032 create() {
        return new Problem0032();
    }

    @Override
    public Long solve(final NoInput _input) {
        final Set<Long> ans = new HashSet<>();
        Heap.accept(
                IntStream.rangeClosed(1, 9).toArray(),
                p -> test(p).ifPresent(ans::add));
        return ans.stream().mapToLong(Long::longValue).sum();
    }

    private Optional<Long> test(final int[] p) {
        if ((p[p.length - 1] & 1) == 1) {
            return Optional.empty();
        }
        final long prod = asLong(Arrays.copyOfRange(p, 5, p.length));
        if (asLong(Arrays.copyOfRange(p, 0, 1)) * asLong(Arrays.copyOfRange(p, 1, 5)) == prod) {
            return Optional.of(prod);
        }
        if (asLong(Arrays.copyOfRange(p, 0, 4)) * asLong(Arrays.copyOfRange(p, 4, 5)) == prod) {
            return Optional.of(prod);
        }
        if (asLong(Arrays.copyOfRange(p, 0, 2)) * asLong(Arrays.copyOfRange(p, 2, 5)) == prod) {
            return Optional.of(prod);
        }
        if (asLong(Arrays.copyOfRange(p, 0, 3)) * asLong(Arrays.copyOfRange(p, 3, 5)) == prod) {
            return Optional.of(prod);
        }
        return Optional.empty();
    }

    private long asLong(final int[] p) {
        long ans = 0;
        for (int i = 0; i < p.length; i++) {
            ans += Math.pow(10, p.length - 1 - i) * p[i];
        }
        return ans;
    }

    public static void main(final String[] args) {
        lap("", () -> Problem0032.create().solve(null));
    }
}
