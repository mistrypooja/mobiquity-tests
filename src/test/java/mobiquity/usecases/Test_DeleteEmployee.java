package test.java.mobiquity.usecases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.java.mobiquity.base.TestBase;
import test.java.mobiquity.config.Config;
import test.java.mobiquity.pages.EmployeeDetailsPage;
import test.java.mobiquity.pages.ListEmployeesPage;
import test.java.mobiquity.pages.LoginPage;
import test.java.mobiquity.utilities.GenericUtilities;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.testng.Assert.assertTrue;

public class Test_DeleteEmployee extends TestBase{
    private WebDriver driver;
    private LoginPage loginPage;
    private GenericUtilities genericUtilities;

    @Test(dataProvider = "testableBrowsers")
    public void testDeleteEmployee(String browser, String version, String platform, Method method)
            throws IOException {

        driver = setDriver( browser, version, platform, method );
        loginPage=new LoginPage( driver );
        genericUtilities=new GenericUtilities( driver );
        String randomString=genericUtilities.generateStringWithCurrentTimestamp();
        String firstName="Pooja"+randomString;
        String lastName="lastName"+randomString;
        String employeeName=firstName+" "+lastName;
        String startDate="2017-01-02";
        String email=randomString+"@mailinator.com";

        EmployeeDetailsPage employeeDetailsPage= loginPage.login( Config.username,Config.password )
                .clickCreateButton();

        ListEmployeesPage listEmployeesPage=employeeDetailsPage.enterFirstName( firstName ).enterLastName( lastName )
                .enterStartDate(
                        startDate )
                .enterEmail( email ).clickAddButton();

        assertTrue(listEmployeesPage.isEmployeeWithFirstAndLastNamePresent( employeeName ), "The created employee " +
                "was not displayed on the List Employee page");
        listEmployeesPage.selectEmployee( employeeName ).clickDeleteButton().acceptDeleteAlert();

        assertTrue(listEmployeesPage.isEmployeeWithFirstAndLastNamePresent( employeeName ), "The deleted employee " +
                "was not removed from the List Employee page");


    }
}

