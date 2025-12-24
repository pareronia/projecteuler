package com.github.pareronia.projecteuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import com.github.pareronia.projecteuler.itertools.Heap;

public class Problem0024 extends ProblemBase {

    private final transient Integer input;

    private Problem0024(final Integer input) {
        this.input = input;
    }

    public static Problem0024 create(final Integer input) {
        return new Problem0024(input);
    }

    @Override
    public Long solve() {
        final List<Long> lst = new ArrayList<>();
        Heap.accept(IntStream.range(0, this.input).toArray(), p -> lst.add(asLong(p)));
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
        lap("10/1E6", () -> Problem0024.create(10).solve());
    }
}
