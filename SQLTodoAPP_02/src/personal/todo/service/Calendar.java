package personal.todo.service;
// calendar 알고리즘 참고
public class Calendar {
		public static int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		public static int[] leap_days = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		public static void printCalendar(int year, int month) {
			int firstd = 0, lastd = 0;
			int count  = 0, sumd = 0;
			int st_year = 1970, st_week = 4;
			
			for (int i = st_year; i < year; i++) {
				if(i % 4 == 0 && (i % 100 != 0 || i % 400 == 0))
					sumd+=366;
				else
					sumd+=365;				
			}
			for (int i = 1; i < month; i++) {
				if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
					sumd += leap_days[i-1];
				else
					sumd += days[i-1];
			}
			if ((year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))) {
				lastd = leap_days[month - 1];
			} else {
				lastd = days[month - 1];
			}
			firstd = (st_week+sumd) % 7;
			
			System.out.printf("%4d년 %d월\n", year, month);
			System.out.println(" SUN MON TUE WED THU FRI SAT");
			System.out.println(" ---------------------------");
			for (int i = 0; i < firstd; i++) {
				System.out.print("    ");
				count++;
			}
			for (int i = 1; i <= lastd; i++) {
				System.out.printf("%4d", i);
				count++;
				if (count % 7 == 0) {
					System.out.println();
				}
			}
			System.out.println();
			System.out.println();
		}

}
