package com.github.pareronia.projecteuler.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;

public final class Primes {

    private static final String FILENAME = "primes.txt";
    public static final Set<Long> PRIMES = new HashSet<>();
    public static final long MAX_PRIME;

    static {
        try (BufferedReader is =
                new BufferedReader(
                        new InputStreamReader(
                                Primes.class.getResourceAsStream(FILENAME),
                                StandardCharsets.UTF_8))) {
            is.lines().map(Long::parseLong).forEach(PRIMES::add);
        } catch (final IOException e) {
            throw new RuntimeException("Could not read file '%'".formatted(FILENAME), e);
        }
        MAX_PRIME = PRIMES.stream().mapToLong(Long::longValue).max().getAsLong();
    }

    public static boolean isPrime(final Long number) {
    	if (PRIMES.contains(number)) {
    		return true;
    	} else if (number > MAX_PRIME) {
			final long start = (long) Math.floor(Math.sqrt(number));
			for (long i = start; i >= 2; i--) {
				if (number % i == 0) {
					return false;
				}
			}
			PRIMES.add(number);
			return true;
    	} else {
    		return false;
    	}
    }

    public static List<Integer> findFactors(final Long input) {
        final List<Integer> factors = new ArrayList<>();

        long number = input;
        int lastFactor;
        if (number % 2 == 0) {
            lastFactor = 2;
            factors.add(2);
            number /= 2;
            while (number % 2 == 0) {
                factors.add(2);
                number /= 2;
            }
        } else {
            lastFactor = 1;
        }
        int factor = 3;
        long maxFactor = (long) Math.floor(Math.sqrt(number));
        while (number > 1 && factor <= maxFactor) {
            if (number % factor == 0) {
                factors.add(factor);
                lastFactor = factor;
                number /= factor;
                while (number % factor == 0) {
                    factors.add(factor);
                    number /= factor;
                }
                maxFactor = (long) Math.floor(Math.sqrt(number));
            }
            factor += 2;
        }
        if (lastFactor == 1) {
            factors.add((int) number);
        } else if (number != 1) {
            factors.add(factor);
        }

        return factors;
    }

    private void generate(final long limit) throws IOException {
        final Path path = Path.of(".").resolve(FILENAME);
        try (PrintStream out =
                new PrintStream(
                        Files.newOutputStream(
                                path,
                                StandardOpenOption.CREATE,
                                StandardOpenOption.TRUNCATE_EXISTING))) {
            out.println(2);
            LongStream.iterate(3, i -> i <= limit, i -> i + 2)
                    .filter(Primes::isPrime)
                    .forEach(out::println);
        }
    }

    public static void main(final String[] args) throws IOException {
        new Primes().generate(2_000_000);
    }
}
