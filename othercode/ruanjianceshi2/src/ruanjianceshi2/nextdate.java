package ruanjianceshi2;

import java.util.Scanner;

import javax.print.attribute.standard.RequestingUserName;

public class nextdate {
	public int nextDate(int year, int month, int day) {
		int flag=1;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
			if (day >= 1 && day < 31)
				day = day + 1;
			else if (day == 31) {
				day = 1;
				month = month + 1;
				
			} else {
				
				flag=0;
				
			}
		case 12:
			if (day >= 1 && day < 31)
				day = day + 1;
			else if (day == 31) {
				day = 1;
				month = 1;
				year = year + 1;
			}

			else {
				flag=0;
				
			}
		case 4:
		case 6:
		case 9:
		case 11:
			if (day >= 1 && day < 30)
				day = day + 1;
			else if (day == 30) {
				day = 1;
				month = month + 1;
			} else {
				flag=0;
				
			}

		case 2:
			if (day >= 1 && day < 28)
				day = day + 1;
			else if (!leap(year) && day == 28) {
				day = 1;
				month = month + 1;
			} else if (leap(year) && day == 28)
				day = day + 1;
			else if (leap(year) && day == 29) {
				day = 1;
				month = month + 1;
			} else {
				flag=0;
				
			}
		}
		return flag;
	}
public boolean leap(int year) {
		return ( year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
	}
	

}
