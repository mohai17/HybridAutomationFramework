package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

//    @FindBy(xpath = "//button[normalize-space()='Login']")
//    WebElement loginButton; //opencart-localhost

    @FindBy(xpath = "//input[@value='Login']")
    WebElement loginButton; //tutorial-ninja

    public void setEmail(String emailAddress){

        email.clear();
        email.sendKeys(emailAddress);

    }

    public void setPassword(String pass){

        password.clear();
        password.sendKeys(pass);

    }

    public void clickOnLoginButton(){


        Actions actions = new Actions(driver);
        actions.moveToElement(loginButton).click().perform();

    }

}
