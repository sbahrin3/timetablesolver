package my.timetable.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author shamsulbahrin
 *
 */

@Entity
@Table(name="activity")
public class Activity {
	
	@Id @Column(length=50)
	private String id;
	private String name;
	@Column(length=10)
	private String code;
	@OneToOne
	private Venue venue;
	
	@Transient
	private List<Teacher> assignedTeachers;
	@Transient
	private List<Integer> allowedDays;
	@Transient
	private List<Integer> allowedPeriods;
	@Transient
	private List<Integer> allowedVenues;
	@Transient
	private int venueNum = -1;
	
	public Activity() {
		setId(lebah.util.UIDGenerator.getUUID());
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Teacher> getAssignedTeachers() {
		return assignedTeachers;
	}

	public void setAssignedTeachers(List<Teacher> assignedTeachers) {
		this.assignedTeachers = assignedTeachers;
	}

	public List<Integer> getAllowedDays() {
		return allowedDays;
	}

	public void setAllowedDays(List<Integer> allowedDays) {
		Collections.sort(allowedDays);
		this.allowedDays = allowedDays;
	}

	public List<Integer> getAllowedPeriods() {
		Collections.sort(allowedPeriods);
		return allowedPeriods;
	}

	public void setAllowedPeriods(List<Integer> allowedPeriods) {
		this.allowedPeriods = allowedPeriods;
	}

	public List<Integer> getAllowedVenues() {
		return allowedVenues;
	}

	public void setAllowedVenues(List<Integer> allowedVenues) {
		Collections.sort(allowedVenues);
		this.allowedVenues = allowedVenues;
	}

	public int getVenueNum() {
		return venueNum;
	}

	public void setVenueNum(int venueNum) {
		this.venueNum = venueNum;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	
}
