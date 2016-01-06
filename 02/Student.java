class Student {
	private final String name;
	private int numTasks;
	
	public Student(String name) {
		this.name = name;
		numTasks = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumTasks() {
		return numTasks;
	}
	
	public void setNumTasks(int numTasks) {
		if (numTasks < 0) {
			throw new IllegalArgumentException("setNumTask can not take a negative number as parameter");
		}
		this.numTasks = numTasks;
	}
	
	public String toString() {
		return "Navn: " + name + " Oppgaver: " + numTasks;
	}
}