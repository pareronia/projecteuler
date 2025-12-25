package com.github.pareronia.projecteuler;

import static com.github.pareronia.projecteuler.util.ProblemUtils.lap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Problem0014 extends ProblemBase<Long, Long> {
    
    private final transient Map<Long, Long> lengths = new HashMap<>();
    
    private Problem0014() {
	}

	public static Problem0014 create() {
    	return new Problem0014();
    }
    
    private Long lengthOfCollatzSequence(final long start) {
        if (this.lengths.containsKey(start)) {
            return this.lengths.get(start);
        }
        
        final long length;
        if (start == 1L) {
            length = 1L;
        } else if (start % 2 == 0) {
            length = 1L + lengthOfCollatzSequence(start / 2);
        } else {
            length = 2L + lengthOfCollatzSequence((3 * start + 1) / 2);
        }
        this.lengths.put(start, length);
        return length;
    }
    
    @Override
    public Long solve(final Long input) {
        for (long i = input / 2; i < input; i++) {
            lengthOfCollatzSequence(i);
        }
        
        return this.lengths.entrySet().stream()
                .max(Comparator.comparing(Entry::getValue))
                .map(Entry::getKey).orElseThrow();
    }

    public static void main(final String[] args) {
        lap("1E6", () -> Problem0014.create().solve(1_000_000L));
        lap("5E6", () -> Problem0014.create().solve(5_000_000L));
    }
}
