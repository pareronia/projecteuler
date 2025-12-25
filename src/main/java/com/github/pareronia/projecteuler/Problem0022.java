package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import com.github.pareronia.projecteuler.util.ProblemUtils;

public class Problem0022 extends ProblemBase<String, Long> {

    private Problem0022() {
    }

    public static Problem0022 create() {
        return new Problem0022();
    }

    public int score(final String name) {
        return IntStream.range(0, name.length()).map(i -> name.charAt(i) - 'A' + 1).sum();
    }

    @Override
    public Long solve(final String input) {
        final List<String> names =
                Arrays.stream(input.substring(1, input.length() - 1).split("\",\""))
                        .sorted()
                        .toList();
        return IntStream.range(0, names.size()).mapToLong(i -> (i + 1) * score(names.get(i))).sum();
    }

    public static void main(final String[] args) {
    	final String input = ProblemUtils.readString("0022_names.txt");
        lap("names.txt", () -> Problem0022.create().solve(input));
    }
}
