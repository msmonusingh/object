package Pages;

import Base.baseClass;
import org.apache.commons.compress.utils.OsgiUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class FooterSection extends baseClass {
    @Test
    public void foooter_Area()
    {
        List<WebElement> footer_section_number = driver.findElements(By.xpath("(//*[@class='row row-cols-1 row-cols-md-4']/div)"));
        int i = footer_section_number.size();
        for ( int j = 0; j < i; j++) {
             String path="(//*[@class='row row-cols-1 row-cols-md-4']/div)["+j+"]/ul/li";
            List<WebElement> a = driver.findElements(By.xpath(path));
            for (WebElement nn:a)
            {
                System.out.println("---------->"+nn.getText());
            }
             //System.out.println(j);
            //System.out.println(path);
        }
//        System.out.println(footer_section_number.size());
//        List<WebElement> footer_list = driver.findElements(By.xpath("(//*[@class='row row-cols-1 row-cols-md-4']/div)[1]/ul/li"));
//        System.out.println(footer_list.size());
//        for (WebElement footer_name:footer_list) {
//            System.out.println(footer_name.getText());
//            System.out.println("------------->");
//            //System.out.println("link is "+"--------->"+link);
//        }
    }
}
