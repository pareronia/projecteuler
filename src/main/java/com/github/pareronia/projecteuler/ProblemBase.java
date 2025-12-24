package com.github.pareronia.projecteuler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public abstract class ProblemBase {

    public abstract Number solve();

    protected static <V> void lap(final MyCallable<V> callable) {
        lap(null, callable);
    }

    protected static <V> void lap(final String prefix, final MyCallable<V> callable) {
        final long timerStart = System.nanoTime();
        final V answer = callable.call();
        final long timeSpent = (System.nanoTime() - timerStart) / 1000;
        double time;
        String unit;
        if (timeSpent < 1000) {
            time = timeSpent;
            unit = "µs";
        } else if (timeSpent < 1_000_000) {
            time = timeSpent / 1000.0;
            unit = "ms";
        } else {
            time = timeSpent / 1_000_000.0;
            unit = "s";
        }
        System.out.println(
                String.format(
                        "%s%s, took: %.3f %s",
                        prefix != null ? "[" + prefix + "] " : "", answer, time, unit));
    }

    @FunctionalInterface
    protected interface MyCallable<T> {
        T call();
    }

    protected static Stream<String> lines(final String filename) {
        try (BufferedReader rdr =
                new BufferedReader(
                        new InputStreamReader(Problem0067.class.getResourceAsStream(filename)))) {

        	return rdr.lines().toList().stream();
        } catch (final IOException e) {
            throw new RuntimeException("Could not read file '%'".formatted(filename), e);
        }
    }
}
