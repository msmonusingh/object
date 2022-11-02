package Test;

import Base.baseClass;
import Pages.Home;
import Pages.Search;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

import java.io.IOException;

public class SearchTest extends baseClass {
    @Test
    public  void logntest() throws IOException {
        Home home= new Home();
        home.login().loginpage("ishu188@grr.la","Ishu@1234");
    }
}
