package Testscripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class TestScenario2 
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
public TestScenario2(String browserName, String browserVersion, String osVersion) 
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
	  
	  //click “Drag & Drop Sliders” under “Progress Bars & Sliders”.
	localDriver.findElement(By.linkText("Drag & Drop Sliders")).click();
	  
	  //Select the slider “Default value 15” and drag the bar to make it 95 by validating whether the range value shows 95.
	  Actions act=new Actions(localDriver);
	  WebDriverWait wait = new WebDriverWait(localDriver, Duration.ofSeconds(10));
			  
	  WebElement slider=localDriver.findElement(By.xpath("//*[@id='slider3']//child::input[@type='range']"));
	  
	  WebElement rangevalue = localDriver.findElement(By.id("rangeSuccess"));
	  
	  int targetvalue = 95;
	  int currentvalue = Integer.parseInt(rangevalue.getText());	  
	  
	// Initialize JavaScript Executor
    JavascriptExecutor js = (JavascriptExecutor) localDriver;

	  	  
	  while(currentvalue < targetvalue)
	  {
		  //act.dragAndDropBy(slider, stepsize, 0).perform();
		  //act.clickAndHold(slider).moveByOffset(1, 0).release().perform();
		  
		  //js.executeScript("arguments[0].value = arguments[0].value + 1; arguments[0].dispatchEvent(new Event('input'));", slider);
		  
		  // Calculate the next value to set for the slider
        int nextValue = Math.min(currentvalue + 1, targetvalue);

        // Set the slider value using JavaScript
        js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));", slider, nextValue);
        
		  //wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(rangevalue, String.valueOf(currentvalue))));
		  
		  currentvalue = Integer.parseInt(rangevalue.getText());
		  
	  }
	  

	  Assert.assertEquals(targetvalue, Integer.parseInt(rangevalue.getText()));
	  
	  System.out.println("Rangevalue after sliding is "+Integer.parseInt(rangevalue.getText()));
}

@AfterMethod
public void tearDown() 
{
	WebDriver localDriver = driver.get();
	localDriver.quit();
}

}
