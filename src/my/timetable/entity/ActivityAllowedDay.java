package my.timetable.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="activity_allowed_day")
public class ActivityAllowedDay {
	
	@Id @Column(length=50)
	private String id;
	
	@OneToOne
	private Activity activity;
	
	public ActivityAllowedDay() {
		setId(lebah.util.UIDGenerator.getUUID());
	}
	
	private int dayNum;

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

	public int getDayNum() {
		return dayNum;
	}

	public void setDayNum(int dayNum) {
		this.dayNum = dayNum;
	}
	
	

}
