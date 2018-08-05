package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_Button_Radio_Checkbox_Alert {
  
	WebDriver driver;
	
	  @BeforeClass
	  public void beforeClass() {
		  driver = new FirefoxDriver();
		// Chrome
		//System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		//  driver = new ChromeDriver();
		// IE
		//System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		//driver = new InternetExplorerDriver();
					
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
	  }
	  
	 // @Test
	  public void TC_01_Button() {
		  driver.get("http://live.guru99.com/");
		  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='account-login']")).isDisplayed());
		  Assert.assertEquals(driver.getCurrentUrl(),"http://live.guru99.com/index.php/customer/account/login/");
		  // click vào button dùng Js Executor code
		 // driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		  clickElementByJavascript("//a[@title='Create an Account']");
		  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='account-create']")).isDisplayed());
		  Assert.assertEquals(driver.getCurrentUrl(),"http://live.guru99.com/index.php/customer/account/create/");

		  
	  }
	  
	 // @Test
	  public void TC_02_Checkbox() {
		  driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		  // Xpath của thẻ input bị invisible
		  String dualZoneCheckbox = "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
		  clickElementByJavascript(dualZoneCheckbox);
		  Assert.assertTrue(isElementSelected(dualZoneCheckbox));
		  unCheckCheckbox(dualZoneCheckbox);
		  Assert.assertFalse(isElementSelected(dualZoneCheckbox));
		  
	  }
	  
	  //@Test
	  public void TC_03_RadioButon() {
		  driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
		  // Xpath của thẻ input bị invisible
		  String valueSelected = "//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
		  clickElementByJavascript(valueSelected);
		  if(!isElementSelected(valueSelected)) {
			  clickElementByJavascript(valueSelected);
		  }
		  
	  }
	  
	  //@Test
	  public void TC_04_JSAlert() {
		  driver.get("http://daominhdam.890m.com/#");
		  driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		  Alert alert = driver.switchTo().alert(); // webBrowser
		  //get text
		  String alertText = alert.getText();
		  Assert.assertEquals(alertText, "I am a JS Alert");
		  //accept alert
		  alert.accept();
		  String resultText = driver.findElement(By.xpath("//p[@id='result']")).getText();
		  Assert.assertEquals(resultText, "You clicked an alert successfully");
	  }
	  
	  public void TC_05_JSConfirm() {
		  driver.get("http://daominhdam.890m.com/#");
		  driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		  Alert  alert = driver.switchTo().alert();
		  String alertText = alert.getText();
		  Assert.assertEquals(alertText, "I am a JS Confirm");
		  alert.dismiss();
		  String resultText = driver.findElement(By.xpath("//p[@id='result']")).getText();
		  Assert.assertEquals(resultText,"You clicked: Cancel");
		  
	  }
	  @Test
	  public void TC_06_JSPrompt() {
		  driver.get("http://daominhdam.890m.com/#");
		  driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		  Alert  alert = driver.switchTo().alert();
		  String alertText = alert.getText();
		  Assert.assertEquals(alertText, "I am a JS prompt");
		  alert.sendKeys("yenyen");
		  alert.accept();
		  String resultText = driver.findElement(By.xpath("//p[@id='result']")).getText();
		  Assert.assertEquals(resultText,"You entered: yenyen");
		  
	  }
	  public boolean isElementSelected(String locator) {
		  WebElement element = driver.findElement(By.xpath(locator));
		  return element.isSelected();
		  
	  }
	  
	  public void unCheckCheckbox(String locator) {
		  if(isElementSelected(locator)) {
			  clickElementByJavascript(locator);
		  }
	  }
	  
	  //ham common js excecutor
	  public void clickElementByJavascript(String locator) {
		    WebElement element = driver.findElement(By.xpath(locator));
		    JavascriptExecutor je = (JavascriptExecutor) driver;
		    je.executeScript("arguments[0].click();", element);
		}
	  
	  
	  @AfterClass
	  public void afterClass() {
		  //driver.quit();
		  
	  }

	}
