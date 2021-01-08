package my.timetable.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import lebah.portal.action.Command;
import lebah.portal.action.LebahModule;
import my.timetable.CDay;
import my.timetable.CPeriod;
import my.timetable.TimetableConfig;
import my.timetable.entity.Activity;
import my.timetable.entity.ActivityAllowedDay;
import my.timetable.entity.ActivityAllowedPeriod;
import my.timetable.entity.ActivityAssignedTeacher;

public class TimetableSolverModule extends LebahModule {

	String path = "app/timetableSolver";
	
	
	@Override
	public String start() {
		listActivities();
		return path + "/start.vm";
	}
	
	@Command("listActivities")
	public String listActivities() {
		List<Activity> activities = db.list("select t from Activity t order by t.name");
		context.put("activities", activities);
		
		activities.stream().forEach(activity ->  TimetableSolver.activitySettingsData(db, activity) );
		
		mapperDayPeriod();
		
		return path + "/listActivities.vm";
	}
	
	private void mapperDayPeriod() {
		context.put("days", TimetableConfig.getDays());
		context.put("periods", TimetableConfig.getPeriods());
		
		Map<Integer, String> dayNameMapper = new HashMap<>();
		Map<Integer, String> periodNameMapper = new HashMap<>();
		
		for ( CDay day : TimetableConfig.getDays() ) {
			dayNameMapper.put(day.getDayNum(), day.getDayName());
		}
		
		for ( CPeriod period : TimetableConfig.getPeriods() ) {
			periodNameMapper.put(period.getPeriodNum(), period.getPeriodName());
		}
		
		context.put("dayNameMapper", dayNameMapper);
		context.put("periodNameMapper", periodNameMapper);
	}
	
	
	@Command("findSolution")
	public String findSolution() {
		
		List<TimetableSolver.ActivitySlot> activitySlots = TimetableSolver.solve(db);
		Map<Integer, List<Activity>> activitySlotMapper = new HashMap<Integer, List<Activity>>();
		context.put("activitySlotMapper", activitySlotMapper);
		activitySlots.stream().forEach(activitySlot -> {
			
			List<Activity> activities = activitySlotMapper.get(activitySlot.slotNum);
			if ( activities == null ) activities = new ArrayList<Activity>();
			activities.add(activitySlot.activity);
			
			activitySlotMapper.put(activitySlot.slotNum, activities);
			
		});
		
		context.put("days", TimetableConfig.getDays());
		context.put("periods", TimetableConfig.getPeriods());
		
		return path + "/findSolution.vm";
	}
	
	

}
