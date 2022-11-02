import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class getAllUrl {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.NANOSECONDS);
        driver.get("https://warehousinjobs.com/findjobs");
        List<WebElement> joblist = driver.findElements(By.xpath("//*[@class='h5 fs-6 text-capitalize letter-spacing-1 mb-1 jd-job-title']/a"));
        System.out.println("------------->"+joblist.get(1).getText());
        driver.close();
    }
}
