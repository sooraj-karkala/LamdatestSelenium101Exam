package Testscripts;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class TestScenario1 
{
	ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	String browserName;
	String browserVersion;
	String osVersion;
	
	@BeforeMethod
	
	public void setup() throws MalformedURLException
	{
		
	    
        System.out.println("Starting test with browser: " + browserName + ", version: " + browserVersion + ", OS: " + osVersion);

		
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		  ltOptions.put("username", "soorajkarkala99");
		  ltOptions.put("accessKey", "zFIu4BsyNhae86CqBemODKxknNp9OMo4oQDnc4wUpP0OxN5iqJ");
		  ltOptions.put("visual", true);
		  ltOptions.put("video", true);
		  ltOptions.put("network", true);
		  ltOptions.put("project", "Untitled");
		  ltOptions.put("w3c", true);
		
		  WebDriver localDriver = null;
		  
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			ChromeOptions browserOptions = new ChromeOptions();
			browserOptions.setPlatformName(osVersion);
			browserOptions.setBrowserVersion(browserVersion);
			browserOptions.setCapability("LT:Options", ltOptions);
			localDriver=new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"),browserOptions);
			
		}
		else if(browserName.equalsIgnoreCase("MicrosoftEdge"))
		{
			EdgeOptions browserOptions = new EdgeOptions();
			browserOptions.setPlatformName(osVersion);
			browserOptions.setBrowserVersion(browserVersion);
			browserOptions.setCapability("LT:Options", ltOptions);
			localDriver=new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"),browserOptions);
			
		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			FirefoxOptions browserOptions = new FirefoxOptions();
			browserOptions.setPlatformName(osVersion);
			browserOptions.setBrowserVersion(browserVersion);
			browserOptions.setCapability("LT:Options", ltOptions);
			localDriver=new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"),browserOptions);
		}
		else if(browserName.equalsIgnoreCase("Internet Explorer"))
		{
			InternetExplorerOptions browserOptions = new InternetExplorerOptions();
			browserOptions.setPlatformName(osVersion);
			browserOptions.setBrowserVersion(browserVersion);
			browserOptions.setCapability("LT:Options", ltOptions);
			localDriver=new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"),browserOptions);
		}
		else
		{
			System.out.println("Browser not found");
		}
		
		driver.set(localDriver);
	}
	
	
@Test
public void initializeTestScenario() 
{
//    this.browserName = browserName;
//	this.browserVersion = browserVersion;
//    this.osVersion = osVersion;
    testscenario();
}
	
@Factory(dataProvider = "browserAndOsProvider", dataProviderClass = TestDataProvider.class)
public TestScenario1(String browserName, String browserVersion, String osVersion) 
{
  this.browserName = browserName;
  this.browserVersion = browserVersion;
  this.osVersion = osVersion;
}

public void testscenario()
{
	WebDriver localDriver = driver.get();
	
	  //Open LambdaTest’s Selenium Playground from
	localDriver.get("https://www.lambdatest.com/selenium-playground");
	  
	  //Click “Simple Form Demo” under Input Forms.
	localDriver.findElement(By.linkText("Simple Form Demo")).click();
	  
	  //Validate that the URL contains “simple-form-demo”
	  String currentURL= localDriver.getCurrentUrl();
	  Assert.assertTrue(currentURL.contains("simple-form-demo"), "URL does not contain 'simple-form-demo'");
	  
	  //Create a variable for a string value E.g: “Welcome to LambdaTest”.
	  String value= "Welcome to LambdaTest";
	  
	  //Use this variable to enter values in the “Enter Message” text box.
	  localDriver.findElement(By.xpath("//input[@id='user-message']")).sendKeys(value);
	  
	  //Click “Get Checked Value”
	  localDriver.findElement(By.cssSelector("#showInput")).click();
	  
	  //Validate whether the same text message is displayed in the right-hand panel under the “Your Message:” section.
	  Assert.assertEquals(localDriver.findElement(By.id("message")).getText(), value);
}

@AfterMethod
public void tearDown() 
{
	WebDriver localDriver = driver.get();
	localDriver.quit();
}

}
