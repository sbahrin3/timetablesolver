package my.timetable.module;

import java.util.List;

import lebah.portal.action.Command;
import lebah.portal.action.LebahModule;
import my.timetable.entity.Teacher;

public class SetupTeachersModule extends LebahModule { // LebahUserAccessModule {
	
	String path = "app/setupTeachers";
	
	@Override
	public String start() {
		listTeachers();
		return path + "/start.vm";
	}
	
	@Command("listTeachers")
	public String listTeachers() {
		List<Teacher> teachers = db.list("select t from Teacher t order by t.name");
		context.put("teachers", teachers);
		return path + "/listTeachers.vm";
	}
	
	@Command("addTeacher")
	public String addTeacher() {
		context.remove("teacher");
		return path + "/teacher.vm";
	}
	
	@Command("editTeacher")
	public String editTeacher() {
		String teacherId = getParam("teacher_id");
		Teacher teacher = db.find(Teacher.class, teacherId);
		context.put("teacher", teacher);
		return path + "/teacher.vm";
	}
	
	@Command("saveTeacher")
	public String saveTeacher() {
		String teacherId = getParam("teacher_id");
		boolean add = "".equals(teacherId);
		Teacher teacher = add ? new Teacher() : db.find(Teacher.class, teacherId);
		teacher.setCode(getParam("teacher_code"));
		teacher.setName(getParam("teacher_name"));
		db.ifAdd(add).saveOrUpdate(teacher);
		return listTeachers();
	}
	
	@Command("deleteTeacher")
	public String deleteTeacher() {
		String teacherId = getParam("teacher_id");
		Teacher teacher = db.find(Teacher.class, teacherId);
		db.delete(teacher);
		return listTeachers();
	}

}
