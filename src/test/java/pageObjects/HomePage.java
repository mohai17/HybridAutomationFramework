package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Base{



    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement myAccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement register;

    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement login;

    public void clickOnMyAccount(){
        myAccount.click();

    }

    public void clickOnRegisterLink(){

        register.click();
    }

    public void clickOnLoginLink() throws InterruptedException {

        login.click();
    }

}
