package com.github.pareronia.projecteuler.util;

import com.github.pareronia.projecteuler.util.ProblemUtils.MyCallable;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

public record Timed<V>(V result, Duration duration) {

    public static <V> Timed<V> timed(
            final MyCallable<V> callable, final Supplier<Long> nanoTimeSupplier)
            throws InvocationTargetException, IllegalAccessException {

        final long timerStart = nanoTimeSupplier.get();
        final V answer = callable.call();
        final long timerEnd = nanoTimeSupplier.get();
        return new Timed<>(answer, Duration.of(timerEnd - timerStart, ChronoUnit.NANOS));
    }
}
