package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MyAccountPage extends Base{
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//a[normalize-space()='Account']")
    WebElement myAccountHeader;

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement myAccount;

    @FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Logout']")
    WebElement logout;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    WebElement continueBtn;

    public boolean isMyAccountVisible() throws InterruptedException {

        Thread.sleep(3000);
        try{
            return myAccountHeader.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void clickOnLogout(){
        logout.click();
    }

    public void clickOnContinue(){
        continueBtn.click();
    }
    public void clickOnMyAccount(){
        myAccount.click();
    }


}
