package test.java.mobiquity.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.java.mobiquity.utilities.GenericUtilities;

public class EmployeeDetailsPage {

    private WebDriver driver;
    private static GenericUtilities genericUtilities;

    @FindBy(xpath = "//input[@ng-model='selectedEmployee.firstName']")
    public WebElement firstNameField;

    @FindBy(xpath = "//input[@ng-model='selectedEmployee.lastName']")
    public WebElement lastNameField;

    @FindBy(xpath = "//input[@ng-model='selectedEmployee.startDate']")
    public WebElement startDateField;

    @FindBy(xpath = "//input[@ng-model='selectedEmployee.email']")
    public WebElement emailField;

    @FindBy(xpath = "//button[.='Add']")
    public WebElement addButton;

    @FindBy(xpath = "//button[.='Update']")
    public WebElement updateButton;


    public EmployeeDetailsPage(WebDriver driver) {
        this.driver = driver;
        genericUtilities = new GenericUtilities( driver );
        PageFactory.initElements( driver, this );
    }

    public EmployeeDetailsPage enterFirstName(String firstName) {
        genericUtilities.waitForElementPresent( firstNameField );
        firstNameField.clear();
        firstNameField.sendKeys( firstName );
        return this;
    }

    public EmployeeDetailsPage enterLastName(String lastName) {
        genericUtilities.waitForElementPresent( lastNameField );
        lastNameField.clear();
        lastNameField.sendKeys( lastName );
        return this;
    }

    public EmployeeDetailsPage enterStartDate(String startDate) {
        genericUtilities.waitForElementPresent( startDateField );
        startDateField.clear();
        startDateField.sendKeys( startDate );
        return this;
    }

    public EmployeeDetailsPage enterEmail(String email) {
        genericUtilities.waitForElementPresent( emailField );
        emailField.clear();
        emailField.sendKeys( email );
        return this;
    }

    public ListEmployeesPage clickAddButton() {
        genericUtilities.waitForElementPresent( addButton );
        addButton.click();
        ListEmployeesPage listEmployeesPage = new ListEmployeesPage( driver );
        genericUtilities.waitForElementPresent( listEmployeesPage.createButton );
        return listEmployeesPage;
    }

    public EmployeeDetailsPage clickUpdateButton() {
        genericUtilities.waitForElementPresent( updateButton );
        updateButton.click();
        return new EmployeeDetailsPage( driver );
    }

}
