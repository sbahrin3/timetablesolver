package my.timetable;

import java.util.ArrayList;
import java.util.List;

public class TimetableConfig {
	
	static List<CPeriod> periods = new ArrayList<CPeriod>();
	static List<CDay> days = new ArrayList<CDay>();
	
	static {
		
		String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
		String[] periodNames = {"8 am - 9 am", "9 am - 10 am", "10 am - 11 am", "11 am - 12 pm", "12 pm - 1 pm" };
		
		for ( int i=0; i < dayNames.length; i++ ) {
			days.add(new CDay(i, dayNames[i]));
		}
		for ( int i=0; i < periodNames.length; i++ ) {
			periods.add(new CPeriod(i, periodNames[i]));
		}
	}
	
	public static List<CPeriod> getPeriods() {
		return periods;
	}
	
	public static List<CDay> getDays() {
		return days;
	}

}
