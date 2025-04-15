package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseTest;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {

    ExtentSparkReporter sparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

    String reportName;


    public void onStart(ITestContext context){

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test-Report"+timeStamp+".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName);
        sparkReporter.config().setReportName("OpenCart Automation Report");
        sparkReporter.config().setDocumentTitle("OpenCart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application","Open Cart");
        extentReports.setSystemInfo("Module","Admin");
        extentReports.setSystemInfo("Sub-Module","Customer");
        extentReports.setSystemInfo("User Name",System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment","QA");

        String os = context.getCurrentXmlTest().getParameter("os");
        extentReports.setSystemInfo("Operating System",os);

        String browser = context.getCurrentXmlTest().getParameter("browser");
        extentReports.setSystemInfo("Browser",browser);

        List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()){
            extentReports.setSystemInfo("Groups",includedGroups.toString());
        }

    }

    public void onTestSuccess(ITestResult result) {

        extentTest = extentReports.createTest(result.getTestClass().getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.PASS,result.getName()+" got successfully executed.");

    }

    public void onTestFailure(ITestResult result) {

        extentTest = extentReports.createTest(result.getTestClass().getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.FAIL,result.getName()+"got failed.");
        extentTest.log(Status.INFO,result.getThrowable().getMessage());

        try {
            String imgPath = new BaseTest().captureScreen(result.getName());
            extentTest.addScreenCaptureFromPath(imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void onTestSkipped(ITestResult result) {

        extentTest = extentReports.createTest(result.getTestClass().getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.log(Status.FAIL,result.getName()+"got failed.");
        extentTest.log(Status.INFO,result.getThrowable().getMessage());



    }

    public void onFinish(ITestContext context) {

        extentReports.flush();

        String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+reportName;
        File report = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(report.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        try {
            URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+reportName);

            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthentication("example@gmail.com","password");
            email.setSSLOnConnect(true);
            email.setFrom("example@gmail.com");//sender
            email.setSubject("Test Reports");
            email.setMsg("Find the report on attachment");
            email.addTo("receiver@gamil.com");//receiver
            email.attach(url,"extent report", "please  check report");
            email.send();
        } catch (MalformedURLException | EmailException e) {
            e.printStackTrace();
        }
*/

    }


}
