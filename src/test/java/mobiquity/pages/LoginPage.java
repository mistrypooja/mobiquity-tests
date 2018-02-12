package test.java.mobiquity.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.java.mobiquity.utilities.GenericUtilities;

public class LoginPage {

    private WebDriver driver;
    private static GenericUtilities genericUtilities;

    @FindBy(xpath="//input[@ng-model='user.name']")
    public WebElement emailField;

    @FindBy(xpath="//input[@ng-model='user.password']")
    public WebElement passwordField;

    @FindBy(xpath = "//button[.='Login']")
    public WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        genericUtilities = new GenericUtilities( driver );
        PageFactory.initElements( driver, this );
    }

    public ListEmployeesPage login(String username, String password){
        genericUtilities.waitForElementPresent( emailField );
        emailField.clear();
        emailField.sendKeys( username );
        passwordField.clear();
        passwordField.sendKeys( password );
        loginButton.click();
        return new ListEmployeesPage( driver );
    }
}
