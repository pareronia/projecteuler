package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import static java.util.stream.Collectors.joining;

import com.github.pareronia.projecteuler.util.ProblemUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Map;

public class Problem0089 extends ProblemBase<String, Long> {

    private static final Map<Integer, String> DIGITS =
            Map.of(
                    1, "I",
                    5, "V",
                    10, "X",
                    50, "L",
                    100, "C",
                    500, "D",
                    1000, "M",
                    5000, "MMM");

    private Problem0089() {}

    public static Problem0089 create() {
        return new Problem0089();
    }

    @Override
    public Long solve(final String input) {
        return Arrays.stream(input.split("\\r?\\n"))
                .mapToLong(line -> line.length() - getMinimal(getValue(line)).length())
                .sum();
    }

    private long getValue(final String string) {
        long ans = 0;
        int i = 0;
        while (true) {
            if (i >= string.length()) {
                break;
            }
            final boolean last = i + 1 == string.length();
            final char ch = string.charAt(i);
            if (ch == 'M') {
                ans += 1000;
                i++;
            } else if (ch == 'D') {
                ans += 500;
                i++;
            } else if (ch == 'C') {
                if (last || string.charAt(i + 1) != 'D' && string.charAt(i + 1) != 'M') {
                    ans += 100;
                    i++;
                } else {
                    ans += string.charAt(i + 1) == 'D' ? 400 : 900;
                    i += 2;
                }
            } else if (ch == 'L') {
                ans += 50;
                i++;
            } else if (ch == 'X') {
                if (last || string.charAt(i + 1) != 'L' && string.charAt(i + 1) != 'C') {
                    ans += 10;
                    i++;
                } else {
                    ans += string.charAt(i + 1) == 'L' ? 40 : 90;
                    i += 2;
                }
            } else if (ch == 'V') {
                ans += 5;
                i++;
            } else {
                if (last || string.charAt(i + 1) != 'V' && string.charAt(i + 1) != 'X') {
                    ans += 1;
                    i++;
                } else {
                    ans += string.charAt(i + 1) == 'V' ? 4 : 9;
                    i += 2;
                }
            }
        }
        return ans;
    }

    private String getMinimal(final long num) {
        final Deque<String> q = new ArrayDeque<>();
        long n = num;
        int pow = 0;
        while (n > 0) {
            final StringBuilder sb = new StringBuilder();
            final long d = n % 10;
            final int p = (int) Math.pow(10, pow);
            if (d <= 3) {
                sb.repeat(DIGITS.get(p), (int) d);
            } else if (d == 4) {
                sb.append(DIGITS.get(p)).append(DIGITS.get(5 * p));
            } else if (d == 5) {
                sb.append(DIGITS.get(5 * p));
            } else if (d <= 8) {
                sb.append(DIGITS.get(5 * p));
                sb.repeat(DIGITS.get(p), (int) d - 5);
            } else {
                sb.append(DIGITS.get(p)).append(DIGITS.get(10 * p));
            }
            q.addFirst(sb.toString());
            pow++;
            n /= 10;
        }
        return q.stream().collect(joining());
    }

    public static void main(final String[] args) {
        final String test =
                """
                IIIIIIIIIIIIIIII
                VIIIIIIIIIII
                VVIIIIII
                XIIIIII
                VVVI
                XVI
                """;
        assert Problem0089.create().solve(test) == 32;

        final String input = ProblemUtils.readString("0089_roman.txt");
        lap("roman.txt", () -> Problem0089.create().solve(input));
    }
}
