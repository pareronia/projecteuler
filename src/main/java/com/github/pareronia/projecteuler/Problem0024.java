package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import com.github.pareronia.projecteuler.itertools.Heap;

public class Problem0024 extends ProblemBase<Long, Long> {

    private Problem0024() {
    }

    public static Problem0024 create() {
        return new Problem0024();
    }

    @Override
    public Long solve(final Long input) {
        final List<Long> lst = new ArrayList<>();
        Heap.accept(IntStream.range(0, input.intValue()).toArray(), p -> lst.add(asLong(p)));
        Collections.sort(lst);
        return lst.get(999_999);
    }

    private long asLong(final int[] p) {
        final char[] c = new char[p.length];
        for (int i = 0; i < p.length; i++) {
            c[i] = (char) ('0' + p[i]);
        }
        return Long.parseLong(String.valueOf(c));
    }

    public static void main(final String[] args) {
        lap("10/1E6", () -> Problem0024.create().solve(10L));
    }
}
