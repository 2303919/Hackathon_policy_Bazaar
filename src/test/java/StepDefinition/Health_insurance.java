package StepDefinition;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import PageObject.Health_Insurance_Pom;
import Utilities.ExcelUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Health_insurance {

	WebDriver driver;
	Properties p;
	Logger logger;
	
	@Given("User should be on Home page")
	public void user_should_be_on_home_page() {
		p=Hooks.getProperties();
		driver=Hooks.getDriver();
		driver.get(p.getProperty("appUrl"));
	}

	@When("User hovers on Insurance products")
	public void user_hovers_on_insurance_products() {
		driver=Hooks.getDriver();
		Health_Insurance_Pom h_ins=new Health_Insurance_Pom(driver);
		ExcelUtility.healthDetails(h_ins.getHealthMenu());
	}

	@Then("Health insurance menu should be printed or displayed")
	public void health_insurance_menu_should_be_printed_or_displayed() {
	   logger=Hooks.getLogger();
	   logger.info("Successfully entered health insurance list");
	}
}
