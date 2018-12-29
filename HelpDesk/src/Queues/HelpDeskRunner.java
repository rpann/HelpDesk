
	/**
	 * HelpDeskRunner v1.0

	 * @author 
	 * Runner class parses input and provides output for the
	 *  Project 3 HelpDesk simulator.  This Runner assumes your
	 *  HelpDesk class provides the following API:
	 * 
	 *  public void step()
	 *    Advance the simulation one minute.
	 * 
	 *  public void addStudent(String name, int course, int workload)
	 *    Add an arriving student with the indicate name, course number, 
	 *    and minutes of workload. If the current student has a lower or
	 *    equal course number, the arriving student is turned away.
	 *    If the current student has a higher course number, the current
	 *    student is preempted while the new student is helped.
	 * 
	 *  public int getTime()
	 *    Get the current simulation time in minutes.
	 * 
	 *  public String toString()
	 *    Return the status of the simulation. This produces strings like, 
	 *    "Time 2, Helping Jack from COSC1010", or "Time 9, IDLE".
	 * 
	 *  public String getLog()
	 *    Return the entire HelpDesk session log.
	 */
	import java.util.Scanner;

import helpDesk.HelpDesk;

	public class HelpDeskRunner
	{
	  public static void main(String[] args)
	  {
	    Scanner tokenizer = new Scanner(System.in);
	    
	    int rounds  = tokenizer.nextInt();
	    int time    = -1;
	    String name = null;
	    int course  = 0;
	    int work    = 0;
	    
	   helpDesk hd = new helpDesk();
	    
	    if (tokenizer.hasNext())
	    {
	      time    = tokenizer.nextInt();
	      name    = tokenizer.next();
	      course  = tokenizer.nextInt();
	      work    = tokenizer.nextInt();
	    }
	    
	    do
	    {
	      while (time == hd.getTime())
	      {
	       // System.out.println("Time " + time + ",\t" + name + " from COSC" + course + " for " + work + " minutes.");
	        hd.addStudent(name, course, work);
	        if (tokenizer.hasNext())
	        {
	          time    = tokenizer.nextInt();
	          name    = tokenizer.next();
	          course  = tokenizer.nextInt();
	          work    = tokenizer.nextInt();
	        }
	        else
	        {
	          time = -1;
	        }
	      }
	     System.out.println(hd);
	     System.out.println(hd.getTime());
	      hd.step();
	    }
	    while (hd.getTime() < rounds);
	    
	    
	   // System.out.println("Time " + time + ",\t" + name + " from COSC" + course + " for " + work + " minutes.");
	    
	    System.out.println("Rounds = " + rounds);
	    System.out.println();
	    System.out.println("LOG:");
	    System.out.println(hd.getLog()); 
	  }
	}

