package Pages;

import Base.baseClass;
import Pages.Affliate.affiliate;
import org.openqa.selenium.By;

public class Home extends baseClass
{
    public Login login()

    {
        driver.findElement(By.xpath("(//a[text()='Login'])[1]")).click();
        return new Login();
    }
    public Search Findjob()
    {
        driver.findElement(By.xpath("(//*[text()='Find Jobs'])[1]")).click();
         return new Search();
    }
    public signUp Signup()
    {
        driver.findElement(By.xpath("(//*[text()='Signup'])[1]")).click();
        return new signUp();
    }

    public Login joinUs()
    {
        driver.findElement(By.xpath("(//a[@class='px-5 py-2 btn-second rounded-3 semi-bold hover-state'])[1]")).click();

        return new Login();
    }

    public Search SearchJobs()
    {
        driver.findElement(By.xpath("(//a[@class='px-5 py-2 btn-second rounded-3 semi-bold hover-state'])[2]")).click();
        return new Search();
    }
    public affiliate learnMore()
    {
        driver.get("https://test.synkdup.com/affiliate-program/");

        //driver.findElement(By.xpath("(//a[@class='px-5 py-2 btn-second rounded-3 semi-bold hover-state'])[3]")).click();
        return new affiliate();
    }

}
