package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.util.Asserts;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_UserInteraction {
	WebDriver driver;
	
  @BeforeClass
  public void beforeClass() {
		  driver = new FirefoxDriver();
		// Chrome
		//System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		//  driver = new ChromeDriver();
		// IE
		//System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		//driver = new InternetExplorerDriver();
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
  }
  
  //@Test
  public void TC_01_HoverMouse() {
	  driver.get("http://daominhdam.890m.com/");
	  WebElement hoverText = driver.findElement(By.xpath("//a[text()='Hover over me']"));
	  Actions action = new Actions(driver);
	  action.moveToElement(hoverText).perform();
	  String TooltipText = driver.findElement(By.xpath("//div[@class='tooltip-inner']")).getText();
	  Assert.assertEquals(TooltipText,"Hooray!");
  }
  
  //@Test
  public void TC_02_HoverMouse() {
	  driver.get("http://www.myntra.com/");
	  WebElement hoverprofileText = driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));
	  Actions action = new Actions(driver);
	  action.moveToElement(hoverprofileText).perform();
	  driver.findElement(By.xpath("//a[text()='login']")).click();
	  // verify form được hiển thị 
	WebElement loginForm = driver.findElement(By.xpath("//div[@class='login-box']"));
	Assert.assertTrue(loginForm.isDisplayed());
	
  }
  
  //@Test
  public void TC_02_ClickAndHold_SelectMultipleItem() {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  List<WebElement> listItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	  Actions moveItem = new Actions(driver);
	  moveItem.clickAndHold(listItems.get(0)).moveToElement(listItems.get(3)).release().perform();
	  
	  List<WebElement> selected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
	  Assert.assertEquals(selected.size(), 4);
  }
  
  //@Test
  public void TC_03_DoubleClick() {
	  driver.get("http://www.seleniumlearn.com/double-click");
	  WebElement DoubleClickText = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
	  Actions action  = new Actions(driver);
	  action.doubleClick(DoubleClickText).perform();
	  //Step 03 - Verify text trong alert được hiển thị: 'The Button was double-clicked.'
	  Alert alert = driver.switchTo().alert();
	  Assert.assertEquals(alert.getText(),"The Button was double-clicked.");
	  //Step 04 - Accept Javascript alert
	  alert.accept();
	  
  }
  
 // @Test
  public void TC_04_RightClick() throws Exception {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  WebElement RightClick = driver.findElement(By.xpath("//span[text()='right click me']"));
	  Actions action = new Actions(driver);
	  action.contextClick(RightClick).perform();
	  WebElement quit = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]/span[text()='Quit']"));
	  action.moveToElement(quit).perform();

	  WebElement hoverQuit = driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']"));
	  hoverQuit.click();
	  Thread.sleep(3000);
	  Alert alert = driver.switchTo().alert();
	  
	  alert.accept();
	  
  }
  //@Test
  public void TC_05_01_DragAnDrop() {
	  driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
	  WebElement sourceButton = driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement targetButton = driver.findElement(By.xpath("//div[@id='droptarget']"));
	  Actions action = new Actions(driver);
	  action.dragAndDrop(sourceButton, targetButton).build().perform();
	  Assert.assertEquals(targetButton.getText(),"You did great!");
  }
  @Test
  public void TC_05_02_DragAndDrop() {
	  driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
	  WebElement sourceButton = driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement TargetButton = driver.findElement(By.xpath("//div[@id='droppable']"));
	  Actions action = new Actions(driver);
	  action.dragAndDrop(sourceButton, TargetButton).build().perform();
	  Assert.assertEquals(TargetButton.getText(),driver.findElement(By.xpath("//div[@id='droppable']/p")).getText());
	  
  }
  
  @AfterClass
  public void afterClass() throws Exception {
	 Thread.sleep(3000);
	 driver.quit();
  }

}
