package commonUtilities;

import org.aeonbits.owner.ConfigFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer
{
	Configuration config = ConfigFactory.create(Configuration.class);
	int retryCount = 0;
	int maxRetryCount = config.RetryCount();
	 
	@Override
	public boolean retry(ITestResult result) 
	{
		// TODO Auto-generated method stub
		if(retryCount < maxRetryCount)
		{
			System.out.println(ConsoleColors.PURPLE+"\nRetrying test : " + result.getName() + "() , Re-Attempt :" + retryCount+"\n"+ConsoleColors.RESET);
			retryCount++;
			return true;
		}
		return false;
	}
	
	
	public String getResultStatusName(int status)
	{
		String resultName = null;
		if(status == 1)
			resultName = "SUCCESS";
		if(status == 2)
			resultName = "FAILURE";
		if(status == 3)
			resultName = "SKIP";
		return resultName;
	}

}
