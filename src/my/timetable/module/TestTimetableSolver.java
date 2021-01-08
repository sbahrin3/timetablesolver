package my.timetable.module;

import java.util.List;
import java.util.Optional;

import lebah.db.entity.Persistence;

public class TestTimetableSolver {
	


	public static void main(String[] args) {

		Persistence db = Persistence.db();
		
		List<TimetableSolver.ActivitySlot> activitySlots = TimetableSolver.solve(db);
		
		
	
		activitySlots.stream().forEach(activitySlot -> {
			
			System.out.println(activitySlot.activity.getCode() + " = " + activitySlot.slotNum + ", " + activitySlot.activity.getVenue());
			
		});
		
		
		
		db.close();
		
		
	}
	
	

}
