package test.java.mobiquity.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericUtilities {
    WebDriverWait wait = null;

    private WebDriver driver;
    public String textLocator = "//%s[text()='%s']";

    public GenericUtilities(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait( driver, 60 );
    }


    public String generateStringWithCurrentTimestamp() {
        long time = new java.util.Date().getTime();
        return String.valueOf( time );
    }

    public void scroll(String pixels, boolean isUp) {

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        if (isUp) {

            jse.executeScript( String.format( "window.scrollBy(0,-%s)", pixels ), "" );
        } else {
            jse.executeScript( String.format( "window.scrollBy(0,%s)", pixels ), "" );
        }
    }

    public boolean isElementPresent(By by) {

        try {
            driver.findElement( by ).isDisplayed();
            return true;
        } catch (Exception e) {
            System.out.print( e );
            return false;
        }
    }


    public boolean isElementPresent(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait( driver, timeout );
            wait.until( ExpectedConditions.presenceOfElementLocated( by ) );
            driver.findElement( by );
            return true;
        } catch (Exception e) {
            System.out.print( e );
            return false;
        }
    }




    public void waitForElementPresent(By locator) {
        try {
            wait.until( ExpectedConditions.presenceOfElementLocated( locator ) );
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }

    }



    public boolean waitForElementPresent(WebElement element) {

        try {
            wait.until( ExpectedConditions.visibilityOf( element ) );
            return true;
        } catch (Exception e) {
            System.out.println( e.getMessage() );
            return false;
        }

    }


    public boolean waitForElementNotPresent(By element) throws InterruptedException {
        boolean foundElement = true;
        int i=1;
        while(i < 20){
            if (isElementPresent( element )) {
                Thread.sleep( 1000 );
                i++;
            }
            else
                foundElement=false;
        }
      return foundElement;
    }

}

