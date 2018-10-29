package Selenium_TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_01_Annotations {
	WebDriver driver;
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.get("http://live.guru99.com/index.php/customer/account/login/");
	  
  }

  @Test()
  public void TC_01_CheckUrl() {
	  String loginUrl = driver.getCurrentUrl();
	  Assert.assertEquals(loginUrl, "http://live.guru99.com/index.php/customer/account/login/");
  }
  
  @Test()
  public void TC_01_CheckTitle() {
	  String loginTitle = driver.getTitle();
	  Assert.assertEquals(loginTitle, "Customer Login");
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
	  System.out.println("afterClass");
  }



}
