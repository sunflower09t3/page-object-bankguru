package commons;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
	private int retryCounter = 0;
	private int retryLimit = 2;
	
	@Override
	public boolean retry(ITestResult result) {
		if(retryCounter < retryLimit) {
			retryCounter++;
			System.out.println("Retry test name: " + result.getName() + " with: " + retryCounter + "times");
			return true;
		}
		else{
			return false;
		}
	}

}
