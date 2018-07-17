package selenium_api;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_02_XpathCss {
	WebDriver driver;
 @BeforeClass
 public void beforeClass() {
	 driver = new FirefoxDriver();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 driver.get("http://live.guru99.com/");
	 
 }

  @Test
  public void TC_01_URLandTitle() {
  }
  
  @Test
  public void TC_02_LoginEmpty() {
	  //click vao link My account
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(), 'My Account' )]")).click();
	  // de trong username, pass
	  // click Loginbutton
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  
	  String emailErrMsg =  driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
	  AssertJUnit.assertEquals("This is a required field.", emailErrMsg);
	  String passErrMsg =  driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
	  AssertJUnit.assertEquals("This is a required field.", passErrMsg);
	  
  }
  
  @Test
  public void TC_03_LoginEmailInvalid() {
  }
  
  @Test
  public void TC_04_LoginPassIncorrect() {
  }
  
  @Test
  public void TC_05_CreateAccount() {
  }
  
  
 
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}

