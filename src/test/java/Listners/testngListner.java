package Listners;

import Base.baseClass;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class testngListner extends baseClass implements ITestListener  {

    public void onTestStart(ITestResult result) {
        if(props.getProperty("browser").equalsIgnoreCase("chrome")) {
            chromeNetworkResponse();
        }
        logger=extent.createTest(result.getMethod().getMethodName());
    }
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        logger.log(Status.PASS, result.getMethod().getDescription());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        logger.log(Status.FAIL, result.getMethod().getDescription());
    }

}
