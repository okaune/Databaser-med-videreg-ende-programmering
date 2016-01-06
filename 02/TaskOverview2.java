import java.util.*;

class TaskOverview2 {
	private ArrayList<Student> students = new ArrayList<Student>();
	private int numStud = 0;
	
	public boolean regNewStudent(String name) {
		for (Student student : students) {
			if (student.getName().equals(name)) {
				return false;
			}
		}
		students.add(new Student(name));
		numStud++;
		return true;
	}
	
	public int getNumStud() {
		return numStud;
	}
	
	public int getNumTasks(String navn) {
		for (Student student : students) {
			if (student.getName().equals(navn)) {
				return student.getNumTasks();
			}
		}
		return -1;
	}
	
	public void increaseNumTasks(String navn, int increase) {
		for (Student student : students) {
			if (student.getName().equals(navn)) {
				student.setNumTasks(student.getNumTasks() + increase);
			}
		}
	}
	
	public String[] getAllNames() {
		String[] names = new String[numStud];
		for (int i = 0; i < numStud; i++) {
			names[i] = students.get(i).getName();
		}
		return names;
	}
	
	public String toString() {
		String output = "";
		for (Student student : students) {
			output += student + "\n";
		}
		return output;
	}
}