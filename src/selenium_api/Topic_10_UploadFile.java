package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_10_UploadFile {
	  WebDriver driver;
	  String projectDirectory = System.getProperty("user.dir");
	  String fileName = "h1.png";
	  String fileName_01 = "h1.png";
	  String fileName_02 = "h2.png";
	  String fileName_03 = "h3.png";
	  String uploadFilePath_01 = projectDirectory +"\\img\\" + fileName_01;
	  String uploadFilePath_02= projectDirectory +"\\img\\" + fileName_02;
	  String uploadFilePath_03 = projectDirectory +"\\img\\" + fileName_03;
	  
	  String uploadFilePath = projectDirectory + "\\img\\"+ fileName;
	  String chromeUpload = projectDirectory + "\\Upload\\chrome.exe";
	  String firefoxUpload = projectDirectory + "\\Upload\\firefox.exe";
	  String ieUpload = projectDirectory + "\\Upload\\ie.exe";
	
	  @BeforeClass
	  public void beforeClass() {
		//  driver = new FirefoxDriver();
		// Chrome
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
	    driver = new ChromeDriver();
		// IE
		//System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		//driver = new InternetExplorerDriver();
					
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
	  }
	 // @Test
	  public void TC_01_UploadfileBySendkeys() {
		 
		//      Step 01 - Truy cập vào trang: 
		  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		//	  Step 02 - Sử dụng phương thức sendKeys để upload file chạy cho 3 trình duyệt (IE/ Firefox/ Chrome)
		WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));
		upload.sendKeys(uploadFilePath);
		  //	  Step 03 - Kiểm tra file đã được tải lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='h1.png']")).isDisplayed());
	    driver.findElement(By.xpath("//table[@class='table table-striped']//button[@class='btn btn-primary start']")).click();
	    Assert.assertTrue(driver.findElement(By.xpath("//a[text()='h1.png']")).isDisplayed());
	    
	  
	  }
	 // @Test
	  public void TC_02_UploadfileByAutoIT() throws Exception {
		//      Step 01 - Truy cập vào trang:
		  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		//	  Step 02 - Sử dụng AutoIT để upload file chạy cho 3 trình duyệt (IE/ Firefox/ Chrome)
		// Firefox
         // WebElement uploadFirefox = driver.findElement(By.xpath("//input[@type='file']"));
        //  uploadFirefox.click();

          // IE 11
         // WebElement uploadIE = driver.findElement(By.xpath("//span[contains(text(),'Add files...')]"));
          //uploadIE.click();

          // Chrome
        WebElement uploadChrome = driver.findElement(By.cssSelector(".fileinput-button"));
        uploadChrome.click();
        Runtime.getRuntime().exec(new String[] { chromeUpload, uploadFilePath });  
		//Runtime.getRuntime().exec(new String[] {chromeUpload, uploadFilePath});
		//	  Step 03 - Kiểm tra file đã được tải lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='h1.png']")).isDisplayed());
	    driver.findElement(By.xpath("//table[@class='table table-striped']//button[@class='btn btn-primary start']")).click();
	    Assert.assertTrue(driver.findElement(By.xpath("//a[text()='h1.png']")).isDisplayed());
	  }
	 // @Test
	  public void TC_03_UploadfileByRobot() throws Exception {
		  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	      //define location của filename
	        StringSelection select = new StringSelection(uploadFilePath);
	        //Copy location to clipboard
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
	        //Click
	        WebElement uploadChrome = driver.findElement(By.cssSelector(".fileinput-button"));
	        uploadChrome.click();
	        Robot robot = new Robot();
	        Thread.sleep(1000);
	        //focus to textbox
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        // giả lập nhấn phím Ctrl-V
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        // giả lập nhả phím Ctrl-V
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_V);
	        Thread.sleep(1000);
	        //nhấn anter = click "open"
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
//	  	  Step 03 - Kiểm tra file đã được tải lên thành công
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='h1.png']")).isDisplayed());
		    driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		    Assert.assertTrue(driver.findElement(By.xpath("//a[text()='h1.png']")).isDisplayed());
		   // Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ fileName +"']")).isDisplayed());
	  }
	  @Test
	  public void TC_04_UploadMultipleFiles() {
		  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		  String file[] = {uploadFilePath_01, uploadFilePath_02, uploadFilePath_03};
		  for(int i = 0; i<file.length; i++) {
			  WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
			  uploadElement.sendKeys(file[i]);
		  }
		  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ fileName_01 +"']")).isDisplayed());
		  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ fileName_02 +"']")).isDisplayed());
		  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ fileName_03 +"']")).isDisplayed());
	 //click start
		  List<WebElement> startButton = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		  for(WebElement start : startButton) {
			  start.click();
		  }
		  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ fileName_01 +"']")).isDisplayed());
		  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ fileName_02 +"']")).isDisplayed());
		  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ fileName_03 +"']")).isDisplayed());
		  //check không phải là img lỗi (real img)
		  List<WebElement> images = driver.findElements(By.xpath("//table[@class='table table-striped']//img"));
		  for (WebElement img : images) {
			  Assert.assertTrue(checkAnyImageLoaded(img));
		  }
		  
		  
	  }
	  @AfterClass
	  public void afterClass() throws Exception {
		  Thread.sleep(3000);
		  driver.quit();
	  }
	  public boolean checkAnyImageLoaded(WebElement image) {
		  JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		  return (boolean) jsExecutor.executeScript("return arguments[0].complete &&" + "typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", image);
	  }

	}
