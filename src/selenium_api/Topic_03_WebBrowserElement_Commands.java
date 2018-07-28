package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_03_WebBrowserElement_Commands {
	WebDriver driver;
	  String ElementEmail = "//input[@id='mail']";
	  String ElementAgeunder18 = "//input[@id='under_18']";
	  String ElementEducation = "//textarea[@id='edu']";
	  String ElementJob01 = "//select[@id='job1']";
	  String ElementInterDevelop = "//input[@id='development']";
	  String ElementSlider01 = "//input[@id='slider-1']";
	  String ElementButtonEn = "//button[@id='button-enabled']";
	  
	  String ElementPass = "//input[@id='password']";
	  String ElementAgeRadio = "//input[@id='radio-disabled']";
	  String ElementBiography = "//textarea[@id='bio']";
	  String ElementJob02 = "//select[@id='job2']";
	  String ElementIntercheckbox = "//input[@id='check-disbaled']";
	  String ElementSlider02 = "//input[@id='slider-2']";
	  String ElementButtonDis = "//button[@id='button-disabled']";
 @BeforeClass
 public void beforeClass() {
	 driver = new FirefoxDriver();
	// Chrome
	// System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
	// driver = new ChromeDriver();
	// IE
	//System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
	//driver = new InternetExplorerDriver();
	
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.manage().window().maximize();
	 driver.get("http://daominhdam.890m.com/");
 }
  @Test
  public void TC_01_CheckElementisDisplayed() {
	 //Kiểm tra các phần tử sau hiển thị trên trang: Email/ Age (Under 18)/ Education
	  if(isElementDisplayed(driver, ElementEmail) && isElementDisplayed(driver, ElementAgeunder18) && isElementDisplayed(driver, ElementEducation)){
		  //Automation Testing vào 2 field Email/ Education và chọn Age = Under 18
		  driver.findElement(By.xpath(ElementEmail)).sendKeys("Automation Testing");
		  driver.findElement(By.xpath(ElementEducation)).sendKeys("Automation Testing");
		  driver.findElement(By.xpath(ElementAgeunder18)).click();
		  }
  }
  @Test
  public void TC_02_CheckEnableDisable() {
	// Step 02 - Kiểm tra các phần tử sau enable trên trang: Email/ Age (Under 18)/ Education/ Job Role 01/ Interests (Development)/ Slider 01/ Button is enabled
	 // Step 03 - Kiểm tra các phần tử sau disable trên trang: Password / Age (Radiobutton is disabled)/ Biography/ Job Role 02/ Interests (Checkbox is disabled)/ Slider 02/ Button is disabled
	//  Step 04 - Nếu có in ra Element is enabled/ ngược lại Element is disabled:
	  //email
	  if (isElementEnabled(driver, ElementEmail)) {
		  System.out.println("Email List is enabled");
		  } else {
		  System.out.println("Email List is disabled ");
		  }
	  //under 18
	  if (isElementEnabled(driver, ElementAgeunder18)) {
		  System.out.println("ElementAgeunder18 List is enabled");
		  } else {
		  System.out.println("ElementAgeunder18 List is disabled ");
		  }
	  //education
	  if (isElementEnabled(driver, ElementEducation)) {
		  System.out.println("ElementEducation List is enabled");
		  } else {
		  System.out.println("ElementEducation List is disabled ");
		  }
	  //job1 ElementJob01
	  if (isElementEnabled(driver, ElementJob01)) {
		  System.out.println("ElementJob01 List is enabled");
		  } else {
		  System.out.println("ElementJob01 List is disabled ");
		  }
	  //interdevelop ElementInterDevelop
	  if (isElementEnabled(driver, ElementInterDevelop)) {
		  System.out.println("ElementInterDevelop List is enabled");
		  } else {
		  System.out.println("ElementInterDevelop List is disabled ");
		  }
	  //slider01 ElementSlider01
	  if (isElementEnabled(driver, ElementSlider01)) {
		  System.out.println("ElementSlider01 List is enabled");
		  } else {
		  System.out.println("ElementSlider01 List is disabled ");
		  }
	  //buttonEn ElementButtonEn
	  if (isElementEnabled(driver, ElementButtonEn)) {
		  System.out.println("ElementButtonEn List is enabled");
		  } else {
		  System.out.println("ElementButtonEn List is disabled ");
		  }
	  //Pass ElementPass
	  if (isElementEnabled(driver, ElementPass)) {
		  System.out.println("ElementPass List is enabled");
		  } else {
		  System.out.println("ElementPass List is disabled ");
		  }
	  //AgeRadio  ElementAgeRadio
	  if (isElementEnabled(driver, ElementAgeRadio)) {
		  System.out.println("ElementAgeRadio List is enabled");
		  } else {
		  System.out.println("ElementAgeRadio List is disabled ");
		  }
	  //bio  ElementBiography
	  if (isElementEnabled(driver, ElementBiography)) {
		  System.out.println("ElementBiography List is enabled");
		  } else {
		  System.out.println("ElementBiography List is disabled ");
		  }
	  //job02 ElementJob02
	  if (isElementEnabled(driver, ElementJob02)) {
		  System.out.println("ElementJob02 List is enabled");
		  } else {
		  System.out.println("ElementJob02 List is disabled ");
		  }
	  //intercheckbox ElementIntercheckbox
	  if (isElementEnabled(driver, ElementIntercheckbox)) {
		  System.out.println("ElementIntercheckbox List is enabled");
		  } else {
		  System.out.println("ElementIntercheckbox List is disabled ");
		  }
	  //slider02  ElementSlider02
	  if (isElementEnabled(driver, ElementSlider02)) {
		  System.out.println("ElementSlider02 List is enabled");
		  } else {
		  System.out.println("ElementSlider02 List is disabled ");
		  }
	  //buttondis ElementButtonDis
	  if (isElementEnabled(driver, ElementButtonDis)) {
		  System.out.println("ElementButtonDis List is enabled");
		  } else {
		  System.out.println("ElementButtonDis List is disabled ");
		  }
  }
  @Test
  public void TC_03_CheckElementSelected() {
	  //- Click chọn Age (Under 18)/ Interests (Development)
	  driver.findElement(By.xpath(ElementAgeunder18)).click();
	  driver.findElement(By.xpath(ElementInterDevelop)).click();
	  //Kiểm tra các phần tử tại Step 02 đã được chọn
	  if(!isElementSelected(driver, ElementAgeunder18)){
		  driver.findElement(By.xpath(ElementAgeunder18)).click();
		  } 
	  if(!isElementSelected(driver, ElementInterDevelop)){
		  driver.findElement(By.xpath(ElementInterDevelop)).click();
		  }
  }
  @Test
  public void TC_01_RunTCOnMultiBrowser() {
  }
  
  public boolean isElementSelected(WebDriver driver, String yourLocator) {
	  try {
	  WebElement locator;
	  locator = driver.findElement(By.xpath(yourLocator));
	  return locator.isSelected();
	  } catch (NoSuchElementException e) {
	  return false;
	  }
	  }
  
  public boolean isElementEnabled(WebDriver driver, String yourLocator) {
	  try {
	  WebElement locator;
	  locator = driver.findElement(By.xpath(yourLocator));
	  return locator.isEnabled();
	  } catch (NoSuchElementException e) {
	  return false;
	  }
	  }

  public boolean isElementDisplayed(WebDriver driver, String yourLocator) {
	  try {
	  WebElement locator;
	  locator = driver.findElement(By.xpath(yourLocator));
	  return locator.isDisplayed();
	  } catch (NoSuchElementException e) {
	  return false;
	  }
	  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
