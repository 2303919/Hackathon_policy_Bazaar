package TestBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public static Logger logger;
	public static Properties p;

	@BeforeTest(groups = {"sanity","regression","master"})
	@Parameters({"browser" , "os"})
	
	public void setup(String browser, String os) {
		
		// initializing logger.
		logger = LogManager.getLogger();
		try {
			// loading config.properties file. 
			p = new Properties();
			FileReader propertiesFile = new FileReader(".\\src\\test\\resources\\config.properties");
			p.load(propertiesFile);
		} 
		catch (FileNotFoundException e) {
			logger.error("Properties file not found");		
		} 
		catch (IOException e) {
			logger.error("IO exception occured");
		}
		// for local machine execution.
		if(p.getProperty("env").equalsIgnoreCase("local")) {
			logger.info("--- Running on local system ---");
			switch (browser.toLowerCase()) {
				case "chrome":
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-notifications");
					options.addArguments("--disable-blink-features=AutomationControlled");
					driver = new ChromeDriver(options);
					break;
				case "edge":
					EdgeOptions edgeOptions = new EdgeOptions();
					edgeOptions.addArguments("--disable-notifications");
					edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
					driver = new EdgeDriver(edgeOptions);
					break;
				default:
					logger.error("No browser found");
					return;

			}

		}

		// for execution on selenium grid.

		else if(p.getProperty("env").equalsIgnoreCase("remote")) {
			logger.info("--- Running on selenium grid system ---");
			DesiredCapabilities capabilities = new DesiredCapabilities();	
			switch (os.toLowerCase()) {
			case "win11":
				capabilities.setPlatform(Platform.WIN11);
				break;
			case "win":
				capabilities.setPlatform(Platform.WINDOWS);
				break;
			case "mac":
				capabilities.setPlatform(Platform.MAC);
				break;
			default:
				logger.error("No matching os found with name " + os);
				return;
		}
			switch (browser.toLowerCase()) {
				case "chrome":
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-notifications");
					options.addArguments("--disable-blink-features=AutomationControlled");
					capabilities.setBrowserName("chrome");
					capabilities.setCapability(ChromeOptions.CAPABILITY, options);
					break;
				case "edge":
					EdgeOptions edgeOptions = new EdgeOptions();
					edgeOptions.addArguments("--disable-notifications");
					edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
					capabilities.setBrowserName("MicrosoftEdge");
					capabilities.setCapability(ChromeOptions.CAPABILITY, edgeOptions);
					break;
				default:
					logger.error("No browser found");
					return;
			}
			try {
				driver = new RemoteWebDriver(new URL(p.getProperty("hubUrl")), capabilities);
			} catch (MalformedURLException e) {
				logger.error("Hub url not correct");
				return;
			}
		}
		
		logger.info("Started " + browser + " browser");
		driver.get(p.getProperty("appUrl"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.manage().window().maximize();

	}

	public static void screenshot(String name) {
			TakesScreenshot ss = ((TakesScreenshot) driver);
			File src=ss.getScreenshotAs(OutputType.FILE);
			try {
				File trg=new File("./reports/Screenshot/"+name+".png");
				FileUtils.copyFile(src, trg);
			} catch (IOException e) {
			System.out.println(e.getMessage());
			}
		}
	 public static String generateRandomPhoneNumber() {
	        Random rand = new Random();
	        StringBuilder phoneNumber = new StringBuilder();
	        phoneNumber.append(rand.nextInt(4) + 6); 
	        for (int i = 1; i < 10; i++) {
	            phoneNumber.append(rand.nextInt(10)); }
	        return phoneNumber.toString();
	    }
	  
	 public static String generateRandomName()
	    {
	        List<String> names = Arrays.asList("Ravi", "Bob", "Charlie", "Dave","Nate","Angelin","Nikki","Tessa","Brooky","Raven");
	        Random rand = new Random();
	        String randomName = names.get(rand.nextInt(names.size()));
	        return randomName; 
	}
	  public String captureScreen(String tname) {
			 
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			String targetFilePath=System.getProperty("user.dir")+"\\ER_screenshots\\" + tname + "_" + timeStamp + ".png";
			File targetFile=new File(targetFilePath);

			try {

				FileUtils.copyFile(sourceFile, targetFile);

			} catch (IOException e) {

				e.printStackTrace();

			}

			return targetFilePath;
}
	  public static String random_name()
	    {
	        List<String> names = Arrays.asList("Ravi singh", "Bob neph", "Charlie man", "Dave nate","Nate amkaria","Angelin new","Nikki kin","Tessa brooky","Brooky raben","Raven maven");
	        Random rand = new Random();
	        String randomName = names.get(rand.nextInt(names.size()));
	        return randomName; 
	    }
	  
	  
	  
	  @AfterTest
	  public void closeBrowser() {
		  driver.quit();
	  }
}
