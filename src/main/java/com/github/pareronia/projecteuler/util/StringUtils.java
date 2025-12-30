package com.github.pareronia.projecteuler.util;

import java.util.Arrays;
import java.util.Objects;

public final class StringUtils {

    public static String[] splitLines(final String string) {
        return Objects.requireNonNull(string).split("\\r?\\n");
    }

    public static int[][] toIntMatrix(final String string) {
        return Arrays.stream(splitLines(string))
                .map(row -> Arrays.stream(row.split("[ ,]")).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
    }
}
