package com.github.pareronia.projecteuler.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import com.github.pareronia.projecteuler.ProblemBase;

public final class ProblemUtils {

    public static String readString(final String filename) {
        try (InputStream is = ProblemBase.class.getResourceAsStream(filename)) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (final IOException e) {
            throw new RuntimeException("Could not read file '%'".formatted(filename), e);
        }
    }

    public static <V> void lap(final String prefix, final MyCallable<V> callable) {
        Timed<V> timed;
		try {
			timed = Timed.timed(
			        callable,
			        System::nanoTime);
		} catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
		}
        final V answer = timed.result();
        final String duration = printDuration(timed.duration());
        System.out.println(String.format("[%s] : %s, took: %s",
                prefix, ANSIColors.bold(answer.toString()), duration));
    }

    public static String printDuration(final Duration duration) {
        double timeSpent = duration.toNanos() / 1_000;
        String time;
        if (timeSpent < 1000) {
            time = String.format("%.0f µs", timeSpent);
        } else if (timeSpent < 1_000_000) {
        	time = String.format("%.3f ms", timeSpent / 1000);
        } else {
        	timeSpent = timeSpent / 1_000_000;
			if (timeSpent <= 1) {
				time = String.format("%.3f s", timeSpent);
			} else if (timeSpent <= 5) {
				time = ANSIColors.yellow(String.format("%.3f s", timeSpent));
			} else {
				time = ANSIColors.red(String.format("%.3f s", timeSpent));
			}
        }
        return String.format("%s", time);
    }

    @FunctionalInterface
    public interface MyCallable<T> {
        T call() throws InvocationTargetException, IllegalAccessException;
    }
}
