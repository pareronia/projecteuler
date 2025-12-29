package com.github.pareronia.projecteuler.math;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FractionTest {

	@Test
	void invert() {
		assertThat(f(1, 1).invert()).isEqualTo(f(1, 1));
		assertThat(f(1, 2).invert()).isEqualTo(f(2, 1));
		assertThat(f(2, 1).invert()).isEqualTo(f(1, 2));
	}

	@Test
	void add() {
		assertThat(f(1, 1).add(f(1, 1))).isEqualTo(f(2, 1));
		assertThat(f(5, 5).add(f(5, 5))).isEqualTo(f(2, 1));
		assertThat(f(1, 1).add(f(1, 2))).isEqualTo(f(3, 2));
		assertThat(f(2, 21).add(f(1, 6))).isEqualTo(f(11, 42));
		assertThat(f(1, 3).add(f(1, 3)).add(f(1, 3))).isEqualTo(f(1, 1));
	}

	private static Fraction f(final long numerator, final long divisor) {
		return new Fraction(numerator, divisor);
	}
}
