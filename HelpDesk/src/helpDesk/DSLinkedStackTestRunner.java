package helpDesk;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class DSLinkedStackTestRunner 
{
	public static void main(String[] args) 
	{
		Result result = JUnitCore.runClasses(DSLinkedStackTest.class);
		for (Failure failure : result.getFailures()) 
		{
			System.out.println(failure.toString());
		}
		if (result.wasSuccessful())
		{
			long time = result.getRunTime();
			System.out.println("All " +
							   result.getRunCount() +
							   " tests successful. " +
							   "(" + time + "ms)");
		}
	}
} 