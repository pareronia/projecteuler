package com.github.pareronia.projecteuler.util;

import java.util.Objects;

public final class StringUtils {

	public static String[] splitLines(final String string) {
		return Objects.requireNonNull(string).split("\\r?\\n");
	}
}
