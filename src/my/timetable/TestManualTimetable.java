package my.timetable;

import java.util.Arrays;
import java.util.List;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

public class TestManualTimetable {
	
	public static void main(String[] args) {
		
		CActivity a1 = new CActivity("A1");
		CActivity a2 = new CActivity("A2");
		CActivity a3 = new CActivity("A3");
		
		a1.setAllowedSlots(new int[] {1, 2, 3, 4, 5});
		a2.setAllowedSlots(new int[] {1, 2, 3, 4, 5});
		a3.setAllowedSlots(new int[] {1, 2, 3, 4, 5});
		
		CTeacher t1 = new CTeacher("T1", "Teacher 1");
		CTeacher t2 = new CTeacher("T2", "Teacher 2");
		CTeacher t3 = new CTeacher("T3", "Teacher 3");
		CTeacher t4 = new CTeacher("T4", "Teacher 4");
		
		
		a1.addTeachers(t1, t4);
		a2.addTeachers(t1, t2);
		a3.addTeachers(t3);
		
		
		Model model = new Model("Test Classroom Scheduler");
		List<CActivity> activities = Arrays.asList(a1, a2, a3);
		
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
		for ( Solution solution : solutions ) {
			System.out.println(solution.toString());
		}
		
	}

}
