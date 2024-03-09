package PageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import TestBase.BaseClass;
import Utilities.ExcelUtility;

public class Travel_Insurance_Pom extends BasePage{

	public Travel_Insurance_Pom(WebDriver driver) {
		super(driver);
	}
	
	//p[contains(text(),'Travel')]
	@FindBy(xpath = "//*[@class='prd-row']/div[7]")
	WebElement travelInsurance;
	
	@FindBy(xpath="//p[text()='Germany']")
	WebElement country;
	
	@FindBy(xpath="//button[text()='Next']")
	WebElement next;
	
	@FindBy(xpath="(//input[@class='MuiInputBase-input MuiOutlinedInput-input'])[1]")
	WebElement calendar;
	
	@FindBy(xpath="(//span[text()='1'])[2]")
	WebElement startDate;
	
	@FindBy(xpath="(//span[text()='14'])[2]")
	WebElement endDate;
	
	@FindBy(xpath= "//input[@id='traveller_2']")
	WebElement twoperson;
	
	@FindBy(xpath = "(//div[@id='divarrow_undefined'])[1]")
	WebElement selectAge1;
	
	@FindBy(xpath = "//input[@id='22 years_undefined']")
	WebElement clickAge1;
	
	@FindBy(xpath = "(//div[@id='divarrow_undefined'])[2]")
	WebElement selectAge2;
	
	@FindBy(xpath = "//input[@id='21 years_undefined']")
	WebElement clickAge2;
	
	@FindBy(xpath = "//input[@id='ped_no']")
	WebElement illness;
	
	@FindBy(xpath="//button[@class='travel_main_cta']")
	WebElement medical_proceed;
	
	@FindBy(xpath ="//*[@id=\"mobileNumber\"]")
	WebElement mobileNo;
	
	@FindBy(xpath = "//button[@class='travel_main_cta']")
	WebElement viewPlans;
	
	@FindBy(xpath = "//input[@id='studentTrip']")
	WebElement studentPlan;
	
	@FindBy(xpath = "//input[@id='Traveller_1']")
	WebElement traveller1;
	
	@FindBy(xpath = "//input[@id='Traveller_2']")
	WebElement traveller2;
	
	@FindBy(xpath = "//select[@id='feet']")
	WebElement tripDuration;
	
	@FindBy(xpath = "//button[text()='Apply']")
	WebElement apply;
	
	@FindBy(xpath = "//p[text()='Sort by']")
	WebElement sortBy;
	
	@FindBy(xpath = "//label[text()='Premium low to high']")
	WebElement premium;
	
	@FindAll(@FindBy(xpath = "//article[@class=\"quotesCardWrapper \"]//p[@class=\"quotesCard--insurerName\"]"))
	List<WebElement> plans;
	
	@FindBy(xpath = "(//div[@class='cardWrapper__showMore '])[1]")
	WebElement morePlans;
	
	@FindAll(@FindBy(xpath = "//article[@class=\"quotesCardWrapper \"]//span[@class=\"premiumPlanPrice\"]"))
	List<WebElement>prices;
	
	public void click_travel_insurance() throws InterruptedException {
		travelInsurance.click();
		Thread.sleep(3000);
	}
	
	public void clickCountry() throws InterruptedException {
		Thread.sleep(2000);
		if(!country.isSelected()) {
		country.click();
		}
		Thread.sleep(2000);
		next.click();
	}
	
	public void clickCalendar() throws InterruptedException {
		Thread.sleep(2000);
		calendar.click();
		Thread.sleep(2000);
		startDate.click();
		endDate.click();
		Thread.sleep(2000);
		next.click();
	}
	
	public void noOfPeople() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", twoperson);
		js.executeScript("arguments[0].click();", selectAge1);
		js.executeScript("arguments[0].click();",clickAge1);
		js.executeScript("arguments[0].click();", selectAge2);
		js.executeScript("arguments[0].click();",clickAge2);
		next.click();
	}
	
	public void healthCondition() throws InterruptedException {
		illness.click();
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",medical_proceed);
	}
	
	public void mobileNo() throws InterruptedException {
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();",mobileNo);
		//js.executeScript("arguments[0].value='" +BaseClass.generateRandomPhoneNumber()+"';",mobileNo);
		mobileNo.sendKeys(BaseClass.generateRandomPhoneNumber());
//		Thread.sleep(3000);
		js.executeScript("arguments[0].click();",viewPlans);
		//viewPlans.click();
	}
	
	public void showPlans() throws InterruptedException {
		Thread.sleep(3000);
		studentPlan.click();
		Thread.sleep(3000);
		traveller1.click();
		Thread.sleep(3000);
		traveller2.click();
		Thread.sleep(3000);
		tripDuration.click();
	}
	
	public void dropDown() throws InterruptedException, IOException {
		
		Select select = new Select(tripDuration);
		select.selectByVisibleText("30 Days");
		apply.click();
		Thread.sleep(3000);
		sortBy.click();
		premium.click(); 
		morePlans.click();
		ExcelUtility.travelDetails(plans, prices);
	}
}
