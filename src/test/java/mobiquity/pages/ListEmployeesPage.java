package test.java.mobiquity.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.mobiquity.utilities.GenericUtilities;

public class ListEmployeesPage {

    private WebDriver driver;
    private static GenericUtilities genericUtilities;

    @FindBy(id = "bAdd")
    protected WebElement createButton;
    @FindBy(id="bDelete")
    private WebElement deleteButton;
    @FindBy(id="bEdit")
    private WebElement editButton;
    @FindBy(xpath = "//li[@ng-click='selectEmployee(employee)']")
    private WebElement employeeRowLocator;

    private String employeeNameLocator = "//li[contains(.,'%s')]";
    private String employeeSelectedLocator="//li[contains(.,'%s') and contains(@class,'active')]";

    public ListEmployeesPage(WebDriver driver) {
        this.driver = driver;
        genericUtilities = new GenericUtilities( driver );
        PageFactory.initElements( driver, this );
    }

    public EmployeeDetailsPage clickCreateButton() {
        genericUtilities.waitForElementPresent( createButton );
        createButton.click();
        return new EmployeeDetailsPage( driver );
    }

    public boolean isEmployeeWithFirstAndLastNamePresent(String employeeName) {
        genericUtilities.waitForElementPresent( employeeRowLocator );
        return genericUtilities.isElementPresent( By.xpath( String.format( employeeNameLocator, employeeName ) )
                ,10 );
    }

    public boolean waitForEmployeeToBeDeleted(String employeeName) throws InterruptedException {
        return genericUtilities.waitForElementNotPresent( By.xpath( String.format( employeeNameLocator, employeeName ) )  );
    }

    public ListEmployeesPage selectEmployee(String employeeName){
        genericUtilities.waitForElementPresent( By.xpath( String.format( employeeNameLocator, employeeName ) ) );
        driver.findElement( By.xpath( String.format( employeeNameLocator, employeeName ) ) ).click();
        genericUtilities.waitForElementPresent(  By.xpath( String.format( employeeSelectedLocator, employeeName ) ) );
        return this;
    }

    public ListEmployeesPage clickDeleteButton() {
        genericUtilities.waitForElementPresent( deleteButton );
        deleteButton.click();
        return this;
    }

    public EmployeeDetailsPage clickEditButton() {
        genericUtilities.waitForElementPresent( editButton );
        editButton.click();
        return new EmployeeDetailsPage( driver );
    }

    public ListEmployeesPage acceptDeleteAlert(){
        WebDriverWait wait = new WebDriverWait(driver, 3000);
        wait.until( ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }
}
