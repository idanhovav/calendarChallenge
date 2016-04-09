import java.io.*;
import java.util.*;

public class Solution {
	// can just define as static
	static class Date {
		int day;
		int days_after_start;
		
		public Date(int day, int days_after_start) {
			this.day = day;
			this.days_after_start = days_after_start;
		}
		public boolean isBothFirstAndSunday() {
			return (this.day == 1 && (this.days_after_start % 7 == 6));
		}
	}
	public static void main(String[] args) {
		int counter = 0;
		int day = 1;
		int days_after_start = 0;
		for (int year = 1900; year <= 2000; year++) {
			for (int month = 0; month < 12; month++) {
				if (year != 1900 && isSunday(days_after_start)) {
					counter++;
				}
				days_after_start += daysInMonth(month, year);
			}
		}
		System.out.println(counter);
		
	}
	private static boolean isSunday(int days_after_start) {
		return (days_after_start % 7 == 6);
	}
	
	public static int calculate() {
		int counter = 0;
		int day = 1;
		int month = 0;
		int year = 1900;
		int days_after_start = 0;
		boolean isEnd = false;
		
		
		/*
		int days_after_start = 0;
		int day = 1;
		for year:
			for month:
				days_after_start = daysInMonth(month, year);
		
		*/
		
		while (!isEnd) {
			Date thisDate = new Date(day, days_after_start);
			if (thisDate.isBothFirstAndSunday() && year >= 1901) {
				counter++;
			}
			int[] updatedVals = update(day, month, year);
			day = updatedVals[0];
			month = updatedVals[1];
			year = updatedVals[2];
			isEnd = (year == 2001);
			days_after_start++;
		}
		return counter;
	}
	
	
	public static int[] update(int day, int month, int year) {
		int[] returnVals = new int[3];
		int days_in_month = daysInMonth(month, year);
		if (day == days_in_month) {
			if (month == 11) {
				year++;
				month = 0;
				day = 1;
			} else {
				month++;
				day = 1;
			}
		} else {
			day++;
		}
		
		returnVals[0] = day;
		returnVals[1] = month;
		returnVals[2] = year;
		return returnVals;
	}
	
	public static int daysInMonth(int month, int year) {
		if (month == 3 || month == 5 || month == 8 || month == 10) {
			return 30;
		} else if (month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11) {
			return 31;
		} else if ((year % 100 == 0 && year % 400 != 0)|| year % 4 != 0) {
			return 28;
		} else {
			return 29;
		}
	}
}




/* 
Your previous Plain Text content is preserved below:
Calendar Programming Problem:
January 1, 1900, was a Monday.
April, June, September, and November each have 30 days.\
3, 5, 8, 10
January, March, May, July, August, October, and December each have 31 days.
0, 2, 4, 6, 7, 9, 11
On years evenly divisible by 4 February has 29 days except on a century
unless the century is divisible by 400.
1
On all other years February has 28 days.

Without using any date/time libraries, figure out how many Sundays fell on the first of the month during the twentieth century (January 1, 1901, to December 31, 2000).

M T W T F S S
0 1 2 3 4 5 6

Create a Day object w/ following variables:
 - int month
 - int day
 - int year
 - int days_after_start
 - boolean isFirst
 
Same w/ Month class- overkill
Same w/ year object - overkill

overall idea: 
- iterate thru days, manipulate year/month/day variables based on rules given
- for each object created with those parameters, check if it's a first and a sunday: if yes, increase some counter.

questions:
- communication - python for interviews
- modularization
- planning
- overall places of improvement
 */