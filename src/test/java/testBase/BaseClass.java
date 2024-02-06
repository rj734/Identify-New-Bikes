
package testBase;

import java.io.File;

import java.io.FileReader;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import java.util.Properties;

import org.apache.commons.io.FileUtils;

import org.apache.logging.log4j.LogManager;//log4j

import org.apache.logging.log4j.Logger; //log4j

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;

	public static Logger logger;

	public Properties p;

	@BeforeTest(groups = {"sanity", "regression"})

	@Parameters({ "os", "browser" })

	public void setup(String os, String br) throws IOException, InterruptedException

	{

		// loading properties file

		FileReader file = new FileReader(".//src//test//resources//config.properties");

		p = new Properties();

		p.load(file);

		// loading log4j file

		logger = LogManager.getLogger(this.getClass());
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	 	{	
		DesiredCapabilities capabilities=new DesiredCapabilities();
		
		
		//os
		if(os.equalsIgnoreCase("windows"))
		{
			capabilities.setPlatform(Platform.WIN11);
		}
		else if(os.equalsIgnoreCase("mac"))
		{
			capabilities.setPlatform(Platform.MAC);
		}
		else
		{
			System.out.println("No matching os..");
			return;
		}
		//browser
		switch(br.toLowerCase())
		{
		case "chrome" : capabilities.setBrowserName("chrome"); break;
		case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
		default: System.out.println("No matching browser.."); return;
		}
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
	    }
	else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	{
		//launching browser based on condition - locally
		switch(br.toLowerCase())
		{
		case "chrome": driver=new ChromeDriver(); break;
		case "edge": driver=new EdgeDriver(); break;
		default: System.out.println("No matching browser..");
					return;
		}
	}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.zigwheels.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}
	// close all the windows open in browser

	@AfterTest(groups = {"sanity", "regression"})

	public void tearDown() {

		driver.quit();

	}

	// Method to take screenshot

	public void captureScreen(String tname) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		File trg = new File("C:\\eclipse\\hackathonProject 1.zip_expanded\\hackathonproject\\screenShots\\" + tname + ".png");

		FileUtils.copyFile(src, trg);

	}

	// Method to take ExtentReport_screenshot

	public String captureScreen1(String tname) throws IOException {

		// String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\ExtentReport_screenshots\\" + tname + ".png";

		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

}
