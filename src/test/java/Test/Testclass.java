package Test;

import Base.baseClass;
import Pages.Home;
import org.testng.annotations.Test;

public class Testclass  extends baseClass {
    @Test
    public void homeTest()
    {
        driver.manage().deleteAllCookies();
        Home home= new Home();
        home.learnMore().assosiatecard();
    }

}
