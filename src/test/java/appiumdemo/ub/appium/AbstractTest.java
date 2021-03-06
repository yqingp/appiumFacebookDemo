package appiumdemo.ub.appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class AbstractTest {

	protected static WebDriver driver = null;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(ITestContext testContext) {

	}

	@BeforeTest(alwaysRun = true)
	public void appiumTestSetUp() throws MalformedURLException, InterruptedException {
		Log.logInfo("Starting Regression Test......");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", Configuration.testDeviceName);
		capabilities.setCapability("platformVersion", Configuration.testPlatformVersion);
		capabilities.setCapability("platformName", Configuration.testPlatformName);
		capabilities.setCapability("browserName", Configuration.testBrowserName);

		capabilities.setCapability("appPackage", Configuration.testAppPackage);
		capabilities.setCapability("appActivity", Configuration.testAppActivity);
		
		Log.logInfo("Initializing appium remote driver...");

		driver = new RemoteWebDriver(new URL(Configuration.testAppiumServer), capabilities);
		Utils.waitForDuration(driver, 10);
		Utils.pauseDriverForDuration(8000);

	}

	@AfterTest
	public void appiumTestTearDown() {
		Log.logInfo("Quit appium remote driver and finish the test..");
		if (driver != null) {
			driver.quit();
		}

	}

	public static WebDriver getDriver() {
		// TODO Auto-generated method stub
		return driver;
	}

}
