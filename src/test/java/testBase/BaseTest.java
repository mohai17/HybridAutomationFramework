package testBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseTest{

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"setup"})
    @Parameters({"os","browser"})
    public void setup(String os, String browser) throws IOException, URISyntaxException {

        logger = LogManager.getLogger(this.getClass());
        FileReader fileReader = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(fileReader);

        if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {

            DesiredCapabilities capabilities = new DesiredCapabilities();

            if(os.equalsIgnoreCase("windows")){

                capabilities.setPlatform(Platform.WIN11);

            }
            else if(os.equalsIgnoreCase("linux"))
            {
                capabilities.setPlatform(Platform.LINUX);
            }
            else if (os.equalsIgnoreCase("mac")) {

                capabilities.setPlatform(Platform.MAC);

            }
            else {
                System.out.println("No Os is found.");
            }

            if(browser.equalsIgnoreCase("chrome")){

                capabilities.setBrowserName(Browser.CHROME.browserName());

            }
            else if(browser.equalsIgnoreCase("edge")){

                capabilities.setBrowserName(Browser.EDGE.browserName());

            }
            else if(browser.equalsIgnoreCase("firefox"))
            {
                capabilities.setBrowserName(Browser.FIREFOX.browserName());
            }
            else{
                System.out.println("No browser is found.");
            }

            URI uri = new URI("http://192.168.1.101:4444");

            driver = new RemoteWebDriver(uri.toURL(),capabilities);


        }
        else {
            switch (browser.toLowerCase()){
                case "chrome": driver = new ChromeDriver(); break;
                case "firefox": driver = new FirefoxDriver(); break;
                case "edge": driver = new EdgeDriver(); break;
                default: System.out.println("Invalid browser.");return;
            }
        }


        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("url"));
        driver.manage().window().maximize();


    }

    @AfterClass(groups = {"setup"})
    public void tearDown(){

        driver.quit();

    }

    public String captureScreen(String tName){

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tName+"_"+timeStamp+".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }

}
