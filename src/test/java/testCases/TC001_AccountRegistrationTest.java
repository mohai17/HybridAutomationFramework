package testCases;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseTest;

public class TC001_AccountRegistrationTest extends BaseTest {

    Lorem lorem = new LoremIpsum();

    @Test(groups = {"sanity","master"})
    public void regWithValidData() {

        logger.info("*** TC001_RegistrationTest is Started ***");

        try {
            HomePage homePage = new HomePage(driver);
            logger.info("Click on My Account");
            homePage.clickOnMyAccount();
            logger.info("Click on RegisterLink");
            homePage.clickOnRegisterLink();

            RegisterPage registerPage = new RegisterPage(driver);
            logger.info("Providing First Name");
            registerPage.setFirstName(lorem.getFirstName());
            logger.info("Providing Last Name");
            registerPage.setLastName(lorem.getLastName());
            logger.info("Providing Email");
            registerPage.setEmail(lorem.getEmail());
            logger.info("Providing Password");
            registerPage.setPassword(p.getProperty("password"));
            logger.info("Password is provided");
            logger.info("Setting Newsletter");
            registerPage.setNewsletter();
            logger.info("Setting Privacy Policy");
            registerPage.setPrivacyPolicy();
            logger.info("Click on Register Button");
            registerPage.clickOnRegisterButton();

            logger.info("Expected Message");
            String actualText = registerPage.getRegisterMessage();
            String expectedText = "Register Account";

            if(actualText.equals(expectedText)){
                Assert.assertTrue(true);
            }
            else {
                logger.error("Test faild...");
                logger.debug("Debug logs...");
                Assert.fail();
            }

        }catch (Exception e){

            Assert.fail();
        }

        logger.info("*** TC001_RegistrationTest is Finished ***");

    }
}

