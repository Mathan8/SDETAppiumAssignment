package SDETAssignment.KhanAcademy;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class TC_01 extends capability{

	AndroidDriver<AndroidElement>driver;
	
	@BeforeTest
	public void bt() throws IOException, InterruptedException{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(5000);
		
	}
	
	@Test
	public void TC_001() throws InterruptedException, IOException {
		
		service = startserver();
		driver = Capability(appPackage,appActivity,deviceName,chromedriverExecutable);
		// explicit wait - to wait for the button to be click-able
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("UiSelector().text(\"Dismiss\")")));
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Dismiss\")")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Dismiss\")")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sign in\")")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sign in\")")).click();
		driver.findElement(MobileBy.AccessibilityId("Enter an e-mail address or username")).sendKeys("mathan.ibt@gmail.com");
		driver.findElement(MobileBy.AccessibilityId("Password")).sendKeys("SDETAppiumAssignment");
		driver.findElement(MobileBy.xpath("//android.widget.Button[@content-desc='Sign in']/android.widget.TextView")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("UiSelector().text(\"Join class\")")));
		String ActualText = driver.findElements(MobileBy.xpath("//*[@class='android.widget.TextView']")).get(3).getText();
		String ExpectedText = "Join class";
		Assert.assertEquals(ActualText, ExpectedText);		
	}
	
	@Test (priority = 2)
	public void TC_002() throws InterruptedException {
		
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Join class\")")).click();
		driver.findElement(MobileBy.AccessibilityId("e.g. ABC123 or teacher@example.com")).sendKeys("a1b2c3@gmail.com");
		driver.hideKeyboard();
		driver.findElement(MobileBy.xpath("//*[@class='android.widget.ScrollView']")).click();;
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"ADD\")")).click();
		String AddNotif = driver.findElement(MobileBy.id("android:id/alertTitle")).getText();
		String ExpectedMessage = "Teacher added";
		Assert.assertEquals(AddNotif, ExpectedMessage);
		driver.findElement(MobileBy.id("android:id/button1")).click();
		Thread.sleep(3000);
		}
	
	
	@Test (priority = 3)
	public void TC_003() throws InterruptedException {
		
		driver.findElement(MobileBy.xpath("//android.widget.ImageView[@content-desc='Settings']")).click();
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator("UiSelector().text(\"Terms of service\")")));
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Terms of service\")")).click();
		Thread.sleep(5000);
		driver.findElements(MobileBy.xpath("//*[@class='android.widget.TextView']")).get(0).click();;
		driver.findElement(MobileBy.id("android:id/button_once")).click();
		Thread.sleep(10000);
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
		    System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
		}
		    driver.context("WEBVIEW_chrome");
		    driver.pressKey(new KeyEvent(AndroidKey.BACK));
		    driver.context("NATIVE_APP");
		}
	
	
	@Test (priority = 4)
	public void TC_004() throws InterruptedException {
		
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Manage teachers\")")).click();
		driver.findElement(MobileBy.AccessibilityId("Delete")).click();
		Thread.sleep(3000);
		driver.findElement(MobileBy.id("android:id/button1")).click();
		driver.findElement(MobileBy.xpath("//android.widget.Button[@content-desc='Dismiss']/android.widget.ImageView")).click();
		Thread.sleep(3000);
		}
	
	
	@Test (priority = 5)
	public void TC_005() throws InterruptedException {
		
		driver.findElement(By.xpath("//android.widget.Button[@index='4']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("android:id/button1")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//android.widget.Button[@content-desc='Back']")).click();
		service.stop();
		}	

}
	
	
	
	