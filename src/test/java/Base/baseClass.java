package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class baseClass {
    public static WebDriver driver;
    public  static ExtentReports extent;
    public static ExtentTest logger;

    @BeforeSuite
    public void setup()
    {
        if(extent==null)
        {
            extent= new ExtentReports();
            ExtentSparkReporter spark=new ExtentSparkReporter("report/page.html");
            extent.attachReporter(spark);
        }
        if(driver==null)
        {
            WebDriverManager.chromedriver().setup();
            driver= new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5000, TimeUnit.NANOSECONDS);
            driver.get("https://test.synkdup.com/");
        }

    }
    public  static String screenShot() throws IOException {
        Date d= new Date();
        String d1 = d.toString().replace(" ", "_").replace(":", "_");
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest=new File("screen/pic_"+d1+".jpeg");
        FileUtils.copyFile(src,dest);
        String finalpath = dest.getAbsolutePath();
        return finalpath;
    }
    //@AfterSuite
    public void tearDown()
    {
        if(extent!=null)
        {
            extent.flush();
        }
        if(driver!=null)
        {
            driver.quit();
        }
    }
}
