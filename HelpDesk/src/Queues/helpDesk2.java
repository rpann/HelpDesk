package Queues;

import java.util.ArrayList;

import helpDesk.DSLinkedStack;
import helpDesk.HelpDesk.aStudent;

public class helpDesk2 {
	public class aStudent 
	{//this is where aStudent starts
	String name;
	int course;
	int minutesNeeded;
	int level;
	//constructs a student object
	public aStudent(String names, int courseNumber, int helpNeeded) 
	{
		name = names;
		course = courseNumber;
		minutesNeeded = helpNeeded;
		level = courseNumber/1000;
		
	}
	public int getLevel() 
	{ //returns the level of the student
		return this.level;
	}

	public int getCourse() //returns the course of the student
	{
		return this.course;
	}
	
	public String getName() //returns the name of the student
	{
		return this.name;
	}
	
	public void setCourse(int course) //sets the course of the student
	{
		this.course = course;
	}

	public int getMinutesNeeded() //gets the number of help needed
	{
		return minutesNeeded;
	}

	public void setMinutesNeeded(int minutesNeeded) { //set the number of minutes needed
		this.minutesNeeded = minutesNeeded;
		}
	}
	//this is when the helpDesk starts
//variables for the helpDesk
		int baseIndex = -1;
		int isHelping = 0;
		int amountOfWork;
		aStudent currentStudent;
		aStudent previousStudent;
		DSArrayBoundedQueue list = new DSArrayBoundedQueue();
		DSArrayBoundedQueue ones = new DSArrayBoundedQueue(3);
		DSArrayBoundedQueue twos = new DSArrayBoundedQueue(3);
		DSArrayBoundedQueue threes = new DSArrayBoundedQueue(3);
		DSArrayBoundedQueue fours = new DSArrayBoundedQueue(3);
		DSArrayBoundedQueue fives = new DSArrayBoundedQueue(3); //not needed
		DSArrayBoundedQueue flags = new DSArrayBoundedQueue();
		//increments the time by one
		public void step() 
		{
			baseIndex++;
			
		}
		//adds a student
		public void addStudent(String name, int course, int workload) 
		{
			amountOfWork = workload; //this variable is how much time they need
			currentStudent = new aStudent(name, course, amountOfWork); //creates a new student
			if (isHelping == 0) {
				for (int i = 0; i < amountOfWork; i++) 
				{
					//when it just starts, raise a flag
					if (i == 0) {
						String flag = this.getTime() + " started helping " + currentStudent.getName()+ " from COSC" + currentStudent.getCourse();
						flags.enqueue(flag);
						list.enqueue(currentStudent);
						step();
					}
					else {
						step();
						list.enqueue(currentStudent);
					}
				}
			}
			else
				this.addToWaitlist(currentStudent);
		}
		public void addToWaitlist(aStudent f) {
			if (f.getLevel() == 1) {
				ones.enqueue(f);
				String flag = baseIndex + " sent " + f.getName() + " from COSC" + f.getCourse() + " to waitlist";
				flags.enqueue(flag);
			}
			if (f.getLevel() == 2) {
				twos.enqueue(f);
				String flag = baseIndex + " sent " + f.getName() + " from COSC" + f.getCourse() + " to waitlist";
				flags.enqueue(flag);
			}
			if (f.getLevel() == 3) {
				threes.enqueue(f);
				String flag = baseIndex + " sent " + f.getName() + " from COSC" + f.getCourse() + " to waitlist";
				flags.enqueue(flag);
			}
			if (f.getLevel() == 4) {
				fours.enqueue(f);
				String flag = baseIndex + " sent " + f.getName() + " from COSC" + f.getCourse() + " to waitlist";
				flags.enqueue(flag);
			}
		}
		public void ifNoHelp() {
			while (!ones.isEmpty()) { //checks level 1 waitlist, if it isn't empty
				aStudent top = (aStudent) ones.dequeue(); //gets the first element from the waitlist
				flags.enqueue(this.getTime() + " started helping " + top.getName() + " from COSC" + top.getClass());
				while (top.minutesNeeded > 1) {
					list.enqueue(top);
					top.minutesNeeded--;
					step();
					isHelping = 1;
			}
				isHelping = 0;
				}
			while (!twos.isEmpty()) { //checks level 2 waitlist
				aStudent top = (aStudent) twos.dequeue();
				flags.enqueue(this.getTime() + " started helping " + top.getName() + " from COSC" + top.getClass());
				while (top.minutesNeeded > 1) {
					list.enqueue(top);
					top.minutesNeeded--;
					step();
					isHelping = 1;
				}
				isHelping = 0;
			}
			while (!threes.isEmpty()) {
				aStudent top = (aStudent) threes.dequeue();
				flags.enqueue(this.getTime() + " started helping " + top.getName() + " from COSC" + top.getClass());
				while (top.minutesNeeded > 1) {
					list.enqueue(top);
					top.minutesNeeded--;
					step();
					isHelping = 1;
			}
				isHelping = 0;
			}
			while (!fours.isEmpty()) {
				aStudent top = (aStudent) fours.dequeue();
				flags.enqueue(this.getTime() + " started helping " + top.getName() + " from COSC" + top.getClass());
				while (top.minutesNeeded > 1) {
					list.enqueue(top);
					top.minutesNeeded--;
					step();
					isHelping = 1;
			}
				isHelping = 0;
			}
			while (!fives.isEmpty()) {
				aStudent top = (aStudent) fives.dequeue();
				flags.enqueue(this.getTime() + " started helping " + top.getName() + " from COSC" + top.getClass());
				while (top.minutesNeeded > 1) {
					list.enqueue(top);;
					top.minutesNeeded--;
					step();
					isHelping = 1;
				}
				isHelping = 0;
			}
		}
		public int getTime() //returns the base index
		{	
			return baseIndex;
		}
		
		public String toString() //this is kind of irrelevant no need for this method at all
		{
			String returnLine = "";
			if (baseIndex > -1) {
				//if list is empty meaning no one is in there
				if (list.isEmpty()) {
					//then it will submit IDLE
					String nohelp = "\nTime " + this.getTime() + ": IDLE";
					returnLine = nohelp;
				}
				else if (list.dequeue() == null) {
					String nohelp = "\nTime " + this.getTime() + ": IDLE";
					returnLine = nohelp;
				}
				else if (list.dequeue() != null && !list.isEmpty()) {
					aStudent mostRecent = (aStudent) list.dequeue();
					String name = mostRecent.getName();
					int course = mostRecent.getCourse();
					String line = "\nTime " + baseIndex +	": Helping " + name + " from COSC" + course;
					returnLine = line;

				}
			}
			return returnLine;
		}

		public String getLog() { //this returns the string, technically should be the toString() method..?
			String returnString = "";
			for (int i = 0; i < flags.size(); i++) {
				returnString += "\n" + flags.dequeue();
			}
			return returnString;
			}
}
