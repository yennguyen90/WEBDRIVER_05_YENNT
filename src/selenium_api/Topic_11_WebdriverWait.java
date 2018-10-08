package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class Topic_11_WebdriverWait {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		// wait = new WebDriverWait(driver, 30); //30s
		// Chrome
		// System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		// driver = new ChromeDriver();
		// IE
		// System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void Implicit_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//div[@id='start']//button")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']//h4")).getText(), "Hello World!");
		// div[@id='finish']//h4[text()='Hello World!']
	}

	@Test
  public void Explicit_Wait() {
	  driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	 // driver.findElement(By.xpath("//div[@id='start']//button")).click();
	 // Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']//h4")).getText(),"Hello World!");
	//div[@id='finish']//h4[text()='Hello World!']
	  
		 // Step 02 - Wait cho "Date Time Picker" được hiển thị (sử dụng: presence or visibility)
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='example']//div[@class='demo-container size-narrow']")));
		 // Step 03 - In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = "No Selected Dates to display."
	  String DayBefore = driver.findElement(By.xpath("//div[@class='datesContainer']//span[@class='label']")).getText();
	  Assert.assertEquals("No Selected Dates to display.", DayBefore);
		//  Step 04 - Chọn ngày hiện tại (VD: 8/10/2018) (hoặc 1 ngày bất kì tương ứng trong tháng/ năm hiện tại)
	  driver.findElement(By.xpath("//a[text()='8']")).click();
		//  Step 05 - Wait cho đến khi "loader ajax" không còn visible (sử dụng: invisibility)
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		//  Step 06 - Wait cho selected date = 8 được visible ((sử dụng: visibility)
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='8']")));
		//  Step 07 - Verify ngày đã chọn bằng = ...
	  String DayAfter = driver.findElement(By.xpath("//div[@class='datesContainer']//span[@class='label']")).getText();
	  Assert.assertEquals("Monday, October 08, 2018", DayAfter);
  }

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
