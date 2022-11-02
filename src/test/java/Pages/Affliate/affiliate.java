package Pages.Affliate;

import Base.baseClass;
import Pages.Home;
import Pages.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class affiliate extends baseClass {

    public Login click_on_joinus()
    {
        return new Home().joinUs();
    }

    
    public void assosiatecard()
    {
        System.out.println(driver.getTitle()+"----------");
        List<WebElement> card = driver.findElements(By.xpath("//*[ @class='col-sm-6 col-md-6 col-lg-3 col-xl-3 col-12 p-0']"));
        System.out.println(card.size());
        for (WebElement cardtext:card) {
            //System.out.println("ddf"+"--------------->");
            List<WebElement> cardlist = cardtext.findElements(By.tagName("a"));
            for (WebElement cardname:cardlist) {
                System.out.println("associate card size"+cardname.getText());
            }
        }
        List<WebElement> sharelist = driver.findElements(By.xpath("//*[@id='assosiate-card']/div"));
        System.out.println("size is ---->"+sharelist.size());
        String name = driver.findElement(By.xpath("//*[@id='assosiate-card']/div[1]/div/div/a[1]")).getText();
        System.out.println(name);
    }
}
