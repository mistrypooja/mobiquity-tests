package test.java.mobiquity.base;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import test.java.mobiquity.config.Config;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import static java.io.File.separator;
import static java.lang.String.format;
import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.openqa.selenium.OutputType.FILE;

public class TestBase {


    private WebDriver driver;
    private static final Platform LOCAL_OS = Platform.getCurrent();

    protected WebDriver setDriver(String browser, String version, String platform, Method method)
            throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browser.equalsIgnoreCase( "firefox" )) {
            if (LOCAL_OS.is( Platform.MAC )) {
                System.setProperty( "webdriver.gecko.driver",
                        System.getProperty( "user.dir" ) + "/src/test/resources/drivers/mac/firefox/geckodriver" );
            }
            if (LOCAL_OS.is( Platform.WINDOWS )) {
                System.setProperty( "webdriver.gecko.driver",
                        System.getProperty( "user.dir" ) + "/src/test/resources/drivers/win/firefox/geckodriver.exe" );
            }
            capabilities = DesiredCapabilities.firefox();
            FirefoxOptions options = new FirefoxOptions();
            options.setCapability( FirefoxOptions.FIREFOX_OPTIONS, capabilities );
            driver = new FirefoxDriver( options );
        } else if (browser.equalsIgnoreCase( "chrome" )) {
            if (LOCAL_OS.is( Platform.MAC )) {
                System.setProperty( "webdriver.chrome.driver",
                        System.getProperty( "user.dir" ) + "/src/test/resources/drivers/mac/chromedriver" );
            }
            if (LOCAL_OS.is( Platform.WINDOWS )) {
                System.setProperty( "webdriver.chrome.driver",
                        System.getProperty( "user.dir" ) + "/src/test/resources/drivers/win/chromedriver.exe" );
            }
            capabilities = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            capabilities.setCapability( ChromeOptions.CAPABILITY, options );
            driver = new ChromeDriver( options );


        }
        driver.manage().window().maximize();
        driver.get( Config.appURL );
        return driver;
    }

    @AfterMethod
    protected void afterInvocation(ITestResult result) throws IOException {
        if (driver != null) {

            if (!result.isSuccess()) {
                captureScreenshot( result );
            }

            driver.quit();
        }
    }

    private void captureScreenshot(ITestResult result) throws IOException {
        String screenshotDirectory = System.getProperty( "user.dir" ) + separator + "out" + separator + "screenshots";
        String screenshotSavePath =
                screenshotDirectory + separator + result.getMethod().getRealClass().getSimpleName() + " "
                        + now().format( ofPattern( "yyyy-MM-dd HH-mm-ss-SSS" ) ) + ".png";

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs( FILE );
        FileUtils.copyFile( screenshot, new File( screenshotSavePath ) );
        System.out.println(
                format( "\nTest Method \"%s\" failed.\nScreenshot was saved on:\n%s\n",
                        result.getMethod().getMethodName(), screenshotSavePath ) );
    }

    @DataProvider(name = "testableBrowsers")
    protected Object[][] testableBrowsersDataProvider() {


        String currentPlatform = LOCAL_OS.toString();

        return new Object[][]{
                new Object[]{"chrome", null, currentPlatform},
                //new Object[]{"firefox", null, currentPlatform}
        };

    }


}
