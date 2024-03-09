package PageObject;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class Health_Insurance_Pom extends BasePage{

	public Health_Insurance_Pom(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//a[text()='Insurance Products ']")
	WebElement insuranceProducts;
	
	@FindAll(@FindBy(xpath = "//div[@class='ruby-row']/div[3]//span"))
	List<WebElement> subItems; 
	
	public List<WebElement> getHealthMenu(){
		Actions act = new Actions(driver);
		act.moveToElement(insuranceProducts).perform();
		return subItems;
	}

}
