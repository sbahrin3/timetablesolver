package my.timetable;

import my.timetable.entity.Teacher;

public class CTeacher {
	
	private String id;
	private String name;
	
	private Teacher teacher;
	
	public CTeacher() { }
	
	public CTeacher(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CTeacher(String id, String name, Teacher teacher) {
		this.id = id;
		this.name = name;
		this.teacher = teacher;
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	

}
