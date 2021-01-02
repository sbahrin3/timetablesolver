package my.timetable;

import java.util.ArrayList;
import java.util.List;

import my.timetable.entity.Activity;

public class CActivity {
	
	private String id;
	private String name;
	private int[] allowedSlots;
	private int[] allowedDays;
	private List<CTeacher> teachers;
	private Activity activity;
	
	public CActivity() { }
	
	public CActivity(String name) {
		this.name = name;
		this.id = name;
	}
	public CActivity(String name, Activity activity) {
		this.name = name;
		this.id = name;
		this.activity = activity;
	}	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int[] getAllowedSlots() {
		return allowedSlots;
	}
	public void setAllowedSlots(int[] allowedSlots) {
		this.allowedSlots = allowedSlots;
	}
	public int[] getAllowedDays() {
		return allowedDays;
	}
	public void setAllowedDays(int[] allowedDays) {
		this.allowedDays = allowedDays;
	}

	public List<CTeacher> getTeachers() {
		if ( teachers == null ) teachers = new ArrayList<CTeacher>();
		return teachers;
	}

	public void setTeachers(List<CTeacher> teachers) {
		this.teachers = teachers;
	}
	
	public void addTeachers(CTeacher... teachers) {
		for ( CTeacher t : teachers ) {
			getTeachers().add(t);
		}
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	

}
