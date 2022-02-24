import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class DialNumberTest {
    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;
    //Elements By
    By startButton           = By.id("com.google.android.apps.messaging:id/start_chat_fab");
    By searchBox           = By.id("com.google.android.apps.messaging:id/recipient_text_view");

    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel_5_API_29");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "10.0");
        caps.setCapability("appPackage", "com.google.android.apps.messaging");
        caps.setCapability("appActivity", "com.google.android.apps.messaging.home.HomeActivity");
        caps.setCapability("noReset", "false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);
    }
    @Test
    public void basicTest() throws InterruptedException {
        //  MobileElement startButton=driver.findElement(By.id("com.google.android.apps.messaging:id/start_chat_fab"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(startButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys("55555555");
      //  wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).submit();
         driver.pressKey(new KeyEvent(AndroidKey.ENTER));
         Thread.sleep(10000);

    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

