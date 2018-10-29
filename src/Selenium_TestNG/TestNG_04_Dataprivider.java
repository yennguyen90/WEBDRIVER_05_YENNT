package Selenium_TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class TestNG_04_Dataprivider {
	WebDriver driver;
	 @BeforeMethod
	  public void beforeMethod() {
		 driver = new FirefoxDriver();
		 driver.get("http://live.guru99.com/index.php/customer/account/login/");
		 
	 }
  @Test(dataProvider = "loginToSystem")
  public void f(String userName, String PassWord) {
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys(userName);
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(PassWord);
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	  
  }

  @DataProvider
  public Object[][] loginToSystem() {
    return new Object[][] {
      new Object[] { "automationvalid_01@gmail.com", "111111" },
      new Object[] { "automationvalid_02@gmail.com", "111111" },
      new Object[] { "automationvalid_03@gmail.com", "111111" },
    };
  }
 

  @AfterMethod
  public void afterTest() {
	  driver.quit();
  }

}
