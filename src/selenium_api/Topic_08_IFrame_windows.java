package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;
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
  //@Test
  public void TC_01_IFrame() {
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
  
 // @Test
  public void TC_02_Windows() {
	      //Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
	  driver.get("http://daominhdam.890m.com/");
	  String parentId = driver.getWindowHandle();
		  //Step 02 - Click "Opening a new window: Click Here" link -> Switch qua tab mới
	  driver.findElement(By.xpath("//label[text()='Opening a new window:']/following-sibling::a")).click();
	  switchToChildWindowByGUID(parentId);
		  //Step 03 - Kiểm tra title của window mới = Google
	  Assert.assertEquals(driver.getTitle(), "Google");
		  //Step 04 - Close window mới
	  driver.close();
		  //Step 05 - Switch về parent window
  }
  
  @Test
  public void TC_03_() {
	      // Step 01 - Truy cập vào trang: http://www.hdfcbank.com/
	  driver.get("http://www.hdfcbank.com/");
		 // Step 02 - Kiểm tra và close quảng cáo nếu có xuất hiện
	  List<WebElement> notiIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  if(notiIframe.size() > 0) {
		  driver.switchTo().frame(notiIframe.get(0));
		//step 02
		  //driver.findElement(By.xpath("//div[@id='div-close']")).click(); 
		  WebElement closeIcon = driver.findElement(By.xpath("//div[@id='div-close']"));
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("arguments[0].click();", closeIcon);
		  System.out.println("Close popup");
		  //Switch to Topwindows
		  driver.switchTo().defaultContent();
		  
	  }
	  String parentID = driver.getWindowHandle();
		  // Step 03 - Click Angri link -> Mở ra tab/window mới -> Switch qua tab mới
	  driver.findElement(By.xpath("//a[text()='Agri']")).click();
	  switchToChildWindowByGUID(parentID);
	  System.out.println("tilte mới = " + driver.getTitle() );
	 // Step 04 - Click Account Details link -> Mở ra tab/window mới -> Switch qua tab mới
	  driver.findElement(By.xpath("//p[text()='Account Details']")).click();
	  switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
	  //Step 05- Click Privacy Policy link (nằm trong frame) -> Mở ra tab/window mới -> Switch qua tab mới
	  WebElement Footerframe = driver.findElement(By.xpath("//frame[@name='footer']"));
	  driver.switchTo().frame(Footerframe);
	  driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
	  switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	  //Step 06- Click CSR link on Privacy Policy page
	  driver.findElement(By.xpath("//a[@title='Corporate Social Responsibility']"));
	  //Step 08 - Close tất cả popup khác - chỉ giữ lại parent window (http://www.hdfcbank.com/)
	  closeAllWithoutParentWindows(parentID);
	  System.out.println(driver.getCurrentUrl());
	  
  }
  // 2 windows
  public void switchToChildWindowByGUID(String parentID) {
      Set<String> allWindows = driver.getWindowHandles();
      for (String runWindow : allWindows) {
                  if (!runWindow.equals(parentID)) {
                              driver.switchTo().window(runWindow);
                              break;
                  }
      }
}
  // > 2windows
  public void switchToWindowByTitle(String title) {
      Set<String> allWindows = driver.getWindowHandles();
      for (String runWindows : allWindows) {
                  driver.switchTo().window(runWindows);
                  String currentWin = driver.getTitle();
                  if (currentWin.equals(title)) {
                              break;
                  }
      }
}
  
  //Close all windows without parent window
  public boolean closeAllWithoutParentWindows(String parentWindow) {
              Set<String> allWindows = driver.getWindowHandles();
              for (String runWindows : allWindows) {
                          if (!runWindows.equals(parentWindow)) {
                                      driver.switchTo().window(runWindows);
                                      driver.close();
                          }
              }
              driver.switchTo().window(parentWindow);
              if (driver.getWindowHandles().size() == 1)
                         return true;
              else
                         return false;
  }
  public void afterClass() throws Exception {
	//  Thread.sleep(3000);
	  driver.quit();
  }
}
