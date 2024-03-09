package TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.Travel_Insurance_Pom;
import TestBase.BaseClass;

public class TC01_travel_Insurance extends BaseClass{

	Travel_Insurance_Pom t_ins;
	
	
	@Test(priority=0,groups={"master"})
	public void travel_insurance() throws InterruptedException { 
		t_ins=new Travel_Insurance_Pom(driver);
		t_ins.click_travel_insurance();
		String title_travel=driver.getTitle();
		Assert.assertEquals(title_travel,"PolicyBazaar Travel Insurance");
		 logger.info("-----Test Case for --------");
		 logger.info("Successfully clicked on Travel Insurance");
	}
	
	@Test(priority=1,groups={"master"})
	public void travel_details() throws Exception {
		t_ins=new Travel_Insurance_Pom(driver);
		
		t_ins.clickCountry();
		logger.info("Successfully selected country");
		
		t_ins.clickCalendar();
		 logger.info("Successfully selected travel date");
		 
		t_ins.noOfPeople();
		logger.info("Successfully selected number of persons and their age");
		
		t_ins.healthCondition();
		 logger.info("Successfully selected their medical health");
		 
		t_ins.mobileNo();
		logger.info("Successfully Entered mobile number");
		
		t_ins.showPlans();
		t_ins.dropDown();
		logger.info("Successfully entered traveller insurance");
		
	}
}
