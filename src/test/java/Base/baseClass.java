package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v108.network.Network;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.awaitility.Awaitility.await;

public class baseClass {
    public static WebDriver driver;
    public  static ExtentReports extent;
    public static ExtentTest logger;
    public static JavascriptExecutor jsExecutor;
    public static WebDriverWait wait;
    public static Select select;
    public static  Properties props;
    public static  FileInputStream file;
    public static DevTools devtool;
    public static Robot rb;
    public static ChromeOptions options;
    @BeforeSuite
    public void setup() throws IOException {
        file= new FileInputStream("src/main/Properties/Properties");
        props = new Properties();
        props.load(file);
        System.out.println(props.getProperty("browser"));

        if(extent==null)
        {
            extent= new ExtentReports();
            ExtentSparkReporter spark=new ExtentSparkReporter("report/page.html");
            extent.attachReporter(spark);
        }
        if(driver==null) {
            if (props.getProperty("browser").equals("chrome")) {
                //WebDriverManager.chromedriver().setup();
                options= new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.get("https://test.synkdup.com/");
            }
            else {
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            }
        }
    }
    public  static String screenShot() throws IOException {
        Date d= new Date();
        String d1 = d.toString().replace(" ", "_").replace(":", "_");
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest=new File("screen/pic_"+d1+".png");
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
            //devtool.close();
        }
        if(driver!=null)
        {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("--------------->"+e.getMessage());
            }
        }
    }

    //
    public static void clickwithborder(String Xpath)
    {
        WebElement element=driver.findElement(By.xpath(Xpath));
        if(element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();
        }

    }
    public static void borderwithsendkeys(String Xpath ,String data)
    {
        WebElement element = driver.findElement(By.xpath(Xpath));
        if(element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.sendKeys(data);
        }


    }
    public static void putwait(WebElement element,int time)
    {
        wait = new WebDriverWait(driver,Duration.ofMillis(time));
        wait.until(ExpectedConditions.visibilityOf(element)).click();

    }

    public static void clickwithcontains(String contains)
    {
        WebElement element = driver.findElement(By.xpath("//button[normalize-space()='" + contains + "']"));
        if(element.isDisplayed())
        {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();
        }
    }

    public static void ClickwithXpath(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();

        }
    }

    public static void clickwithText(String Text)
    {
        WebElement element= driver.findElement(By.xpath("//a[contains(text(),'"+Text+"')]"));
        if(element.isDisplayed())
        {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();

        }
    }

    public static void selectBySuggestions(String inputXpath,String inputVlalue,String suggestionsXpath,String suggestionsValue) throws InterruptedException {
        driver.findElement(By.xpath(inputXpath)).sendKeys(inputVlalue);
        Thread.sleep(5000);
        List<WebElement> Suggestion_list = driver.findElements(By.xpath(suggestionsXpath));
        Thread.sleep(3000);
        //System.out.println(Suggestion_list.size());
        //System.out.println("suggestion name is "+Company_suggestion.get(0).getText());
        for (WebElement suggestion:Suggestion_list) {
            Thread.sleep(2000);
            //System.out.println(suggestion.getText());
            if(suggestion.getText().equalsIgnoreCase(suggestionsValue))
            {
                putwait(suggestion,10);
                break;
            }
        }
    }

    public static void waitAndClick(String Xpath, int time) {

        WebElement element = driver.findElement(By.xpath(Xpath));
        await().atMost(Duration.ofSeconds(time)).until(() -> {
                    return element.isDisplayed();
                }
        );
        element.click();

    }

    public static void waitAndDisplay(String Xpath) {
        WebElement element = driver.findElement(By.xpath(Xpath));
        await().until(() -> {
                    return element.isDisplayed();
                }
        );

    }
    public static void waitAndGetText(String Xpath) {
        WebElement element = driver.findElement(By.xpath(Xpath));
        await().until(() -> {
                    return element.isDisplayed();
                }
        );
        System.out.println(element.getText());
    }



    public static void getText(String Xpath) {
        WebElement element = driver.findElement(By.xpath(Xpath));
        String text = element.getText();
        System.out.println(text);
    }


    public static void checkBtnEnable(String Xpath) {
        WebElement element = driver.findElement(By.xpath(Xpath));
        if(element.isEnabled())
        {
            System.out.println("Button is enabled");
        }
        else {
            System.out.println("Button is disabled");
        }
    }
    public static void checkBtnWithText (String Xpath,String ButtonName) {
        WebElement element = driver.findElement(By.xpath(Xpath));
        //System.out.println(element.getText()+" "+ButtonName);
        if(element.getText().equalsIgnoreCase(ButtonName))
        {
            System.out.println("button text is matched");
        }
        else {
            System.out.println("button text is not matched");
        }

    }
    public static void clickonLinkText(String LinkText) {
        WebElement element = driver.findElement(By.linkText(LinkText));
        if(element.isDisplayed())
        {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();
        }

    }

    public static void clickByjavaScript(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        if(element.isDisplayed())
        {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].click();", element);
        }

    }
    public static void GetTextByJavascript(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].text;", element);
        }

    }

    public static void SendKeysByJavaScript(String xpath,String textvalue) {
        WebElement element = driver.findElement(By.xpath(xpath));
        if(element.isDisplayed())
        {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].value='"+ textvalue +"';", element);
        }

    }
    public static void addScreemshot() throws IOException {
        String screen = screenShot();
        logger.log(Status.INFO, "Screenshot of this page",MediaEntityBuilder.createScreenCaptureFromPath(screen).build());
        //logger.log(Status.INFO, MediaEntity.MediaEntityBuilder.<String>createScreenCaptureFromPath(screen).build());
    }

    public static void SelectByValue(String selectvaluexpath,String value)
    {
        WebElement element = driver.findElement(By.xpath(selectvaluexpath));
        if(element.isDisplayed())
        {
            select = new Select(element);
            select.selectByValue(value);
        }
    }

    public static void SelectByIndex(String selectvaluexpath,int index)
    {
        WebElement element = driver.findElement(By.xpath(selectvaluexpath));
        if(element.isDisplayed())
        {
            select = new Select(element);
            select.selectByIndex(index);
        }
    }

    public static void SelectByVisibleText(String selectvaluexpath,String text)
    {
        WebElement element = driver.findElement(By.xpath(selectvaluexpath));
        if(element.isDisplayed())
        {
            select = new Select(element);
            select.selectByVisibleText(text);
        }
    }
    public static void chromeNetworkResponse()
    {
        devtool = ((ChromeDriver) driver).getDevTools();
        devtool.createSession();
        devtool.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        devtool.addListener(Network.responseReceived(),responseReceived ->
        {
            if((responseReceived.getResponse().getStatus().toString().startsWith("40")))
            {
                System.out.println("Api is not working correctly");
                System.out.println(responseReceived.getResponse().getUrl());
                System.out.println(responseReceived.getResponse().getUrl()+"Status code is "+responseReceived.getResponse().getStatus());
            }
            //System.out.println("-------------------------------->");
            //System.out.println(responseReceived.getResponse().getUrl()+"Status code is "+responseReceived.getResponse().getStatus());
        });
    }

    //file path is the path of your computer
    public static void uploadFile(String filePath) throws IOException, AWTException {
        rb = new Robot();
        rb.delay(5000);

        // copying File path to Clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
    }

    public void alertAccept(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public void matchAlertMessage(String message){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        if(alert.getText().equals(message))
        {
            System.out.println("message set same as expected");
        }
        else
        {
            System.out.println("message not set same as expected");
        }
        alert.accept();
    }

    public void getAlertText(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());

    }
}