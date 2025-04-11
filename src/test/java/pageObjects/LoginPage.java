package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Base{


    public LoginPage(WebDriver driver) {
        super(driver);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.body.style.zoom='80%'");
    }

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;

    @FindBy(xpath = "//form[@id='form-login']//button[@type='submit'][text()='Login']")
    WebElement loginButton;

    public void setEmail(String emailAddress){

        email.clear();
        email.sendKeys(emailAddress);

    }

    public void setPassword(String pass){

        password.clear();
        password.sendKeys(pass);

    }

    public void clickOnLoginButton() throws InterruptedException {

        loginButton.click();

    }

}
