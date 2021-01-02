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
		
		activities.stream().forEach(activity ->  activitySettingsData(activity) );
		
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
	
	private void activitySettingsData(Activity activity) {
		List<ActivityAssignedTeacher> teacherList = db.list("select t from ActivityAssignedTeacher t where t.activity.id = '" + activity.getId() + "'");
		activity.setAssignedTeachers(teacherList.stream().map(e -> e.getTeacher()).collect(Collectors.toList()));
		
		List<ActivityAllowedDay> dayList = db.list("select t from ActivityAllowedDay t where t.activity.id = '" + activity.getId() + "'");
		activity.setAllowedDays(dayList.stream().map(e -> e.getDayNum()).collect(Collectors.toList()));
		
		List<ActivityAllowedPeriod> periodList = db.list("select t from ActivityAllowedPeriod t where t.activity.id = '" + activity.getId() + "'");
		activity.setAllowedPeriods(periodList.stream().map(e -> e.getPeriodNum()).collect(Collectors.toList()));
	}
	
	@Command("findSolution")
	public String findSolution() {
		
		Optional<List<TimetableSolver.ActivitySlot>> optActivitySlots = Optional.of(TimetableSolver.solve(db).get(0));
		optActivitySlots.ifPresent(activitySlots -> {
			Map<Integer, List<Activity>> activitySlotMapper = new HashMap<Integer, List<Activity>>();
			context.put("activitySlotMapper", activitySlotMapper);
			activitySlots.stream().forEach(activitySlot -> {
				
				List<Activity> activities = activitySlotMapper.get(activitySlot.slotNum);
				if ( activities == null ) activities = new ArrayList<Activity>();
				activities.add(activitySlot.activity);
				
				activitySlotMapper.put(activitySlot.slotNum, activities);
				
			});
		});
		
		
		context.put("days", TimetableConfig.getDays());
		context.put("periods", TimetableConfig.getPeriods());
		
		return path + "/findSolution.vm";
	}
	
	@Command("findManySolutions")
	public String findManySolutions() {
		
		List<Map<Integer, List<Activity>>> activitySlotMapperList = new ArrayList<>();
		context.put("activitySlotMapperList", activitySlotMapperList);
		
		Optional<List<List<TimetableSolver.ActivitySlot>>> optListActivitySlots = Optional.of(TimetableSolver.solve(db));
		optListActivitySlots.ifPresent(listActivitySlots -> {
			
			int cnt = 0;
			for ( List<TimetableSolver.ActivitySlot> activitySlots : listActivitySlots ) {
				Map<Integer, List<Activity>> activitySlotMapper = new HashMap<Integer, List<Activity>>();
				activitySlotMapperList.add(activitySlotMapper);
				
				activitySlots.stream().forEach(activitySlot -> {
					
					List<Activity> activities = activitySlotMapper.get(activitySlot.slotNum);
					if ( activities == null ) activities = new ArrayList<Activity>();
					activities.add(activitySlot.activity);
					
					activitySlotMapper.put(activitySlot.slotNum, activities);
					
				});
				
				cnt++;
				if ( cnt > 19 ) break;
			}
			
			
		});
		
		
		context.put("days", TimetableConfig.getDays());
		context.put("periods", TimetableConfig.getPeriods());
		
		return path + "/findManySolutions.vm";
	}

}
