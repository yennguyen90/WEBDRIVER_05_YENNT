package JavaBasic;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class JavaString {
	WebDriver driver;
	
	//  @BeforeClass
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
	  
	  @Test
	  public void TC_01() {
		  XuLyChuoi();
	  }
	 
	 // @AfterClass
	  public void afterClass() throws Exception {
		  Thread.sleep(3000);
		  driver.quit();
	  }
	  public void XuLyChuoi() {
		  String automation1 = "1234 aaa H chi Minh 5678 Nguyễn Ái Quốc 910";
		  // đếm số lượng kí tự là a
		  int a =0;
		  String[] subString = automation1.split("");
		  for( int i=0; i< subString.length; i++) {
			  if(subString[i].equals("a")) {
				  a++;
			  }
		  }
		  System.out.println("Số ký tự a ="+a);
		  //kiểm tra chuỗi có chứa từ Minh không
		  String automation2 = "1234 Ho chi Minh 5678 Nguyễn Ái Quốc 910";
		  Boolean checkingContentString = automation2.contains("Minh");
		  System.out.println(checkingContentString);
		  
		  //Kiểm tra chuỗi có bắt đầu bằng Ho không
		  String automation3 = "1234 Ho chi Minh 5678 Nguyễn Ái Quốc 910";
		  Boolean compareStart = automation3.startsWith("Ho");
		  System.out.println(compareStart);
		  
		  //Kiểm tra chuỗi có kết thúc bằng 910 không
		  Boolean compareEnd = automation3.endsWith("910");
		  System.out.println(compareEnd);
		  
		  //lấy vị trí của từ "chi" trong chuỗi
		  String automation6 = "1234 Ho chi Minh 5678 Nguyễn Ái Quốc 910";
		  int index = automation6.indexOf("chi");
		  System.out.println("Vi tri chuoi = " + index);
		  
		  //thay thế từ 910 bằng !!!
		  automation6 = automation6.replace("910", "!!!");
		  System.out.println(automation6);
		  
		  //đếm số lượng kí tự là số có trong chuỗi
		  int number =0;
		  String[] ArrayNumber = {"0","1","2","3","4","5","6","8","9","7"};
		  String[] subString1 = automation6.split("");
		  for(int so=0; so<10; so++) {
			  for( int i=0; i< subString1.length; i++) {
				  if(subString1[i].equals(ArrayNumber[so])) {
					  number++;
				  }
			  }
		  }
		 
		  System.out.println("Số ký tự is number ="+number);
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
	  }

	}
