/**
 * COSC 2100 - Project 6
 * This is a help desk where instead of being one big waiting area, there are smaller queues for each level. The lower-levels queues have first priority
 * @author Rucheng Pan
 * Instructor Brylow
 * TA-BOT:MAILTO rucheng.pan@marquette.edu
 */

public class DSQueueOverflowException extends RuntimeException 
{
 public DSQueueOverflowException(String s)
 {
  super(s);
 }
}