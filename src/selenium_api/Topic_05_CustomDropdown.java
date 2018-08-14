package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_05_CustomDropdown {
	WebDriver driver;
	WebDriverWait wait;
	 @BeforeClass
	  public void beforeClass() {
			  driver = new FirefoxDriver();
			  wait = new WebDriverWait(driver, 30);
			  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
			  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			  driver.manage().window().maximize();
	  }

	 
	 @Test
	  public void TC_01_Jquery_() throws Exception {
		 //Jquery
		 selectCustomDropdown("//span[@id='speed-button']","//ul[@id='speed-menu']/li/div","Faster");
		 Thread.sleep(2000);
		 Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text' and text()='Faster']")).isDisplayed());
		 selectCustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']/li/div","19");
		 Thread.sleep(2000);
		 Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());

		 //Angular
		 //selectCustomDropdown("","","");
		 //Thread.sleep(2000);
	 }
	 
  public void selectCustomDropdown(String dropdown, String listItem,String Value) throws Exception {
	  //Click vào dropdown
	 WebElement dropdownElement =  driver.findElement(By.xpath(dropdown));
	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownElement);
	 dropdownElement.click();
	 Thread.sleep(500); 
	 //Get tất cả item trong dropdown vào 1 list element (List <WebElement>)
	 List<WebElement> allItems = driver.findElements(By.xpath(listItem));
	  //Wait để tất cả phần tử trong dropdown được hiển thị
	  wait.until(ExpectedConditions.visibilityOfAllElements(allItems));
	 
	  //Dùng vòng lặp for duyệt qua từng phần tử sau đó getText
	  for(WebElement item : allItems) {
			 if(item.getText().equals(Value)) {
				 //scroll to item
				 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
				 Thread.sleep(3000);
				 item.click();
				 Thread.sleep(3000);
				 break;
			 }
	  }
  }
 
//
  @AfterClass
  public void afterClass() throws Exception {
	  Thread.sleep(3000);
	  driver.quit();
	  
  }

}
