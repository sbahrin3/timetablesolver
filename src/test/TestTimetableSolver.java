package test;

import java.util.List;
import java.util.Optional;

import lebah.db.entity.Persistence;
import my.timetable.module.TimetableSolver;

public class TestTimetableSolver {
	


	public static void main(String[] args) {

		Persistence db = Persistence.db();
		
		//List<TimetableSolver.ActivitySlot> activitySlots =  TimetableSolver.solve(db);
		Optional<List<TimetableSolver.ActivitySlot>> opt = Optional.of(TimetableSolver.solve(db).get(0));
		opt.ifPresent( activitySlots -> {
		
			activitySlots.stream().forEach(activitySlot -> {
				
				System.out.println(activitySlot.activity.getCode() + " = " + activitySlot.slotNum);
				
			});
		});
		
		
		db.close();
		
		
	}
	
	

}
