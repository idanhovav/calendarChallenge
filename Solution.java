import java.io.*;
import java.util.*;

/* Idea:
create a date object. has day of week (how, where to start?)

Manipulation of what days to add to hashmap.

Create a hashmap of dates and a fun fact scraped from wikipedia about that day.



*/

// EVERYTHING in the form of day month year


public class Solution {
	public static final int MONTHS_IN_YEAR = 12;
	public static final int DAYS_IN_WEEK = 7;
	public static void main(String[] args) {
		ArrayList<Date> dates = new ArrayList();
		Scanner userInput = new Scanner(System.in);
		String response = getDayOrSet(userInput);
		int startYear, endYear, startMonth, endMonth, startDay, endDay;
		int[] startDate, endDate;
		// check inputs
		if (response.equals("day")) {
			startDate = getDate(userInput, "specific");
			endDate = startDate;
		} else {
			int[] startDate = getDate(userInput, "starting");
			int[] endDate = getDate(userInput, "ending");
		}
		startDay = startDate[0];
		startMonth = startDate[1];
		startYear = startDate[2];
		endDay = endDate[0];
		endMonth = endDate[1];
		endYear = endDate[2];

		addDaysToList(dates, startYear, endYear, startMonth, endMonth, startDay, endDay);
		// now we have the days in a hashset.

		printList(dates);
	}
	private static void addDaysToList(ArrayList<Date> dates, int startYear, int endYear, int startMonth,
		int endMonth, int startDay, int endDay) {
		int[] mLimits, dLimits; //monthLimits and dayLimits, respectively.
		int yearlyStartMonth;
		int yearlyEndMonth;
		int monthlyStartDay;
		int monthlyEndDay;

		for (int year = startYear; year <= endYear; year++) {
			mLimits = monthLimits(year, startYear, endYear, startMonth, endMonth);
			yearlyStartMonth = limits[0];
			yearlyEndMonth = limits[1];
			for (int month = yearlyStartMonth; month <= yearlyEndMonth; month++) {
				dLimits = dayLimits(month, year, startYear, endYear, startMonth,
					endMonth, startDay, endDay);
				monthlyStartDay = dLimits[0];
				monthlyEndDay = dLimits[1];
				for (int day = monthlyStartDay; day <= monthlyEndDay; day++) {
					dates.add(new Date(day, month, year));
				}
			}
		}
	}
	private static int[] dayLimits(int month, int year, int startYear, int endYear,
		int startMonth, int endMonth, int startDay, int endDay) {
		int[] limits = new int[2];
		int monthlyStartDay = 1;
		int monthlyEndDay = daysInMonth(month, year);
		if (month == startMonth && year == startYear) {
			monthlyStartDay = startDay;
		} else if (month == endMonth && year == endYear) {
			monthlyEndDay = endDay;
		}
		limits[0] = monthlyStartDay;
		limits[1] = monthlyEndDay;
		return limits;
	}
	private static int[] monthLimits(int year, int startYear, int endYear,
		int startMonth, int endMonth) {
		int[] limits = new int[2];
		int yearlyStartMonth = 1;
		int yearlyEndMonth = 12;
		if (year == startYear) {
			yearlyStartMonth = startMonth;
		} else if (year == endYear) {
			yearlyEndMonth = endMonth;
		}
		limits[0] = yearlyStartMonth;
		limits[1] = yearlyEndMonth;
		return limits;
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
	private static String getDayOrSet(Scanner userInput) {
		System.out.println("Welcome to DateFacts! Give us a day or a set of days, 
			and we'll find some cool facts about them!");
		System.out.println("If you'd like to learn about a specific day, enter 'day'. \n
			If you want to learn about a set of days, enter 'set'");
		String response = userInput.nextLine();
		return response;
	}
	private static int[] getDate(Scanner userInput, String descriptor) {
		System.out.println("Please enter the " + descriptor + " date in the form dd/mm/yyyy");
		String date = userInput.nextLine();
		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(3, 5));
		int year = Integer.parseInt(date.substring(6, 10));
		return new int[3]{day, month, year};
	}
	private static void printList(ArrayList<Date> dates) {
		for (date : dates) {
			System.out.println(date.toString() + " " + dates.hashCode());
		}
	}
}
