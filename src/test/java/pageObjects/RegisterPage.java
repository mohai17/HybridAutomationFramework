package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends Base{
    public RegisterPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;

    @FindBy(xpath = "//input[@id='input-newsletter']")
    WebElement newsletter;

    @FindBy(xpath = "//input[@name='agree']" )
    WebElement privacyPolicy;

    @FindBy(xpath = "//button[normalize-space()='Continue']")
    WebElement registerButton;



    public void setFirstName(String fName){

        firstName.clear();
        firstName.sendKeys(fName);

    }

    public void setLastName(String lName){

        lastName.clear();
        lastName.sendKeys(lName);

    }

    public void setEmail(String mail){

        email.clear();
        email.sendKeys(mail);

    }

    public void setPassword(String pass){

        password.clear();
        password.sendKeys(pass);

    }

    public void setNewsletter() {

        Actions actions = new Actions(driver);
        actions.moveToElement(newsletter).click().perform();


    }

    public void setPrivacyPolicy() {


        Actions actions = new Actions(driver);
        actions.moveToElement(privacyPolicy).click().perform();

    }

    public void clickOnRegisterButton() {

        Actions actions = new Actions(driver);
        actions.moveToElement(registerButton).click().perform();

    }



}
