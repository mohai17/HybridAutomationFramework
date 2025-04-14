package pageObjects;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Base {

    WebDriver driver;

    public Base(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);

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
