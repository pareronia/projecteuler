package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import com.github.pareronia.projecteuler.itertools.Heap;
import com.github.pareronia.projecteuler.util.NumberUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Problem0024 extends ProblemBase<Long, Long> {

    private Problem0024() {}

    public static Problem0024 create() {
        return new Problem0024();
    }

    @Override
    public Long solve(final Long input) {
        final List<Long> lst = new ArrayList<>();
        Heap.accept(
                IntStream.range(0, input.intValue()).toArray(),
                p -> lst.add(NumberUtils.asLong(p)));
        Collections.sort(lst);
        return lst.get(999_999);
    }

    public static void main(final String[] args) {
        lap("10/1E6", () -> Problem0024.create().solve(10L));
    }
}
