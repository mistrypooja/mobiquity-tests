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

            jse.executeScript(String.format("window.scrollBy(0,-%s)", pixels), "");
        } else {
            jse.executeScript(String.format("window.scrollBy(0,%s)", pixels), "");
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

    public void clickStaleElement(By locator) throws InterruptedException {
        waitForElementPresent(driver.findElement( locator ));
        for (int i = 1; i < 60; i++) {
            try {
                driver.findElement( locator ).click();
            } catch (Exception e) {
                if (e.getMessage().contains("element is not attached")) {
                    Thread.sleep(1000);
                } else {
                    break;
                }
            }
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

    public boolean isElementVisible(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait( driver, timeout );
            wait.until( ExpectedConditions.visibilityOfElementLocated( by ) );
            driver.findElement( by );
            return true;
        } catch (Exception e) {
            System.out.print( e );
            return false;
        }
    }

    public boolean isElementVisible(By by) {
        try {
            driver.findElement( by );
            return true;
        } catch (Exception e) {
            System.out.print( e );
            return false;
        }
    }

    public boolean waitForAlertPresent() throws InterruptedException {
        boolean presentFlag = false;
        for (int i = 1; i <= 60; i++) {
            try {
                presentFlag = true;
            } catch (NoAlertPresentException ex) {
                ex.printStackTrace();
            }
            Thread.sleep( 1000 );
        }
        return presentFlag;
    }


    public void waitForElementPresent(By locator) {
        try {
            wait.until( ExpectedConditions.presenceOfElementLocated( locator ) );
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }

    }


    public void waitForElementVisible(By locator) {

        wait.until( ExpectedConditions.visibilityOfElementLocated( locator ) );

    }

    public void waitForElementNotVisible(By locator) {

        wait.until( ExpectedConditions.invisibilityOfElementLocated( locator ) );

    }


    public boolean isElementPresent(WebElement element) {

        try {

            element.isDisplayed();
            return true;

        } catch (Exception e) {
            System.out.print( e );
            return false;
        }
    }


    public boolean isElementPresent(WebElement element, int timeout) {
        try {
            wait = new WebDriverWait( driver, timeout );
            wait.until( ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            System.out.print( e );
            return false;
        }
    }

    public void waitForElementVisible(WebElement element) {

        try {
            wait.until(ExpectedConditions.visibilityOf(element));

        }catch (ElementNotVisibleException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean waitForElementPresent(WebElement element) {

        try{
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public void waitForElementNotVisible(WebElement element) {

        wait.until( ExpectedConditions.invisibilityOf(element) );

    }

    public void waitForElementNotPresent(By element) throws InterruptedException {
        for(int i=1;i<=60;i++) {
            if (isElementPresent( element )) {
                Thread.sleep( 1000 );
            } else

                break;
        }

    }

}

