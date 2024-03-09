package StepDefinition;

import org.openqa.selenium.WebDriver;

import PageObject.Travel_Insurance_Pom;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Travel_insurance {

	Travel_Insurance_Pom t_ins;
	WebDriver driver;
	Logger logger=Hooks.getLogger();
	
	@Given("User should load the website")
	public void user_should_load_the_website() throws InterruptedException {
		driver=Hooks.getDriver();
		t_ins=new Travel_Insurance_Pom(driver);
		t_ins.click_travel_insurance();
		 logger.info("-----Test Case for --------");
		 logger.info("Successfully clicked on Travel Insurance");
	}

	@When("user fills travel details")
	public void user_fills_travel_details() throws InterruptedException, IOException {
		driver=Hooks.getDriver();
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
	}

	@Then("Student plans should be displayed")
	public void student_plans_should_be_displayed() {
		logger.info("Successfully entered traveller insurance");
	}
}
