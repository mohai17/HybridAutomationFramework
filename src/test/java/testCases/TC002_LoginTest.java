package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;

public class TC002_LoginTest extends BaseTest {

    @Test(groups = {"regression","master"})
    public void loginWithValidData(){

        logger.info("*** TC002_LoginTest is Started ***");

        try {

            HomePage homePage = new HomePage(driver);
            logger.info("Click on My Account Page");
            homePage.clickOnMyAccount();
            logger.info("Click on Login Page");
            homePage.clickOnLoginLink();

            LoginPage loginPage = new LoginPage(driver);
            logger.info("Providing email");
            loginPage.setEmail(p.getProperty("email"));
            logger.info("Email is provided");
            logger.info("Providing Password");
            loginPage.setPassword(p.getProperty("password"));
            logger.info("Password is provided");
            logger.info("Clicking on login button");
            loginPage.clickOnLoginButton();
            logger.info("Login button is clicked");

            MyAccountPage myAccountPage = new MyAccountPage(driver);

            boolean myAccountVisible = myAccountPage.isMyAccountVisible();

            if(myAccountVisible){
                Assert.assertTrue(true);
            }
            else {
                logger.error("Test Failed...");
                logger.debug("Test Debug...");
                Assert.fail();
            }

        }catch (Exception e){
            Assert.fail();
        }

        logger.info("*** TC002_LoingTest is Finished ***");

    }

}
