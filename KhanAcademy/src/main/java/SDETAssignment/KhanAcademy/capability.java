package SDETAssignment.KhanAcademy;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class capability {
	
	protected static String deviceName;
	protected static String appPackage;
	protected static String appActivity;
	protected static String chromedriverExecutable;
	
	
public AppiumDriverLocalService service;
	
	public AppiumDriverLocalService startserver()
	{
		boolean flag = checkifserverisRunning(4723);
		if(!flag)
			{
			service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                    .withAppiumJS(new File("C:\\Users\\MathanRajS\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                    .withIPAddress("0.0.0.0").usingPort(4723));
			service.start();
			}
		return service;
	}
	
	public static boolean checkifserverisRunning(int port)
    {
        boolean isserverRunning = false;
        ServerSocket serversocket;
        try
        {
            serversocket = new ServerSocket(port);
	    serversocket.close();
        }
        catch (Exception e) {
            isserverRunning= true;
        }
        finally {
            serversocket=null;
        }
        return isserverRunning;
    }
	
	
	public static void startEmulator() throws IOException, InterruptedException {
		  Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\emulator.bat");
		  Thread.sleep(20000);
	  }

	public static AndroidDriver<AndroidElement> Capability(String deviceName, String appPackage, String appActivity, String chromedriverExecutable) throws IOException, InterruptedException{
		
			FileReader fs = new FileReader(System.getProperty("user.dir")+"//src//main//java//global.Properties");
			Properties pr = new Properties();
			pr.load(fs);
			deviceName = pr.getProperty("deviceName");
			if(deviceName.contains("Mathan")) {
				startEmulator();
			}
			appPackage = pr.getProperty("appPackage");
			appActivity = pr.getProperty("appActivity");
			chromedriverExecutable = pr.getProperty("chromedriverExecutable");		
		
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
			capability.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, chromedriverExecutable);
			capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
			capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
			AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),capability);
			return driver;
	}

}
