import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static org.testng.Assert.assertNotEquals;

public class TouchActionsTest {

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;
    //Elements By
    By literature           = By.id("dragdrop.stufflex.com.dragdrop:id/btn_literature");
    By literature1         = MobileBy.AndroidUIAutomator("new UiSelector().text(\"LITERATURE\")");
    By drapElement = MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"dragdrop.stufflex.com.dragdrop:id/chooseA\")");




    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel_5_API_29");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "10.0");
       /* caps.setCapability("appPackage", "dragdrop.stufflex.com.dragdrop");
        caps.setCapability("appActivity", "dragdrop.stufflex.com.dragdrop.splash");*/
        caps.setCapability("noReset", "false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void dragNDropByElementTest() {
        Activity activity = new Activity("dragdrop.stufflex.com.dragdrop", "dragdrop.stufflex.com.dragdrop.splash");
        driver.startActivity(activity);
        WebDriverWait wait = new WebDriverWait(driver,10);
      wait.until(ExpectedConditions.visibilityOfElementLocated(literature1)).click();
      wait.until(ExpectedConditions.visibilityOfElementLocated(drapElement));
        AndroidElement dragElement         = (AndroidElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"dragdrop.stufflex.com.dragdrop:id/chooseA\")"));
        AndroidElement dropElement         = (AndroidElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"dragdrop.stufflex.com.dragdrop:id/answer\")"));

       int middleXCoordinate_dragElement=dragElement.getLocation().x+(dragElement.getSize().width/2);
        int middleYCoordinate_dragElement=dragElement.getLocation().y+(dragElement.getSize().height/2);

        int middleXCoordinate_dropElement=dropElement.getLocation().x+(dragElement.getSize().width/2);
        int middleYCoordinate_dropElement=dropElement.getLocation().y+(dragElement.getSize().height/2);

        /*First option*/

        TouchAction dragNDrop = new TouchAction(driver)
                .longPress(PointOption.point(middleXCoordinate_dragElement,middleYCoordinate_dragElement))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(middleXCoordinate_dropElement,middleYCoordinate_dropElement))
                .release();
        dragNDrop.perform();

    /*Second option*/

     /*   TouchAction dragNDrop = new TouchAction(driver)
                .longPress(element(dragElement))
                .moveTo(element(dropElement))
                .release();
        dragNDrop.perform();
*/
    }

    @Test
    public void verticalSwipeUpAndDown() throws InterruptedException {
        Activity activity = new Activity("com.h6ah4i.android.example.advrecyclerview", "com.h6ah4i.android.example.advrecyclerview.launcher.MainActivity");
        driver.startActivity(activity);
        WebDriverWait wait = new WebDriverWait(driver,10);
        By advanced         = By.xpath("//androidx.appcompat.app.ActionBar.Tab[@content-desc=\"Advanced\"]/android.widget.TextView");
        wait.until(ExpectedConditions.visibilityOfElementLocated(advanced)).click();
        By draggable = By.xpath("//android.widget.Button[@text='DRAGGABLE + SWIPEABLE']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(draggable)).click();

      /*  By listElement= By.xpath("//android.widget.TextView");
        wait.until(ExpectedConditions.visibilityOfElementLocated(listElement));
        List<MobileElement> list= driver.findElements(listElement);
        for (MobileElement e:list) {
            System.out.println(e.getAttribute("text"));
        }
*/
        Dimension dimension = driver.manage().window().getSize();
        int startX = (int) (dimension.width * 0.5);
        int startY = (int) (dimension.height * 0.8);

        int endX = (int) (dimension.width * 0.5);
        int endY = (int) (dimension.height * 0.2);


        TouchAction touch = new TouchAction(driver)
                .press(PointOption.point(startX,startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX,endY))
                .release();



        Thread.sleep(3000);

         dimension = driver.manage().window().getSize();
         startX = (int) (dimension.width * 0.5);
         startY = (int) (dimension.height * 0.2);

         endX = (int) (dimension.width * 0.5);
         endY = (int) (dimension.height * 0.8);


        TouchAction touch1 = new TouchAction(driver)
                .press(PointOption.point(startX,startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX,endY))
                .release();


        MultiTouchAction multiTouchAction = new MultiTouchAction(driver).add(touch).add(touch1);
        multiTouchAction.perform();

    }

    @Test
    public void horizontalSwipe(){

        Activity activity = new Activity("com.h6ah4i.android.example.advrecyclerview", "com.h6ah4i.android.example.advrecyclerview.launcher.MainActivity");
        driver.startActivity(activity);

        AndroidElement drag         = (AndroidElement) driver.findElement(By.xpath("//androidx.appcompat.app.ActionBar.Tab[@content-desc=\"Drag\"]/android.widget.TextView"));
        wait.until(ExpectedConditions.visibilityOf(drag)).click();

        AndroidElement expand         = (AndroidElement) driver.findElement( By.xpath("//androidx.appcompat.app.ActionBar.Tab[@content-desc=\"Expand\"]/android.widget.TextView"));
        wait.until(ExpectedConditions.visibilityOf(expand)).click();



        int midOfYCoordinate=drag.getLocation().y + (drag.getSize().height/2);
        int firstElementXCoordinate = drag.getLocation().x;
        int thirdElementYCoordinate = expand.getLocation().x;


        TouchAction    touch = new TouchAction(driver)
                .press(PointOption.point(thirdElementYCoordinate,midOfYCoordinate))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(firstElementXCoordinate,midOfYCoordinate))
                .release();
        touch.perform();


    }
}
