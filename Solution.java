import java.io.*;
import java.util.*;

/* Idea:
create a date object. has day of week (how, where to start?)

Manipulation of what days to add to hashmap.

Create a hashmap of dates and a fun fact scraped from wikipedia about that day.

January 1, 0001 was a Monday in theory

*/

// EVERYTHING in the form of dd/mm/yyyy


public class Solution {
	public static final int MONTHS_IN_YEAR = 12;
	public static final int DAYS_IN_WEEK = 7;
	public static void main(String[] args) {
		HashSet<Date> dates = new HashSet<Date>();
		Scanner userInput = new Scanner(System.in);
		intro();
		// check inputs
		int[] startDate = getDate(userInput, "starting");
		int[] endDate = getDate(userInput, "ending");
		int startDay = startDate[0];
		int startMonth = startDate[1];
		int startYear = startDate[2];
		int endDay = endDate[0];
		int endMonth = endDate[1];
		int endYear = endDate[2];

		addDaysToList(dates, startYear, endYear, startMonth, endMonth, startDay, endDay);
		// now we have the days in a hashset.

		System.out.println("There are "+ dates.size() + " days in the given set.");
	}
	private static void addDaysToList(HashSet<Date> dates, int startYear, int endYear, int startMonth,
		int endMonth, int startDay, int endDay) {
		int[] mLimits, dLimits; //monthLimits and dayLimits, respectively.
		int yearlyStartMonth;
		int yearlyEndMonth;
		int monthlyStartDay;
		int monthlyEndDay;

		for (int year = startYear; year <= endYear; year++) {
			mLimits = monthLimits(year, startYear, endYear, startMonth, endMonth);
			yearlyStartMonth = mLimits[0];
			yearlyEndMonth = mLimits[1];
			for (int month = yearlyStartMonth; month <= yearlyEndMonth; month++) {
				dLimits = dayLimits(month, year, startYear, endYear, startMonth,
					endMonth, startDay, endDay);
				monthlyStartDay = dLimits[0];
				monthlyEndDay = dLimits[1];
				for (int day = monthlyStartDay; day <= monthlyEndDay; day++) {
					Date date = new Date(day, month, year);
					dates.add(date);
				}
			}
		}
	}
	private static int[] dayLimits(int month, int year, int startYear, int endYear,
		int startMonth, int endMonth, int startDay, int endDay) {
		int monthlyStartDay = 1;
		int monthlyEndDay = daysInMonth(month, year);
		if (month == startMonth && year == startYear) {
			monthlyStartDay = startDay;
		}
		if (month == endMonth && year == endYear) {
			monthlyEndDay = endDay;
		}
		int[] limits = {monthlyStartDay, monthlyEndDay};
		return limits;
	}
	private static int[] monthLimits(int year, int startYear, int endYear,
		int startMonth, int endMonth) {
		int yearlyStartMonth = 1;
		int yearlyEndMonth = 12;
		if (year == startYear) {
			yearlyStartMonth = startMonth;
		}
		if (year == endYear) {
			yearlyEndMonth = endMonth;
		}
		int[] limits = {yearlyStartMonth, yearlyEndMonth};
		return limits;
	}
	public static int daysInMonth(int month, int year) {
		// months indexed starting at 1
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		} else if ((year % 100 == 0 && year % 400 != 0)|| year % 4 != 0) {
			return 28;
		} else {
			return 29;
		}
	}
	private static void intro() {
		System.out.println("Welcome to DateFacts! Give us a day or a set of days, "
			+ "and we'll find some cool facts about them!");
		System.out.println("Enter a set of days, and we'll tell you how many days are in that set!"
			+ "\n");
	}
	private static int[] getDate(Scanner userInput, String descriptor) {
		System.out.println("Please enter the " + descriptor + " date in the form dd/mm/yyyy");
		String date = userInput.nextLine();
		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(3, 5));
		int year = Integer.parseInt(date.substring(6, 10));
		int[] returnVals = {day, month, year};
		return returnVals;
	}
}
