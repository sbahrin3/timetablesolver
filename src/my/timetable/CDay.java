package my.timetable;

public class CDay {
	
	int dayNum;
	String dayName;
	
	public CDay(int n, String s) {
		dayNum = n;
		dayName = s;
	}
	
	public int getDayNum() {
		return dayNum;
	}
	public void setDayNum(int dayNum) {
		this.dayNum = dayNum;
	}
	public String getDayName() {
		return dayName;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	
	

}
