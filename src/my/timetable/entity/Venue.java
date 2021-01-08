package my.timetable.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="venue")
public class Venue {
	
	@Id @Column(length=50)
	private String id;
	@Column(length=50)
	private String name;
	@Column(length=10)
	private String code;
	
	private int venueNum;
	
	public Venue() {
		setId(lebah.util.UIDGenerator.getUUID());
	}
	
	public int getVenueNum() {
		return venueNum;
	}
	public void setVenueNum(int venueNum) {
		this.venueNum = venueNum;
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

	

}
