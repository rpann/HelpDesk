
/**
 * COSC 2100 - Project 6
 * This is a help desk where instead of being one big waiting area, there are smaller queues for each level. The lower-levels queues have first priority
 * @author Rucheng Pan
 * Instructor Brylow
 * TA-BOT:MAILTO rucheng.pan@marquette.edu
 */


import java.util.ArrayList;

import helpDesk.DSLinkedStack;

public class HelpDesk {
	public class aStudent 
	{//this is where aStudent starts
		//sets up the variables
	String name;
	int course;
	int minutesNeeded;
	int level;
	public aStudent(String names, int courseNumber, int helpNeeded) 
	{
		name = names;
		course = courseNumber;
		minutesNeeded = helpNeeded;
		level = courseNumber/1000;
		
	}
	public int getLevel()
	{//gets the level for the student
		return this.level;
	}

	public int getCourse()
	//gets the course level for the student
	{
		return this.course;
	}
	
	public String getName() 
	 //gets the name of the student
	{
		return this.name;
	}
	
	public void setCourse(int course) //sets the course number for the student
	{
		this.course = course;
	}

	public int getMinutesNeeded() //gets the minutes of help needed
	{
		return minutesNeeded;
	}

	public void setMinutesNeeded(int minutesNeeded) {//sets the number of minutes needed
		this.minutesNeeded = minutesNeeded;
		}
	}
	//this is when the helpDesk starts
	//sets up variables
		int baseIndex = -1;//timer
		int isHelping = 0;//if the helper is helping anyone, it's 0, else, it's 1 
		int amountOfWork; // num minutes student needs
		aStudent currentStudent;
		//all the waitlist queues
		DSLinkedStack list = new DSLinkedStack();
		DSArrayBoundedQueue ones = new DSArrayBoundedQueue(3);
		DSArrayBoundedQueue twos = new DSArrayBoundedQueue(3);
		DSArrayBoundedQueue threes = new DSArrayBoundedQueue(3);
		DSArrayBoundedQueue fours = new DSArrayBoundedQueue(3);

		ArrayList<String> returnStatement = new ArrayList<String>();
		//adds all the important "flags" to a returnStatement that gets printed out into "toLog"

		//increments the time by one
		public void step() 
		{
			baseIndex++;
			
		}
		//adds a new student
		public void addStudent(String name, int course, int workload) 
		{
			amountOfWork = workload; //this variable is how much time they need
			currentStudent = new aStudent(name, course, amountOfWork); //creates a new student
			if (isHelping == 1) //the helper is currently helping someone
			{
				int level = currentStudent.getLevel();//gets the level of the student
				if (level == 1) {//if the course is 1000-2000
					if (ones.isFull()) {//if ones is full, then it checks the 2000s
						if (twos.isFull()) {//if twos is full, then it turns the student away
							returnStatement.add(this.getTime() + " turned " + currentStudent.getName() + " from COSC" + currentStudent.getClass() + "away ");
						}
						else//if twos isn't full but ones is, add the currentStudent to the waitlist
							twos.enqueue(currentStudent);
					}//if ones isn't full, add student to waitlist
					else
						ones.enqueue(currentStudent);
						
				}
				if (level == 2) {//if course # is from 2000-3000
					//same thing as for level 1, places currentStudent in a waiting queue
					if (twos.isFull()) {
						if (threes.isFull()) {
							returnStatement.add(this.getTime() + " turned " + currentStudent.getName() + " from COSC" + currentStudent.getClass() + "away ");
						}
						else
							threes.enqueue(currentStudent);
					}
					twos.enqueue(currentStudent);
				}
				if (level == 3) { //if course # is from 3000 - 4000
					if (threes.isFull()) {
						if (fours.isFull()) {
							returnStatement.add(this.getTime() + " turned " + currentStudent.getName() + " from COSC" + currentStudent.getClass() + "away ");
						}
						else
							fours.enqueue(currentStudent);
					}
					threes.enqueue(currentStudent);
				}
				if (level == 4) { //if course # is from 4000-5000
					if (fours.isFull()) 
							returnStatement.add(this.getTime() + " turned " + currentStudent.getName() + " from COSC" + currentStudent.getClass() + "away ");
					fours.enqueue(currentStudent);
				}
			} 
			else if (isHelping == 0) {//helper is currently not helping anyone
				returnStatement.add(this.getTime() + " started helping " + currentStudent.getName() + " from COSC" + currentStudent.getClass());
				for (int i = 0; i < currentStudent.minutesNeeded; i++) {
					list.push(currentStudent);
				}
				//once this person is finished, it looks at the waitlists
				ifNoHelp();
			}
		}
		public void ifNoHelp() {
			//starts helping people on the waitlist. starting with the lowest level courses
			while (!ones.isEmpty()) {
				aStudent top = (aStudent) ones.dequeue(); //gets the first student at the top of the queue
				returnStatement.add(this.getTime() + " started helping " + top.getName() + " from COSC" + top.getClass()); //adds the statement to be printed out a "log"
				while (top.minutesNeeded > 1) {
					list.push(currentStudent);
					top.minutesNeeded--;
					step();
					isHelping = 1;
					}
				isHelping = 0;
				}
			while (!twos.isEmpty()) {
				aStudent top = (aStudent) twos.dequeue();
				returnStatement.add(this.getTime() + " started helping " + top.getName() + " from COSC" + top.getClass());
				while (top.minutesNeeded > 1) {
					list.push(currentStudent);
					top.minutesNeeded--;//subtracts from the total number of minutes of help needed
					step();//moves the time foward one
					isHelping = 1;//currently is helping someone
				}
				isHelping = 0;//no longer helping anyone
			}
			while (!threes.isEmpty()) {
				aStudent top = (aStudent) threes.dequeue();
				returnStatement.add(this.getTime() + " started helping " + top.getName() + " from COSC" + top.getClass());
				while (top.minutesNeeded > 1) {
					list.push(currentStudent);
					top.minutesNeeded--;
					step();
					isHelping = 1;
			}
				
			}
			while (!fours.isEmpty()) {
				aStudent top = (aStudent) fours.dequeue();
				returnStatement.add(this.getTime() + " started helping " + top.getName() + " from COSC" + top.getClass());
				while (top.minutesNeeded > 1) {
					list.push(currentStudent);
					top.minutesNeeded--;
					step();
					isHelping = 1;
				}
			}
		}
		public int getTime() 
		{	
			return baseIndex;
		}
		
		public String toString() //returns the output of the sequence
		{
			String returnLine = "";
			//as long as the time has increased
			if (baseIndex > -1) {
				//if list is empty meaning no one is in there
				if (list.isEmpty()) {
					//then it will submit IDLE
					String nohelp = "\nTime " + this.getTime() + ": IDLE";
					returnLine = nohelp;
				}
				//if theres no one being helped at this time
				else if (list.top() == null) {
					String nohelp = "\nTime " + this.getTime() + ": IDLE";
					returnLine = nohelp;
				}
				//if theres a student at the top of the list
				else if (list.top() != null && !list.isEmpty()) {
					aStudent mostRecent = (aStudent) list.top();
					String name = mostRecent.getName();
					int course = mostRecent.getCourse();
					String line = "\nTime " + baseIndex +	": Helping " + name + " from COSC" + course;
					returnLine = line;
					list.pop();

				}
			}
			return returnLine;
		}
		//returns the log of important events
		public String getLog() {
			String returnString = "";
			for (int i = 0; i < returnStatement.size(); i++) {
				returnString += "\n" + returnStatement.get(i);
			}
			return returnString;
			}
}