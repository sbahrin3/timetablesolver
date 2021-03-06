package my.timetable.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

import lebah.db.entity.Persistence;
import my.timetable.CActivity;
import my.timetable.CTeacher;
import my.timetable.TimetableConfig;
import my.timetable.entity.Activity;
import my.timetable.entity.ActivityAllowedDay;
import my.timetable.entity.ActivityAllowedPeriod;
import my.timetable.entity.ActivityAssignedTeacher;
import my.timetable.entity.Venue;

/**
 * 
 * @author shamsulbahrin
 *
 */

public class TimetableSolver {
	
	public static void activitySettingsData(Persistence db, Activity activity) {
		List<ActivityAssignedTeacher> teacherList = db.list("select t from ActivityAssignedTeacher t where t.activity.id = '" + activity.getId() + "'");
		activity.setAssignedTeachers(teacherList.stream().map(e -> e.getTeacher()).collect(Collectors.toList()));
		
		List<ActivityAllowedDay> dayList = db.list("select t from ActivityAllowedDay t where t.activity.id = '" + activity.getId() + "'");
		activity.setAllowedDays(dayList.stream().map(e -> e.getDayNum()).collect(Collectors.toList()));
		
		List<ActivityAllowedPeriod> periodList = db.list("select t from ActivityAllowedPeriod t where t.activity.id = '" + activity.getId() + "'");
		activity.setAllowedPeriods(periodList.stream().map(e -> e.getPeriodNum()).collect(Collectors.toList()));
		
		//hardcoded venues temprorarily
		activity.setAllowedVenues(Arrays.asList(new Integer[] { 0,1,2,3 }));
	}
	
	public static class ActivitySlot {
		
		public final Activity activity;
		public final int slotNum;
		
		public ActivitySlot(Activity a, int n) {
			activity = a;
			slotNum = n;
		}
		
	}
	
	
	public static List<ActivitySlot> solve(Persistence db) {
		
		Model model = new Model("Timetable Generation Model");
		
		List<Activity> activities = db.list("select a from Activity a");
		List<CActivity> classActivities = new ArrayList<CActivity>();
		activities.stream().forEach(activity -> {
			
			activitySettingsData(db, activity);
			
			CActivity classActivity = new CActivity(activity.getCode(), activity);	
			
			//Let's assign allowed slot numbers to each activities
			List<Integer> allowedPeriods = activity.getAllowedPeriods();
			List<Integer> allowedDays = activity.getAllowedDays();
			int totalAllowedSlots = allowedPeriods.size() * allowedDays.size();
			int[] slots = new int[totalAllowedSlots];
			int i=0;
			
			for ( int dayNum : allowedDays ) {
				for ( int periodNum : allowedPeriods ) {
					slots[i] = TimetableConfig.slotNum(dayNum, periodNum);
					i++;
				}
			}
						
			classActivity.setAllowedSlots(slots);
			classActivity.setTeachers(
				activity.getAssignedTeachers().stream().map(teacher -> new CTeacher(teacher.getCode(), teacher.getName(), teacher)).collect(Collectors.toList())
			);
			classActivity.setVenue(activity.getVenue());
			
			classActivities.add(classActivity);
			
		});
		
		//initialize unknowns
		IntVar[] activityVars = new IntVar[classActivities.size()];
		for ( int i=0; i < classActivities.size(); i++ ) {
			activityVars[i] = model.intVar(classActivities.get(i).getId(), classActivities.get(i).getAllowedSlots());
		}
		
		for ( int i=0; i < classActivities.size()-1; i++ ) {
			List<CTeacher> teachers = classActivities.get(i).getTeachers();
			for ( int j = i+1; j < classActivities.size(); j++ ) {
				List<CTeacher> teachers2 = classActivities.get(j).getTeachers();
				for ( CTeacher teacher1 : teachers ) {
					for ( CTeacher teacher2 : teachers2 ) {
						//a teacher can not be in one slot at a time
						if ( teacher1.getId().equals(teacher2.getId())) {
							model.arithm(activityVars[i], "!=", activityVars[j]).post();
							break;
						}
					}
				}
			}
		}
		for ( int i=0; i < classActivities.size()-1; i++ ) {
			Venue v1 = classActivities.get(i).getVenue();
			if ( v1 != null ) {
				for ( int j = i+1; j < classActivities.size(); j++ ) {
					Venue v2 = classActivities.get(j).getVenue();
					if ( v2 != null ) {
						if ( v1.getId().equals(v2.getId() ) ) {
							model.arithm(activityVars[i], "!=", activityVars[j]).post();
							break;
						}
					}
				}
			}
		}
		
		List<List<ActivitySlot>> solutionList = new ArrayList<>();
		
		int cnt = 0;
		List<Solution> solutions = model.getSolver().findAllSolutions();
		for ( Solution s : solutions ) {		
			List<ActivitySlot> activitySlots = new ArrayList<ActivitySlot>();
			for ( int i=0; i < classActivities.size(); i++ ) {
		    	CActivity classActivity = classActivities.get(i);
		    	int slotNum = s.getIntVal(activityVars[i]);
		    	ActivitySlot activityslot = new ActivitySlot(classActivity.getActivity(), slotNum);
		    	activitySlots.add(activityslot);
		    }
			solutionList.add(activitySlots);
			if ( cnt++ > 50 ) break;  //allow up to 10 solutions
		}
		
		//randomly pick a solution
		int index = new Random().nextInt(solutionList.size());		
		List<ActivitySlot> activitySlotList = solutionList.get(index);
		
		return activitySlotList;

	}
	
	private static boolean isVenueAssigned(int vnum, List<ActivitySlot> thisSlotActivities) {
		
		return thisSlotActivities.stream().filter(activitySlot -> activitySlot.activity.getVenueNum() == vnum).findFirst().isPresent();
		
	}


}
