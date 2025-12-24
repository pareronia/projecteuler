package com.github.pareronia.projecteuler;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class Problem0019 extends ProblemBase {

    private Problem0019() {}

    public static Problem0019 create() {
        return new Problem0019();
    }

    @Override
    public Long solve() {
    	long ans = 0;
    	final LocalDate end = LocalDate.of(2001, 1, 1);
    	LocalDate date = LocalDate.of(1901, 1, 1);
		while (date.isBefore(end)) {
    		if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
    			ans++;
    		}
    		date = date.plusMonths(1);
    	}
        return ans;
    }

    public long solveExt() {
    	long ans = 0;
    	int day = 1;  // Tuesday 1 Jan 1901
    	for (int year = 1901; year <= 2000; year++) {
			for (int month = 1; month <= 12; month++) {
				day = (day + days(year, month)) % 7;
				if (day == 6) {
					ans++;
				}
			}
		}
    	return ans;
    }

    private int days(final int year, final int month) {
    	if (Set.of(1, 3, 5, 7, 8, 10, 12).contains(month)) {
    		return 31;
    	}
    	if (month == 2) {
    		return isLeapYear(year) ? 29 : 28;
    	}
    	return 30;
    }

    private boolean isLeapYear(final int year) {
    	return year % 4 == 0 && (year % 400 == 0 || year % 100 != 0);
    }

    public static void main(final String[] args) {
        lap("java.time", () -> Problem0019.create().solve());
        lap("ext", () -> Problem0019.create().solveExt());
    }
}
