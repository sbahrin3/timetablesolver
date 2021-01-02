package my.timetable.module;

import java.util.Arrays;
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
import my.timetable.entity.Teacher;

/**
 * 
 * @author shamsulbahrin
 *
 */

public class SetupActivitiesModule extends LebahModule { // LebahUserAccessModule {
	
	String path = "app/setupActivities";
	
	
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
	
	@Command("addActivity")
	public String addActivity() {
		context.remove("activity");
		return path + "/activity.vm";
	}
	
	@Command("editActivity")
	public String editActivity() {
		String activityId = getParam("activity_id");
		Activity activity = db.find(Activity.class, activityId);
		context.put("activity", activity);
		return path + "/activity.vm";
	}
	
	@Command("saveActivity")
	public String saveActivity() {
		String activityId = getParam("activity_id");
		boolean add = "".equals(activityId);
		Activity activity = add ? new Activity() : db.find(Activity.class, activityId);
		activity.setCode(getParam("activity_code"));
		activity.setName(getParam("activity_name"));
		db.ifAdd(add).saveOrUpdate(activity);
		return listActivities();
	}
	
	@Command("deleteActivity")
	public String deleteActivity() {
		String activityId = getParam("activity_id");
		Activity activity = db.find(Activity.class, activityId);
		db.delete(activity);
		return listActivities();
	}
	
	@Command("settings")
	public String settings() {
		String activityId = getParam("activity_id");
		Activity activity = db.find(Activity.class, activityId);
		context.put("activity", activity);
		
		activitySettingsData(activity);
		
		List<Teacher> teachers = db.list("select t from Teacher t order by t.name");
		context.put("teachers", teachers);
		
		mapperDayPeriod();
		
		return path + "/settings.vm";
	}

	@Command("saveSettings")
	public String saveSettings() {
		String activityId = getParam("activity_id");
		Activity activity = db.find(Activity.class, activityId);
		
		db.execute("delete from ActivityAssignedTeacher t where t.activity.id = '" + activity.getId() + "'");
		Optional<String[]> requestTeacherIds = Optional.of(request.getParameterValues("teachers"));
		requestTeacherIds.ifPresent(teacherIds -> {
			Arrays.asList(teacherIds).stream().forEach( id -> {
				ActivityAssignedTeacher assignedTeacher = new ActivityAssignedTeacher();
				assignedTeacher.setActivity(activity);
				assignedTeacher.setTeacher(db.find(Teacher.class, id));
				db.save(assignedTeacher);
				
			});
		});
		
		
		db.execute("delete from ActivityAllowedDay t where t.activity.id = '" + activity.getId() + "'");
		Optional<String[]> requestDayNums = Optional.of(request.getParameterValues("days"));
		requestDayNums.ifPresent(dayNums -> {
			Arrays.asList(dayNums).stream().forEach( dayNum -> {
				ActivityAllowedDay allowedDay = new ActivityAllowedDay();
				allowedDay.setActivity(activity);
				allowedDay.setDayNum(Integer.parseInt(dayNum));
				db.save(allowedDay);
			});
		});
		
		
		db.execute("delete from ActivityAllowedPeriod t where t.activity.id = '" + activity.getId() + "'");
		Optional<String[]> requestPeriodNums = Optional.of(request.getParameterValues("periods"));
		requestPeriodNums.ifPresent(periodNums -> {
			Arrays.asList(periodNums).stream().forEach( periodNum -> {
				ActivityAllowedPeriod allowedPeriod = new ActivityAllowedPeriod();
				allowedPeriod.setActivity(activity);
				allowedPeriod.setPeriodNum(Integer.parseInt(periodNum));
				db.save(allowedPeriod);
			});
		});
		
		return settings();
	}

}
