package helpDesk;

/**
 * COSC2100

 * @author Rucheng Pan
 * 
 * This project simulates a HelpDesk that prioritizes students in lower-level courses
 * 
 * Instructor - Brylow
 *
 */
import java.util.ArrayList;

public class HelpDesk {
//this is where helpDesk starts
		public class aStudent 
		{//this is where aStudent starts
		String name;
		int course;
		int minutesNeeded;
		
		public aStudent()
		{
			name = "";
			course = 0;
			minutesNeeded = 0;
		}
		
		/**
		 * @param names
		 * @param courseNumber
		 * @param helpNeeded
		 */
		public aStudent(String names, int courseNumber, int helpNeeded) 
		{
			name = names;
			course = courseNumber;
			minutesNeeded = helpNeeded;
		}

		public int getCourse()
		{
			return this.course;
		}
		
		public String getName() 
		{
			return this.name;
		}
		
		public void setCourse(int course) 
		{
			this.course = course;
		}

		public int getMinutesNeeded() 
		{
			return minutesNeeded;
		}

		public void setMinutesNeeded(int minutesNeeded) {
			this.minutesNeeded = minutesNeeded;
			}
		}
		//this is when the helpDesk starts

			int baseIndex = -1;
			int isHelping = 0;
			int n = 0;
			int numberInWaitlist;
			DSLinkedStack list = new DSLinkedStack();
			DSLinkedStack flags = new DSLinkedStack();
			DSLinkedStack publish = new DSLinkedStack();
			aStudent previousStudent = new aStudent("", 0, 0);
			aStudent currentStudent;
			int amountOfWork;
			ArrayList<aStudent> waitlist = new ArrayList<aStudent>();
			//increments the time by one
			public void step() 
			{
				baseIndex++;
				
			}
			
			public void addStudent(String name, int course, int workload) 
			{
				amountOfWork = workload; //this variable is how much time they need
				currentStudent = new aStudent(name, course, amountOfWork);
				//creates a new student
				if (previousStudent.getCourse() > currentStudent.getCourse() && isHelping == 1) 
				{
					//if the new student has a lower course number and the other student is currently being helped, previous student gets sent to waitlist
					waitlist.add(previousStudent);
					//for loop, every minute the student needs, the amount of work will decrease by 1
					for (int i = 0; i < workload; i++) 
					{
						//when it just starts, raise a flag
						if (i == 0) {
							String flag = baseIndex + " started helping " + name + " from COSC" + course;
							flags.push(flag);
							list.push(currentStudent);
						}
						list.push(currentStudent);
						isHelping = 1;
						step();
						amountOfWork--;
					}
					//once finished, add another flag
					String flag = baseIndex + " finished helping" + name + " from COSC" + course;
					flags.push(flag);
					isHelping = 0;
					//the currentStudent now becomes the previous student
					aStudent previousStudent = currentStudent;
				} 
				else if (previousStudent.getCourse() < currentStudent.getCourse() && isHelping == 1) //if the new student has a higher course number and ishelping == 1
				{
					waitlist.add(currentStudent);
					String flag = baseIndex + " sent " + name + " from COSC" + course + " to the waiting list";
				}
				else if (isHelping == 0 && !waitlist.isEmpty()) 
				{
					aStudent next = new aStudent("", 10000, 100);
					while (!list.isEmpty()) {
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
				
				}
				else if (isHelping == 0 && waitlist.isEmpty()){
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
				String returnLine = "";
				if (baseIndex > -1) {
					//if list is empty meaning no one is in there
					if (list.isEmpty()) {
						//then it will submit IDLE
						String nohelp = "\nTime " + this.getTime() + ": IDLE";
						returnLine = nohelp;
					}
					else if (list.top() == null) {
						String nohelp = "\nTime " + this.getTime() + ": IDLE";
						returnLine = nohelp;
					}
					else if (list.top() != null && !list.isEmpty()) {
						aStudent mostRecent = (aStudent) list.top();
						String name = mostRecent.getName();
						int course = mostRecent.getCourse();
						String line = "\nTime " + baseIndex +	": Helping " + name + " from COSC" + course;
						returnLine = line;

					}
				}
				return returnLine;
			}

			public String getLog() {
				String finalString = "";
				//as long flags isn't empty
				while (!flags.isEmpty()) {
					//it'll return the top
					finalString = finalString + (String) flags.top();
				}
				return finalString;

				}
		}
	//this is where helpDesk ends






