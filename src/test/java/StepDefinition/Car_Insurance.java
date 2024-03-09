package StepDefinition;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import PageObject.Car_Insurance_Pom;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Car_Insurance {

	WebDriver driver;
	Car_Insurance_Pom c_ins;
	Properties p;
	Logger logger=Hooks.getLogger();
	
	@Given("user should be on the car insurance page")
	public void user_should_be_on_the_car_insurance_page() {
		p=Hooks.getProperties();
	    driver=Hooks.getDriver();
	    driver.get(p.getProperty("appUrl"));
		c_ins=new Car_Insurance_Pom(driver);
		c_ins.click_car_insurance();
		logger.info("clicked on Car insurance menu");
	}

	@When("user enters all car details")
	public void user_enters_all_car_details() throws InterruptedException, IOException {
		driver=Hooks.getDriver();
		c_ins=new Car_Insurance_Pom(driver);
		c_ins.car_details();
		logger.info("Successfully Entered all car details");
	}

	@When("user provides invalid data for email")
	public void user_provides_invalid_data_for_email() throws IOException {
		driver=Hooks.getDriver();
		c_ins=new Car_Insurance_Pom(driver);
		c_ins.invalid();
	}

	@Then("error message should displayed")
	public void error_message_should_displayed() {
		driver=Hooks.getDriver();
		c_ins=new Car_Insurance_Pom(driver);
	    Assert.assertEquals(c_ins.errorMsg(),"Please enter valid email address");
	    logger.info("Error message is displayed after entering invalid data");
	}

	@When("user provides valid data and details")
	public void user_provides_valid_data_and_details() throws IOException, InterruptedException {
		driver=Hooks.getDriver();
		c_ins=new Car_Insurance_Pom(driver);
		c_ins.valid();
		logger.info("enters valid data");
		c_ins.carQuotes();
	}

	@Then("user should receive a car insurance quote")
	public void user_should_receive_a_car_insurance_quote() {
		logger.info("car Quotes are entered in Excel");
	}

}
