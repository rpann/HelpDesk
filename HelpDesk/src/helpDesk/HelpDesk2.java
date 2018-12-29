package helpDesk;

import java.util.ArrayList;

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
		
	}
	
	public void step() 
	{
		baseIndex++;
		
	}
	
	public void addStudent(String name, int course, int workload) 
	{
		amountOfWork = workload;
		currentStudent = new aStudent(name, course, amountOfWork);
		if (previousStudent.getCourse() > currentStudent.getCourse() && isHelping == 0) 
		{
			list.push(previousStudent);
			for (int i = 0; i < workload; i++) 
			{
				list.push(currentStudent);
				isHelping = 1;
				step();
				amountOfWork--;
			}
			isHelping = 0;
			aStudent previousStudent = currentStudent;
		}
		else //if previousStudent.getCourse() < currentStudent.getCourse() || isHelping != 0
		{
			waitlist.add(currentStudent);
		}
		if (isHelping == 0) 
		{
			aStudent next = new aStudent("", 10000, 100);
				for (aStudent e: waitlist) 
				{
					if (e.getCourse() < next.getCourse()) {
						while (e.minutesNeeded > 0) {
							list.push(e);
							isHelping = 1;
							step();
							e.minutesNeeded--;
						}
						isHelping = 0;
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
		String flag = "";
		while (!flags.isEmpty()) {
			flag = flag + flags.top();
		}
		return flag;
		}

	}

