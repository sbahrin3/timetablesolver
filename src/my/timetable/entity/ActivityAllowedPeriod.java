package my.timetable.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="activity_allowed_period")
public class ActivityAllowedPeriod {
	
	@Id @Column(length=50)
	private String id;
	
	@OneToOne
	private Activity activity;
	
	private int periodNum;
	
	public ActivityAllowedPeriod() {
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

	public int getPeriodNum() {
		return periodNum;
	}

	public void setPeriodNum(int periodNum) {
		this.periodNum = periodNum;
	}
	
	

}
