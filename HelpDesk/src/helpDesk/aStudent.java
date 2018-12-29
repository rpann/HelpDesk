package helpDesk;

import java.util.ArrayList;

public class aStudent {
	String name;
	int course;
	int minutesNeeded;
	
	public aStudent() {
		name = "";
		course = 0;
		minutesNeeded = 0;
	}
	
	/**
	 * @param names
	 * @param courseNumber
	 * @param helpNeeded
	 */
	public aStudent(String names, int courseNumber, int helpNeeded) {
		name = names;
		course = courseNumber;
		minutesNeeded = helpNeeded;
	}

	public int getCourse() {
		return course;
	}
	public String getName() {
		return name;
	}
	public void setCourse(int course) {
		this.course = course;
	}

	public int getMinutesNeeded() {
		return minutesNeeded;
	}

	public void setMinutesNeeded(int minutesNeeded) {
		this.minutesNeeded = minutesNeeded;
	}
	//this is when the helpDesk starts
	public class HelpDesk2 {
		int baseIndex = -1;
		int isHelping = 0;
		int n = 0;
		int numberInWaitlist;
		DSLinkedStack list = new DSLinkedStack();
		DSLinkedStack flags = new DSLinkedStack();
		aStudent previousStudent = new aStudent("", 0, 0);
		aStudent currentStudent;
		int amountOfWork;
		ArrayList<aStudent> waitlist = new ArrayList<aStudent>();
		
		public HelpDesk2() {
			//this constructor essentially does nothing, like literally nothing at all....
		}
		
		public void step() 
		{
			baseIndex++;
			
		}
		
		public void addStudent(String name, int course, int workload) 
		{
			amountOfWork = workload;
			currentStudent = new aStudent(name, course, amountOfWork);
			if (previousStudent.getCourse() > currentStudent.getCourse() && isHelping == 1) 
			{
				waitlist.add(previousStudent);
				for (int i = 0; i < workload; i++) 
				{
					if (i == 0) {
						String flag = baseIndex + " started helping " + name + " from COSC" + course;
						flags.push(flag);
					}
					list.push(currentStudent);
					isHelping = 1;
					step();
					amountOfWork--;
				}
				String flag = baseIndex + " finished helping" + name + " from COSC" + course;
				flags.push(flag);
				isHelping = 0;
				aStudent previousStudent = currentStudent;
			}
			else //if previousStudent.getCourse() < currentStudent.getCourse() || isHelping != 0
			{
				waitlist.add(currentStudent);
				String flag = baseIndex + " sent " + name + " from COSC" + course + " to the waiting list";
			}
			if (isHelping == 0) 
			{
				aStudent next = new aStudent("", 10000, 100);
					for (aStudent e: waitlist) 
					{
						if (e.getCourse() < next.getCourse()) {
							String flag = baseIndex + " started helping " + name + "from COSC" + course;
							flags.push(flag);
							while (e.minutesNeeded > 0) {
								list.push(e);
								isHelping = 1;
								step();
								e.minutesNeeded--;
							}
							isHelping = 0;
							next = e;
							flag = baseIndex + " finished helping " + name + "from COSC" + course;
						}
					}
			}
			if (isHelping == 0 && waitlist.isEmpty() && baseIndex < n) {
				step();
				list.push(null);
			}
		}
		
		public int getTime() 
		{
			return baseIndex;
		}
		
		public String toString() 
		{
			while (baseIndex > -1) {
				if (list.top() != null) {
					aStudent mostRecent = (aStudent) list.top();
					String name = mostRecent.getName();
					int course = mostRecent.getCourse();
					String line = "\nTime " + baseIndex +	": Helping " + name + " from COSC" + course;
					baseIndex--;
					list.pop();
					return line;
				}
				else {
					String nohelp = "\nTime " + baseIndex + ": IDLE";
					baseIndex--;
					list.pop();
					return nohelp;
				}
				
			}
			return "";
		}
		public String getLog() {
			while (!flags.isEmpty()) {
				return (String) flags.top();
			}
			return "";

		}
	}
	
}

