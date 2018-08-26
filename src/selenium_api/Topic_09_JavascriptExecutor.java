package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_09_JavascriptExecutor {
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
	  
	//  @Test
	  public void TC_01_JE() {
		  //  Step 01 - Truy cập vào trang: http://live.guru99.com/
		  openAnyUrlByJs("http://live.guru99.com/");
			//  Step 02 - Sử dụng JE để get domain của page
		  String homepageDomain = (String) executeJSForBrowserElement("return document.domain;");
			//  Verify domain =  live.guru99.com
		  Assert.assertEquals(homepageDomain, "live.guru99.com");
			//  Step 03 - Sử dụng JE để get URL của page
		  String homepageUrl = (String) executeJSForBrowserElement("return document.URL;");
			//  Verify URL =  http://live.guru99.com/
		  Assert.assertEquals(homepageUrl, "http://live.guru99.com/");
			//  Step 04 - Open MOBILE page (Sử dụng JE)
		  WebElement MobileElement = driver.findElement(By.xpath("//a[text()='Mobile']"));
		  executeForWebElementByJs(MobileElement);
			//  Step 05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button bằng JE)
		         //  Hướng dẫn: sử dụng following-sibling
		  WebElement SSaddtocart = driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div[@class='product-info']//button[@title='Add to Cart']"));
		  executeForWebElementByJs(SSaddtocart);
			//  Step 06 - Verify message được hiển thị:  Samsung Galaxy was added to your shopping cart. (Sử dụng JE - Get innertext of the entire webpage )
		  String innertext = (String) executeJSForBrowserElement("return document.documentElement.innerText;");
		  Assert.assertTrue(innertext.contains("Samsung Galaxy was added to your shopping cart."));
		  
			//  Step 07 - Open PRIVACY POLICY page (Sử dụng JE)
		  WebElement privacyPolicyElement = driver.findElement(By.xpath("//div[@class='footer']//a[text()='Privacy Policy']"));
		  executeForWebElementByJs(privacyPolicyElement);
			//  Verify title của page = Privacy Policy (Sử dụng JE)
			String titlePage = (String) executeJSForBrowserElement("return document.title;") ;
		   Assert.assertEquals(titlePage, "Privacy Policy");
			//  Step 08 - Srcoll xuống cuối page
		   scrollToBottomPage();
		//  Step 09 - Verify dữ liệu có hiển thị với chỉ 1 xpath:
		   String Checktext = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td")).getText();
	       Assert.assertEquals(Checktext , "The number of items in your Wishlist.")  ;  
	     //Step 10 - Navigate tới domain: http://demo.guru99.com/v4/  (Sử dụng JE)
	     //  Verify domain sau khi navigate = demo.guru99.com
	       openAnyUrlByJs("http://demo.guru99.com/v4/");
	       String guruDomain = (String) executeJSForBrowserElement("return document.domain;");
	        Assert.assertEquals(guruDomain, "demo.guru99.com");  
		     
	  }
	  @Test
	  public void TC_02_RemoveAttribute() {
		 // Step 01 - Truy cập vào trang: https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled
	driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		  //	  Step 02 - Remove thuộc tính disabled của field Last name
		//	  Switch qua iframe nếu có
	WebElement resultIframe = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
	driver.switchTo().frame(resultIframe);
	WebElement lastName = driver.findElement(By.xpath("//input[@name='lname']"));
	removeAttributeInDOM(lastName,"disabled");
	//	  Step 03 - Sendkey vào field Last name
	lastName.sendKeys("Automation Testing");
		//	  Step 04 - Click Submit button
	driver.findElement(By.xpath("//input[@value='Submit']")).click();
		//	  Step 05 - Verify dữ liệu sau khi submit chứa đoạn text đã fill trong field Lastname (Automation Testing)
    String resultLastname = driver.findElement(By.xpath("//h2[text()='Your input was received as:']/following-sibling::div[@class='w3-container w3-large w3-border']")).getText();
	Assert.assertTrue(resultLastname.contains("Automation Testing"));
	  
	  
	  
	  
	  }

	  
	  @AfterClass
	  public void afterClass() throws Exception {
		  Thread.sleep(3000);
		  driver.quit();
	  }
	// common function 
	  public Object openAnyUrlByJs(String url) {
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  return js.executeScript("window.location = '"+ url +"'");
	  }
	  	//  Highlight an element:
		  public Object highlightElement(WebElement element) {
		                          JavascriptExecutor js = (JavascriptExecutor) driver;
		                          return          js.executeScript("arguments[0].style.border='6px groove red'", element);
		              }
	  
		 // Execute for Browser
		  public Object executeJSForBrowserElement( String javaSript) {
			  JavascriptExecutor js = (JavascriptExecutor) driver;
              return js.executeScript(javaSript);                       
		  }
		  
		  //Execute for WebElement
		  public Object executeForWebElementByJs( WebElement element) {
			  JavascriptExecutor js = (JavascriptExecutor) driver;
              return js.executeScript("arguments[0].click();", element);
		   }
		  //Remove attribute in DOM
		  public Object removeAttributeInDOM(WebElement element, String attribute) {
			  JavascriptExecutor js = (JavascriptExecutor) driver;
              return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
          }
		  
		  //Scroll to bottom page
		  public Object scrollToBottomPage() {
			  JavascriptExecutor js = (JavascriptExecutor) driver;
              return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		  }  
              
          
              
              
              
              
              
              
              
              
              
              
  

		  
		  
		  
		  
		  
		  
		  
		  
	}
