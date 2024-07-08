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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class testScenario3 
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
public testScenario3(String browserName, String browserVersion, String osVersion) 
{
  this.browserName = browserName;
  this.browserVersion = browserVersion;
  this.osVersion = osVersion;
}


public void testscenario()
{
	WebDriver localDriver = driver.get();
	
	localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	  
	  //Open LambdaTest’s Selenium Playground from
	localDriver.get("https://www.lambdatest.com/selenium-playground");
	  
	  //click “Input Form Submit” under “Input Forms”.
	  localDriver.findElement(By.linkText("Input Form Submit")).click();
	  
	  
	  //Click “Submit” without filling in any information in the form.
	  WebElement submit = localDriver.findElement(By.xpath("//*[contains(text(), 'Submit')]"));
	  submit.click();
	  
	  // Locate the name fields
    WebElement nameField = localDriver.findElement(By.cssSelector("input[name='name']"));
    
    // Assert “Please fill in the fields” error message.
    JavascriptExecutor js = (JavascriptExecutor) localDriver;
    Boolean isValid = (Boolean) js.executeScript("return arguments[0].checkValidity();", nameField);

    if (isValid) {
        System.out.println("Validation message is not displayed.");
    } else {
  	  String validationMessage = (String) js.executeScript("return arguments[0].validationMessage;", nameField);
        //System.out.println("Validation message is displayed: " + validationMessage);
        Assert.assertEquals(validationMessage, "Please fill out this field.", "Not same");
    }
    
    
    //Fill in Name, Email, and other fields.
    nameField.sendKeys("Sooraj");
    WebElement email=localDriver.findElement(By.cssSelector("#inputEmail4"));
    email.sendKeys("sooraj@gmail.com");
    WebElement password=localDriver.findElement(By.xpath("//*[@id='inputPassword4']"));
    password.sendKeys("Soorya@123456");
    WebElement company = localDriver.findElement(By.xpath("//*[@name='company']"));
    company.sendKeys("TCS");
    WebElement website = localDriver.findElement(By.cssSelector("input[name='website']"));
    website.sendKeys("https://onlinetcs.com");
    WebElement city = localDriver.findElement(By.id("inputCity"));
    city.sendKeys("Karkala");
    WebElement addressone=localDriver.findElement(By.xpath("//*[@id='inputAddress1']"));
    addressone.sendKeys("hello world");
    WebElement addresstwo=localDriver.findElement(By.xpath("//*[@id='inputAddress2']"));
    addresstwo.sendKeys("welcome to india");
    WebElement state = localDriver.findElement(By.cssSelector("input[id='inputState']"));
    state.sendKeys("orlando");
    WebElement zipcode = localDriver.findElement(By.cssSelector("input[id='inputZip']"));
    zipcode.sendKeys("32819");
    
    //From the Country drop-down, select “United States” using the text property
    WebElement country = localDriver.findElement(By.xpath("//*[@name='country']"));
    Select countryvalue = new Select(country);
    countryvalue.selectByVisibleText("United States");
    
    //Fill all fields and click “Submit”.     
    submit.click();
    
    //validate the success message “Thanks for contacting us, we will get back to you shortly.” on the screen.
    String result = localDriver.findElement(By.xpath("//*[@class='success-msg hidden']")).getText();
    
    Assert.assertEquals(result, "Thanks for contacting us, we will get back to you shortly.", "Unable to validate the successfull message");
}

@AfterMethod
public void tearDown() 
{
	WebDriver localDriver = driver.get();
	localDriver.quit();
}

}