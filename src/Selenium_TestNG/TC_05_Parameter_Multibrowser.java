package Selenium_TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class TC_05_Parameter_Multibrowser {
	WebDriver driver;
	@Parameters("browser")
	 @BeforeMethod
	  public void beforeMethod(String browserName) {
		 driver = new FirefoxDriver();
		 
		 if(browserName.equals("chrome")) {
			 System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			 driver = new ChromeDriver();
		 } else if (browserName.equals("firefox")) {
			 driver = new FirefoxDriver();
		 } else {
			 System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
			 driver = new InternetExplorerDriver();
		 }
		 
		 driver.get("http://live.guru99.com/index.php/customer/account/login/");
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 }
	 
	 @Test()
	 public void TC_01_LoginToSystem() {
		  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automationvalid_01@gmail.com");
		  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("111111");
		  driver.findElement(By.xpath("//button[@id='send2']")).click();
		  
		  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		  
	 }

	  @AfterMethod
	  public void afterClass() {
		  driver.quit();
	  }

}
