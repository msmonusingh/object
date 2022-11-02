package Pages;

import Base.baseClass;
import org.openqa.selenium.By;

public class Login extends baseClass {

    public dashBoard loginpage(String name, String pass)
    {
        driver.findElement(By.xpath("//*[@id='exampleInputEmail1']")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id='exampleInputPassword1']")).sendKeys(pass);
        driver.findElement(By.xpath("//*[text()='Continue']")).click();
        return new dashBoard();

    }
}
