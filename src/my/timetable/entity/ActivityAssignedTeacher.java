package my.timetable.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="activity_assigned_teacher")
public class ActivityAssignedTeacher {
	
	@Id @Column(length=50)
	private String id;
	
	@OneToOne
	private Activity activity;
	@OneToOne
	private Teacher teacher;
	
	public ActivityAssignedTeacher() {
		setId(lebah.util.UIDGenerator.getUUID());
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	

}
