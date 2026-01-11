package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.LongStream;

import com.github.pareronia.projecteuler.ProblemBase.NoInput;

public class Problem0039 extends ProblemBase<NoInput, Long> {

    private Problem0039() {}

    public static Problem0039 create() {
        return new Problem0039();
    }

    @Override
    public Long solve(final NoInput _input) {
        final Map<Long, Integer> map = LongStream.iterate(122, i -> i < 1000, i -> i + 2).boxed()
                .parallel()
                .collect(toMap(i -> i, this::count));
        return map.entrySet().stream()
                .max(comparing(Entry::getValue))
                .map(Entry::getKey)
                .get();
    }

    private int count(final long n) {
        int ans = 0;
        for (long i = 1; i < n; i++) {
            for (long j = 1; j <= n - i; j++) {
                final long k = n - i - j;
                if (k == 0) {
                    continue;
                }
                final long[] nums = { i, j, k };
                Arrays.sort(nums);
                if (nums[0] * nums[0] + nums[1] * nums[1] == nums[2] * nums[2]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(final String[] args) {
        lap("", () -> Problem0039.create().solve(null));
    }
}
