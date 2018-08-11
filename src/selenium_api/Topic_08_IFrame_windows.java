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

public class Topic_08_IFrame_windows {
	WebDriver driver;
	WebDriverWait wait;
	
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  wait = new WebDriverWait(driver, 10);
		// Chrome
		//System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		//  driver = new ChromeDriver();
		// IE
		//System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		//driver = new InternetExplorerDriver();
					
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
  }
  @Test
  public void TC_01_() {
	  driver.get(" http://www.hdfcbank.com/");
	  //switch to iframe 
	  //step 01 : check close popup display
	  List<WebElement> notiIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  if(notiIframe.size() > 0) {
		  driver.switchTo().frame(notiIframe.get(0));
		//step 02
		 // driver.findElement(By.xpath("//div[@id='div-close']")).click(); 
		  WebElement closeIcon = driver.findElement(By.xpath("//div[@id='div-close']"));
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("arguments[0].click();", closeIcon);
		  System.out.println("Close popup");
		  //Switch to Topwindows
		  driver.switchTo().defaultContent();
		  
	  }
	  //step 03
	  WebElement LockingIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
	  driver.switchTo().frame(LockingIframe);
	  WebElement MessText = driver.findElement(By.xpath("//span[@id='messageText']"));
	  String MesText = MessText.getText();
	  Assert.assertEquals(MesText,"What are you looking for?");
	  //step 04: Verify banner image được hiển thị (switch qua iframe nếu có)
	  
	  //Switch to Topwindows
	  driver.switchTo().defaultContent();
	  WebElement imgIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
	  Assert.assertTrue(imgIframe.isDisplayed());
	  //Verify banner có đúng 6 images
	  driver.switchTo().frame(imgIframe);
	  By bannerimg_ = By.xpath("//div[@id='productcontainer']//img");
	  List<WebElement> imgBanners = driver.findElements(bannerimg_);
	  Assert.assertEquals(imgBanners.size(), 6);
	  // check all images is displayed
		  //issues :check display ở div visible 
		 //Assert.assertTrue(img.isDisplayed()); -->issues :check display ở loctor invisible 
		// --> dùng wait check nó có tồn tại(presence) trong DOM.
	  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bannerimg_));
	  
	  //Step 05 - Verify flipper banner được hiển thị và có 8 items
	//Switch to Topwindows
	  driver.switchTo().defaultContent();
	  WebElement flipbannerX = driver.findElement(By.xpath("//div[@class='flipBanner']"));
	  Assert.assertTrue(flipbannerX.isDisplayed());
	  List<WebElement> ItemFlipbanner = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
	  Assert.assertEquals(ItemFlipbanner.size(), 8);
	  // check display
	  int i=0;
	  //for each
	  for(WebElement img : ItemFlipbanner) {
		  Assert.assertTrue(img.isDisplayed());
		  i++;
		  System.out.println("item " + i+ " is display !");
	  }
	  
	  
  }
  
  @Test
  public void TC_02_() {
  }
  
  @Test
  public void TC_03_() {
  }
  
  @Test
  public void TC_04_() {
  }
  
  @Test
  public void TC_05_() {
  }
  
  
  public void afterClass() throws Exception {
	//  Thread.sleep(3000);
	  driver.quit();
  }
}
