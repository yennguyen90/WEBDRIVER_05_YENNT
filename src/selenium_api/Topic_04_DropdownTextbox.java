package selenium_api;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.support.ui.Select;

public class Topic_04_DropdownTextbox {
	WebDriver driver;
	String job1 = "//select[@id='job1']";
	
	 @BeforeClass
	  public void beforeClass() {
		  driver = new FirefoxDriver();
			// Chrome
			//System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		  //  driver = new ChromeDriver();
			// IE
			//System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
			//driver = new InternetExplorerDriver();
			
			
			 driver.manage().window().maximize();
			
	  }
  @Test
  public void TC_01_Dropdown() {
	  driver.get("http://daominhdam.890m.com/");
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 // Step 02 - Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
	 Select jobRole01 = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
	 Assert.assertFalse(jobRole01.isMultiple());  
	  // Step 03 - Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
	 jobRole01.selectByVisibleText("Automation Tester");
	  // Step 04 - Kiểm tra giá trị đã được chọn thành công
	 Assert.assertEquals("Automation Tester", jobRole01.getFirstSelectedOption().getText());
	  //Step 05 - Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue
	 jobRole01.selectByValue("manual");
	  //Step 06 - Kiểm tra giá trị đã được chọn thành công
	 Assert.assertEquals("Manual Tester", jobRole01.getFirstSelectedOption().getText());
	  // Step 07 - Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex
	 jobRole01.selectByIndex(3);
	  // Step 08 - Kiểm tra giá trị đã được chọn thành công
	 Assert.assertEquals("Mobile Tester", jobRole01.getFirstSelectedOption().getText());
	  //  Step 09 - Kiểm tra dropdown có đủ 5 giá trị
	 Assert.assertEquals(5, jobRole01.getOptions().size());

  }
  //@Test
  public void TC_03_Textbox_Textarea() {
	  driver.get("http://demo.guru99.com/v4"); 
	  
	  
	  
	   
	  //Step 02 - Đăng nhập với thông tin: User ID :	mngr145337 / nYqesud
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr145337");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("nYqesud");
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  //Step 03 - Chọn menu New Customer
	  driver.findElement(By.xpath("//a[text()=\"New Customer\"]")).click();
	  //Step 04 - Nhập toàn bộ dữ liệu đúng > Click Submit
	//input[@name='name']
	  WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
	  WebElement radm = driver.findElement(By.xpath("//input[@name='rad1' and @value='m']"));
	  WebElement radf = driver.findElement(By.xpath("//input[@name='rad1' and @value='f']"));
	  WebElement DateofBirth = driver.findElement(By.xpath("//input[@id='dob']"));
	  WebElement address = driver.findElement(By.xpath("//textarea[@name='addr']"));
	  WebElement city = driver.findElement(By.xpath("//input[@name='city']"));
	  WebElement state = driver.findElement(By.xpath("//input[@name='state']"));
	  WebElement pin = driver.findElement(By.xpath("//input[@name='pinno']"));
	  WebElement telephoneno = driver.findElement(By.xpath("//input[@name='telephoneno']"));
	  WebElement emailid = driver.findElement(By.xpath("//input[@name='emailid']"));
	  WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
	  WebElement Submit = driver.findElement(By.xpath("//input[@value='Submit']"));
	  String inputName = "yennguyen";
	  String inputAddr = "Long bien";
	  name.sendKeys(inputName);
	  radf.click();
	  DateofBirth.sendKeys("22/02/1992");
	  address.sendKeys(inputAddr);
	  city.sendKeys("HN");
	  state.sendKeys("xx");
	  pin.sendKeys("123456");
	  telephoneno.sendKeys("0973796710");
	  emailid.sendKeys("yennt"+randomEmail()+"@gmail.com");
	  password.sendKeys("Yen123");
	  Submit.click();
	//  Step 05 - Sau khi hệ thống tạo mới Customer thành công > Get ra thông tin của Customer ID
	//  Sử dụng xpath với following-sibling::td
	  String CustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	  //Step 06 - Chọn menu Edit Customer > Nhập Customer ID > Submit
	  driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	  driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(CustomerID);
	  driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
	  //Step 07 - Tại màn hình Edit Customer:
	  WebElement addrbox = driver.findElement(By.xpath("//textarea[@name='addr']"));
	  WebElement citybox = driver.findElement(By.xpath("//input[@name='city']"));
	  WebElement namebox = driver.findElement(By.xpath("//input[@name='name']"));
	  
	  //Verify giá trị tại 2 field: Customer Name và Address đúng với dữ liệu khi tạo mới New Customer tại Step 04
	  
	  String currentValueName = namebox.getAttribute("value");
	  String currentValueAddress = addrbox.getText();
	  
	  Assert.assertEquals(currentValueName, inputName);
	  Assert.assertEquals(currentValueAddress, inputAddr);
	  //Nhập giá trị mới tại 2 field Customer Address và City > Submit
	  
	  citybox.clear();
	  String newcity = "New York";
	  citybox.sendKeys(newcity);
	  addrbox.clear();
	  String newadd = "America";
	  addrbox.sendKeys(newadd);
	  driver.findElement(By.xpath("//input[@name='sub']")).click();
	  
	  //Verify giá trị tại 2 field: Customer Address và City đúng với dữ liệu sau khi đã Edit thành công
	  
	  Assert.assertEquals(newcity, driver.findElement(By.xpath("//td[text()=\"City\"]/following-sibling::td")).getText());
	  Assert.assertEquals(newadd, driver.findElement(By.xpath("//td[text()=\"Address\"]/following-sibling::td")).getText());
	  
  }
  
  public int randomEmail() {
	  Random random = new Random();
	  int number = random.nextInt(999999);
	  return number;
  }
 

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
