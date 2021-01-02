package my.timetable;

public class CPeriod {
	
	int periodNum;
	String periodName;
	
	public CPeriod(int n, String s) {
		periodNum = n;
		periodName = s;
	}
	
	public int getPeriodNum() {
		return periodNum;
	}
	public void setPeriodNum(int periodNum) {
		this.periodNum = periodNum;
	}
	public String getPeriodName() {
		return periodName;
	}
	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}
	
	

}
