package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager {

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

        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.PASS,"Test Case Passed is:"+result.getName());

    }

    public void onTestFailure(ITestResult result) {

        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.FAIL,"Test Case Failed is:"+result.getName());
        extentTest.log(Status.FAIL,"Exception: "+result.getThrowable());

    }

    public void onTestSkipped(ITestResult result) {

        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.SKIP,"Test Case Skiped is:"+result.getName());

    }

    public void onFinish(ITestContext context) {

        extentReports.flush();

    }


}
