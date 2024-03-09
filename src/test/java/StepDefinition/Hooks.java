package StepDefinition;



	import java.io.FileReader;
	import java.io.IOException;
	import java.time.Duration;
	import java.util.Properties;
	 
	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	 
	import io.cucumber.java.After;
	import io.cucumber.java.AfterAll;
	import io.cucumber.java.AfterStep;
	import io.cucumber.java.Before;
	import io.cucumber.java.BeforeAll;
	import io.cucumber.java.Scenario;
	 
	 
	public class Hooks {
		public static WebDriver driver;
			public static Logger logger;
			public static Properties p;

			@BeforeAll
			public static void setup() throws IOException {

				FileReader file = new FileReader(".//src/test/resources/config.properties");
				p = new Properties();
				p.load(file);
				String browser=p.getProperty("browser");
				//loading log4j2 file
				 logger = LogManager.getLogger();	
				//creating driver
			    if(browser.equalsIgnoreCase("chrome")) {
					driver = new ChromeDriver();
					logger.info(" Testing in chrome___");
				}
				else if(browser.equalsIgnoreCase("edge")) {
					driver = new EdgeDriver();
					logger.info(" Testing in edge___");
				}
				else if(browser.equalsIgnoreCase("firefox")) {
					driver = new FirefoxDriver();
				}
			    driver.get(p.getProperty("appUrl"));
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				driver.manage().deleteAllCookies();
			}
			@AfterAll
			public static void tearDown() {
				driver.quit();
			}
	 
	@AfterStep
	public void addScreenshot(Scenario scenario) {
		TakesScreenshot ts=(TakesScreenshot) driver;
		byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png",scenario.getName());
	}
	 
	 
	public static WebDriver  getDriver() {
		return driver;
	}
	 
	public static Properties getProperties() {
		return p;
	}
	 
	public static Logger getLogger() {
		return logger;
	}
	 
	}

