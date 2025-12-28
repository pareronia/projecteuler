package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;
import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import com.github.pareronia.projecteuler.itertools.Heap;
import com.github.pareronia.projecteuler.util.NumberUtils;
import com.github.pareronia.projecteuler.util.ProblemUtils;
import com.github.pareronia.projecteuler.util.StringUtils;

public class Problem0079 extends ProblemBase<String, Long> {

    private Problem0079() {}

    public static Problem0079 create() {
        return new Problem0079();
    }

    @Override
    public Long solve(final String input) {
        final Set<String> keylogs = Arrays.stream(StringUtils.splitLines(input)).collect(toSet());
        final int[] chars = {0, 1, 2, 3, 6, 7, 8, 9};
        final PassCodeFinder consumer = new PassCodeFinder(keylogs);
        Heap.accept(chars, consumer);
        return consumer.ans;
    }

    public static void main(final String[] args) {
        final String input = ProblemUtils.readString("0079_keylog.txt");
        lap("keylog.txt", () -> Problem0079.create().solve(input));
    }

    private static final class PassCodeFinder implements Consumer<int[]> {
        private final Set<String> keylogs;
        private long ans;

        private PassCodeFinder(final Set<String> keylogs) {
            this.keylogs = keylogs;
        }

        @Override
        public void accept(final int[] p) {
            final List<Integer> lst = Arrays.stream(p).boxed().toList();
            for (final String keylog : this.keylogs) {
                for (int i = 0; i < keylog.length(); i++) {
                    final int a = keylog.charAt(i) - '0';
                    for (int j = i + 1; j < keylog.length(); j++) {
                        final int b = keylog.charAt(j) - '0';
                        if (!(lst.indexOf(a) < lst.indexOf(b))) {
                            return;
                        }
                    }
                }
            }
            this.ans = NumberUtils.asLong(p);
        }
    }
}
