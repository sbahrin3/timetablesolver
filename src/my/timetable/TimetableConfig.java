package my.timetable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimetableConfig {
	
	static List<CPeriod> periods = new ArrayList<CPeriod>();
	static List<CDay> days = new ArrayList<CDay>();
	static Map<String, Integer> slotNumMapper = new HashMap<>();
	
	static {
		
		
		String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
		String[] periodNames = {"8 am - 9 am", "9 am - 10 am", "10 am - 11 am", "11 am - 12 pm", "12 pm - 1 pm" };
		int totalDay = dayNames.length;
		int totalPeriod = periodNames.length;
		
		for ( int i=0; i < dayNames.length; i++ ) {
			days.add(new CDay(i, dayNames[i]));
		}
		for ( int i=0; i < periodNames.length; i++ ) {
			periods.add(new CPeriod(i, periodNames[i]));
		}
		
		
		int sn = 0;
		for ( int dayNum = 0; dayNum < totalDay; dayNum++ ) {
			for ( int periodNum = 0; periodNum < totalPeriod; periodNum++ ) {
				slotNumMapper.put(dayNum + "-" + periodNum, sn);
				sn++;
			}
			
		}
	}
	
	public static List<CPeriod> getPeriods() {
		return periods;
	}
	
	public static List<CDay> getDays() {
		return days;
	}

	public static Map<String, Integer> getSlotNumMapper() {
		return slotNumMapper;
	}
	
	public static int slotNum(int dayNum, int periodNum) {
		return slotNumMapper.get(dayNum + "-" + periodNum);
	}
	
	public static int totalSlots() {
		return days.size() * periods.size();
	}

}
