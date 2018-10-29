package Selenium_TestNG;

import org.testng.annotations.Test;

public class TC_03_Priority {
	@Test(groups="customer" )
	  public void TC_01_NewCustomer() {
		  System.out.println("TC 01");
	  }
	  
	  @Test(groups="payment")
	  public void TC_02_EditCustomer() {
		  System.out.println("TC 02");
	  }
	  
	  @Test(groups="manager", enabled = false)
	  public void TC_03_DeleCutomer() {
		  System.out.println("TC 03");
	  }
	  
	  @Test(groups="customer")
	  public void TC_04_ManageCustomer() {
		  System.out.println("TC 04");
	  }
	  
	  @Test(groups="payment")
	  public void TC_05_TransferCustomer() {
		  System.out.println("TC 05");
	  }
}

// run TC theo nam = anphabe : A-B-C ---> X-Y-Z 0-1-2---9
// qly 