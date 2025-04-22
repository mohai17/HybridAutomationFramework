package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void loginTestWithDDT(String email, String password, String expected){

        logger.info("*** TC003_LoginDDT is started ***");

        try {

            HomePage homePage = new HomePage(driver);
            logger.info("Click on MyAccount");
            homePage.clickOnMyAccount();
            logger.info("My Account is clicked");
            logger.info("Click on Login Link");
            homePage.clickOnLoginLink();
            logger.info("Login link is clicked");

            LoginPage loginPage = new LoginPage(driver);
            logger.info("Providing Email");
            loginPage.setEmail(email);
            logger.info("Email is provided.");
            logger.info("Providing Password");
            loginPage.setPassword(password);
            logger.info("Password is provided");
            logger.info("Click on Login Button");
            loginPage.clickOnLoginButton();
            logger.info("Login Button is clicked");

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean myAccountVisible = myAccountPage.isMyAccountVisible();

            if(expected.equalsIgnoreCase("valid")){
                if (myAccountVisible){
                    myAccountPage.clickOnMyAccount();
                    logger.info("Click on Logout");
                    myAccountPage.clickOnLogout();
                    logger.info("Logout button is clicked");
                    logger.info("Click on Continue");
                    myAccountPage.clickOnContinue();
                    logger.info("Continue button is clicked");
                    Assert.assertTrue(true);

                }
                else {
                    Assert.fail("Fail");
                }
            }
            if(expected.equalsIgnoreCase("invalid")){
                if (myAccountVisible){
                    myAccountPage.clickOnMyAccount();
                    myAccountPage.clickOnLogout();
                    myAccountPage.clickOnContinue();
                    Assert.fail("Fail");

                }
                else {
                    Assert.assertTrue(true);
                }


            }


        }catch (Exception e){

            Assert.fail();
        }

        logger.info("*** TCOO3_LoginDDT is finished ***");

    }

}
