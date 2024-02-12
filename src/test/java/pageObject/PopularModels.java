package pageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelUtility;

public class PopularModels extends BasePage {

	public PopularModels(WebDriver driver) {
		super(driver);
	}
	
	
	//locators
	
	@FindBy(xpath="//a[@class='zw i-b mt-10 pt-2 zw-srch-logo']/img")
	WebElement zigWheels;
	
	@FindBy(xpath="//div[@class='col-lg-12 pl-0']/ul/li[7]/a")
	WebElement usedCars;
	
	@FindBy(xpath="//div[@class='h-dd-r']/ul/li[4]/span")
	WebElement chennai;
	
    @FindBy(xpath="//ul[contains(@class,'ml-20 mt-10')]/li")
    List<WebElement> models;
    
    public boolean mouseHoverUsedCars() throws IOException {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0)");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
    	Actions a = new Actions(driver);
		a.moveToElement(usedCars).perform();
		Boolean ch = chennai.isEnabled();
		
		chennai.click();
		captureScreen("//screenShots//chennaiUsedCars");

		return ch;
    }
    
    public String displayPopularModels() throws InterruptedException, IOException {
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		
		Thread.sleep(2000);
		captureScreen("//screenShots//displayPopularModels");

		ExcelUtility et = new ExcelUtility(System.getProperty("user.dir")+"\\TestData\\bikeDetails.xlsx");
		
		 System.out.println("List of popular models");
    	for(int i=0; i<models.size(); i++) {
    		
    		System.out.println(models.get(i).getText());
    		et.setCellData("ChennaiUsedCars", i+1, 0, models.get(i).getText());
    	}
    	String model=models.get(8).getText();

    	return model;
    }
    
    public boolean clickOnLogo() throws IOException {
    	
		Boolean bol = zigWheels.isEnabled();
    	zigWheels.click();
		captureScreen("//screenShots//NavigateToHomePage");

    	return bol;
    }
    
}
