package TestCase;

import org.testng.annotations.Test;

import PageObject.Health_Insurance_Pom;
import TestBase.BaseClass;
import Utilities.ExcelUtility;

public class TC03_Health_Insurance extends BaseClass{

	
	@Test(priority = 1,groups={"master"})
	public void print_health_Insurance() {
		driver.get(p.getProperty("appUrl"));
		Health_Insurance_Pom h_ins=new Health_Insurance_Pom(driver);
		ExcelUtility.healthDetails(h_ins.getHealthMenu());
		logger.info("Successfully entered health insurance list");
	}
}
