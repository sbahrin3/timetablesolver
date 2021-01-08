package my.timetable.module;

import java.util.List;

import lebah.portal.action.Command;
import lebah.portal.action.LebahModule;
import my.timetable.entity.Venue;

public class SetupVenuesModule  extends LebahModule { // LebahUserAccessModule {
	
	String path = "app/setupVenues";
	
	@Override
	public String start() {
		listVenues();
		return path + "/start.vm";
	}
	
	@Command("listVenues")
	public String listVenues() {
		List<Venue> venues = db.list("select t from Venue t order by t.name");
		context.put("venues", venues);
		return path + "/listVenues.vm";
	}
	
	@Command("addVenue")
	public String addVenue() {
		context.remove("venue");
		return path + "/venue.vm";
	}
	
	@Command("editVenue")
	public String editVenue() {
		String venueId = getParam("venue_id");
		Venue venue = db.find(Venue.class, venueId);
		context.put("venue", venue);
		return path + "/venue.vm";
	}
	
	@Command("saveVenue")
	public String saveVenue() {
		String venueId = getParam("venue_id");
		boolean add = "".equals(venueId);
		Venue venue = add ? new Venue() : db.find(Venue.class, venueId);
		venue.setCode(getParam("venue_code"));
		venue.setName(getParam("venue_name"));
		db.ifAdd(add).saveOrUpdate(venue);
		return listVenues();
	}
	
	@Command("deleteVenue")
	public String deleteVenue() {
		String venueId = getParam("venue_id");
		Venue venue = db.find(Venue.class, venueId);
		db.delete(venue);
		return listVenues();
	}

}
