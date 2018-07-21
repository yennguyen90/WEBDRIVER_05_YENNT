package selenium_api;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.util.Random;
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
	  	//Step 01 - Truy cập vào trang: http://live.guru99.com/
		//Step 02 - Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(), 'My Account' )]")).click();
		//Step 03 - Nhập email invalid: 123434234@12312.123123
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		//Step 04 - Click Login button
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
	  
		//Step 05 - Verify error message xuất hiện:  Please enter a valid email address. For example johndoe@domain.com.
	  String emailErrMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
	  AssertJUnit.assertEquals("Please enter a valid email address. For example johndoe@domain.com.", emailErrMsg);

  }
  
  @Test
  public void TC_04_LoginPassIncorrect() {
	     // Step 01 - Truy cập vào trang: http://live.guru99.com/
		 //  Step 02 - Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(), 'My Account' )]")).click();
		 // Step 03 - Nhập email correct and password incorrect: automation@gmail.com/ 123
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation"+ randomEmail()+"@gmail.com");
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		 // Step 04 - Click Login button
	  driver.findElement(By.xpath("//button[@id='send2']")).click();
		 //  Step 05 - Verify error message xuất hiện: Please enter 6 or more characters without leading or trailing spaces.
	  String passErrMsg =  driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
	  AssertJUnit.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", passErrMsg);
	  
  }
  
  @Test
  public void TC_05_CreateAccount() throws InterruptedException  {
	//Step 01 - Truy cập vào trang: http://live.guru99.com/
			//Step 02 - Click vào link "My Account" để tới trang đăng nhập
		  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(), 'My Account' )]")).click();
		//  Step 03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
		  driver.findElement(By.xpath("//a[contains(@title,'Create an Account')]")).click();
		  //Step 04 - Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password
		  driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("yen");
		  driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("thi");
		  driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("nguyen");
		
		  driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("automation"+ randomEmail() + "@gmail.com");
		  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Yen123$");
		  driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("Yen123$");
		 // (Lưu ý: Tạo random cho dữ liệu tại field Email Address)
		//  Step 05 - Click REGISTER button
		  driver.findElement(By.xpath("//button[@title=\"Register\"]")).click();
		//  Step 05 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store.
		  String successMsg = driver.findElement(By.xpath("//span[contains(text(),'Thank you for registering with Main Website Store.')]")).getText();
		  AssertJUnit.assertEquals("Thank you for registering with Main Website Store.",successMsg);
		//  Step 06 - Logout khỏi hệ thống
		  driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[contains(text(),'Account')]")).click();
		  driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		//  Step 07 - Kiểm tra hệ thống navigate về Home page sau khi logout thành công
		  Thread.sleep(10000);
		 String urlhomepage =  driver.getCurrentUrl();
		 AssertJUnit.assertEquals("http://live.guru99.com/index.php/",urlhomepage);
  }
  
 
  @AfterClass
 
  public void afterClass() {
	 
	  driver.quit();
  }
  public int randomEmail() {
	  Random random = new Random();
	  int number = random.nextInt(999999);
	  return number;
  }

}

