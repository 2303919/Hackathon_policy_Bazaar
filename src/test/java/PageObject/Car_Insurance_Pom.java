package PageObject;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import TestBase.BaseClass;
import Utilities.ExcelUtility;
import Utilities.ReadExcel;

public class Car_Insurance_Pom extends BasePage{

	public Car_Insurance_Pom(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[text()='Insurance Products ']")
	WebElement insuranceProducts;

	@FindBy(linkText="Car Insurance")
	WebElement car_insurance;
	
	@FindBy(xpath="//span[text()='car? Click here']") 
	WebElement newCar;
	
	@FindBy(xpath="//*[@id='pre-quote']/div[2]/div[3]/div[2]/div[2]/div[3]/div[1]")
	WebElement city;
	
	@FindBy(xpath="//label[@data-id='481']")
	WebElement rto;
	
	@FindBy(xpath="//label[@data-id='33']")
	WebElement brand;
	
	@FindBy(xpath="//label[@data-id='222']")
	WebElement model;
	
	@FindBy(xpath="//span[text()='Petrol']")
	WebElement fuelType;
	
	@FindBy(xpath="//span[text()='Delta AMT'][@class='text']")
	WebElement variant;
	
	@FindBy(xpath="//*[@id=\'txtName\']")
	WebElement name;
	
	@FindBy(xpath="//*[@id=\"txtEmail\"]")
	WebElement email;
	
	@FindBy(xpath = "//*[@id=\"mobNumber\"]")
	WebElement mobileNo;
	
	@FindBy(xpath = "//div[text()='Please enter valid email address']")
	WebElement errorMsg;
	
	@FindBy(xpath="//span[@class='icon']")
	WebElement view_prices;
	
	@FindBy(xpath="(//*[@class='radioBox bgWhite'])[2]")
	WebElement sevendays;
	
	@FindBy(xpath="//*[@name='exShowRoomPrice']")
	WebElement exshowroomprice;
	
	@FindBy(xpath="//*[text()='Submit']")
	WebElement submitbtn;
	
	@FindBy(xpath="//*[@class='primaryBtnV2 fillingEffect']")
	WebElement insurance;
	
	@FindBy(name="summaryFormPF")
	WebElement quote;
	
	public void click_car_insurance() {
		Actions act = new Actions(driver);
		act.moveToElement(insuranceProducts).perform();
		car_insurance.click();
	}
	public void car_details() throws InterruptedException, IOException{
		newCar.click();
		Thread.sleep(5000);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",city);
		Thread.sleep(2000);
		rto.click();
		Thread.sleep(2000);
		brand.click();
		model.click();
		fuelType.click();
		Thread.sleep(2000);
		variant.click();
		Thread.sleep(2000);
	}
	   //invalid data
		public void invalid() throws IOException {
			name.sendKeys(BaseClass.generateRandomName());
			String data2[]=new String[2];
			data2=ReadExcel.invalid_Data();
			email.sendKeys(data2[0]);
			mobileNo.sendKeys(data2[1]);
			view_prices.click();
			System.out.println(errorMsg.getText());
			clearAll();
		}
		
		//valid data
		public void valid() throws IOException {
		name.sendKeys(BaseClass.generateRandomName());
		String data1[]=new String[2];
		data1=ReadExcel.valid_Data();
		email.sendKeys(data1[0]);
		mobileNo.sendKeys(data1[1]);
		view_prices.click();
	}
	public void carQuotes() throws InterruptedException {
		sevendays.click();
		exshowroomprice.sendKeys("900000");
		submitbtn.click();
		Thread.sleep(5000);
		insurance.click();
//		System.out.println(quote.getText());
		ExcelUtility.carDetail(quote);
	}
	public String errorMsg() {
		return errorMsg.getText();
	}
	public void clearAll() {
		name.clear();
		email.clear();
		mobileNo.clear();
	}

}
