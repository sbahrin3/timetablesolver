package my.timetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

public class TestManualTimetable {


	public static void main(String[] args) {


		int[] allDays = new int[] { 0,1,2,3,4 };
		int[] allPeriods = new int[] {0,1,2,3,4 };
		int[] allVenues = new int[] { 0,1,2,3 };

		int totalPeriod = allPeriods.length;
		int totalVenue = allVenues.length;

		Map<String, Integer> slotNumMapper = new HashMap<>();
		int slotNum = 0;
		for ( int dayNum : allDays ) {
			for ( int periodNum : allPeriods ) {
				slotNumMapper.put(dayNum + "-" + periodNum, slotNum);
				slotNum++;
			}
			
		}



		CActivity a1 = new CActivity("A1");
		CActivity a2 = new CActivity("A2");
		CActivity a3 = new CActivity("A3");

		a1.setAllowedDays(new int[] { 0,1,2 });
		a2.setAllowedDays(new int[] { 0,1,2 });
		a3.setAllowedDays(new int[] { 0,1 });

		a1.setAllowedPeriods(new int[] { 0,1 });
		a2.setAllowedPeriods(new int[] { 0,1 });
		a3.setAllowedPeriods(new int[] { 0,1 });

		List<CActivity> activities = Arrays.asList(a1, a2, a3);

		activities.stream().forEach(a -> {

			int totalActivitySlots = a.getAllowedDays().length * a.getAllowedPeriods().length * totalVenue;
			int[] activitySlots = new int[totalActivitySlots];
			int i=0;
			for ( int dayNum : a.getAllowedDays() ) {
				for ( int periodNum : a.getAllowedPeriods() ) {
					activitySlots[i] = slotNumMapper.get(dayNum + "-" + periodNum);
					i++;
				}
				
			}

			a.setAllowedSlots(activitySlots);

		}); 



		CTeacher t1 = new CTeacher("T1", "Teacher 1");
		CTeacher t2 = new CTeacher("T2", "Teacher 2");
		CTeacher t3 = new CTeacher("T3", "Teacher 3");
		CTeacher t4 = new CTeacher("T4", "Teacher 4");


		a1.addTeachers(t1);
		a2.addTeachers(t1);
		a3.addTeachers(t3);

		Model model = new Model("Classvenue Solver");

		//initialize unknowns
		IntVar[] activityVars = new IntVar[activities.size()];
		for ( int i=0; i < activities.size(); i++ ) {
			activityVars[i] = model.intVar(activities.get(i).getId(), activities.get(i).getAllowedSlots());
		}

		for ( int i=0; i < activities.size(); i++ ) {
			List<CTeacher> teachers = activities.get(i).getTeachers();
			for ( int j = i + 1; j < activities.size(); j++ ) {
				List<CTeacher> teachers2 = activities.get(j).getTeachers();
				for ( CTeacher teacher1 : teachers ) {
					for ( CTeacher teacher2 : teachers2 ) {
						//a teacher can not be in one slot at a time
						if ( teacher1.equals(teacher2)) {
							model.arithm(activityVars[i], "!=", activityVars[j]).post();
							break;
						}
					}
				}

			}
		}
		
				
		List<Solution> solutions = model.getSolver().findAllSolutions();
		int i=0;
		for ( Solution solution : solutions ) {
			System.out.println(i + ") " + solution.toString());
			i++;
			if ( i > 40 ) break;
		}
		
		System.out.println("=====");
		
		int index = 0;
		Map<Integer, List<CActivity>> slotActivitiesMapper = new HashMap<>();
		
		Solution solution = solutions.get(index);
		for ( int j=0; j < activities.size(); j++ ) {
			
			int slot = solution.getIntVal(activityVars[j]);
			List<CActivity> list = slotActivitiesMapper.get(slot);
			if ( list == null ) {
				list = new ArrayList<CActivity>();
				slotActivitiesMapper.put(slot, list);
			}
			list.add(activities.get(j));
			
		}
		
		//Venues for each activity in each slot
		//Let say we have 3 Venues { 0,1,2 }
		
		for ( int slot = 0; slot < 2; slot++ ) {
			
			List<CActivity> list = slotActivitiesMapper.get(slot);
			System.out.println("slot " + slot);
			
			Model venueModel = new Model("Venue Model");
			IntVar[] venueVars = new IntVar[list.size()];
			for ( int j=0; j < list.size(); j++ ) {
				venueVars[j] = venueModel.intVar(list.get(j).getName(), new int[] { 0,1,2 });
			}
			
			//create constraint
			venueModel.allDifferent(venueVars).post();;

			int cnt = 0;
			List<Solution> venueSols = venueModel.getSolver().findAllSolutions();
			for ( Solution venueSol : venueSols ) {
				System.out.println(venueSol);
				if ( cnt++ > 5 ) break;
			}
			
		}
		
		


	}

}
