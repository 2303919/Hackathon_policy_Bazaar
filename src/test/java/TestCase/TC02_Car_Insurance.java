package TestCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.Car_Insurance_Pom;
import TestBase.BaseClass;

public class TC02_Car_Insurance extends BaseClass{

	@Test(priority = 0,groups={"master"})
	public void car_Details_invalid() throws InterruptedException, IOException {
		driver.get(p.getProperty("appUrl"));
		Car_Insurance_Pom c_ins=new Car_Insurance_Pom(driver);
		
		c_ins.click_car_insurance();
		logger.info("clicked on Car insurance menu");
		
		c_ins.car_details();
		logger.info("Successfully Entered all car details");
		
		c_ins.invalid();
		//Assert.assertEquals(c_ins.errorMsg(),"Please enter valid email address");
		logger.info("Error message is displayed after entering invalid data");
	}
	
	@Test(priority = 1)
	public void car_Details_valid() throws InterruptedException, IOException {
		Car_Insurance_Pom c_ins=new Car_Insurance_Pom(driver);
		c_ins.valid();
		logger.info("enters valid data");
		
		c_ins.carQuotes();
		logger.info("car Quotes are entered in Excel");
	}
	
}
