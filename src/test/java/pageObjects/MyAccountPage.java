package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends Base{
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement myAccount;

    @FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Logout']")
    WebElement logout;


    public void clickOnMyAccount(){
        myAccount.click();
    }

    public boolean getLogoutText(){
        return logout.isDisplayed();
    }

}
