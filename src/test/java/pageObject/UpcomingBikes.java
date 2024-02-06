package pageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelUtility;

public class UpcomingBikes extends BasePage {

	public UpcomingBikes(WebDriver driver) {
		super(driver);
	}
	
	//locators
	
	@FindBy(xpath="//div[@class='col-lg-12 pl-0']/ul/li[3]/a")
	WebElement newBikes;
	
	@FindBy(xpath="//div[@class='col-lg-12 pl-0']/ul/li[3]/ul/li[5]/span")
	WebElement upcomingBikes;
	
	@FindBy(xpath="(//a[@class='fnt-14'])[3]")
	WebElement hondaBikes;
	
	@FindBy(xpath="//li[contains(@class,'modelItem')]")
	List<WebElement> bikePrice;
	
	@FindBy(xpath="//strong[@class='lnk-hvr block of-hid h-height']")
	List<WebElement> bikeModels;
	
	@FindBy(xpath="//div[@class='clr-try fnt-14']")
	List<WebElement> launchDates;
	
	@FindBy(xpath="//span[@class='zw-cmn-loadMore']")
	WebElement viewmore;
	
	//Actions
	
	public boolean clickOnUpcomingBikes() throws InterruptedException {
		
		Actions a = new Actions(driver);
		a.moveToElement(newBikes).perform();
		Thread.sleep(2000);
		Boolean bol = upcomingBikes.isEnabled();
	
		//click on upcomingBikes
		upcomingBikes.click();
		
		return bol;
	}
	
	public boolean selectBrand() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",hondaBikes);
		
		Boolean hb = hondaBikes.isEnabled();
		js.executeScript("arguments[0].click()", hondaBikes);
		return hb;
		
	}
	
    public boolean viewMoreBikes() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",viewmore);
		
		Boolean bol = viewmore.isEnabled();
		js.executeScript("arguments[0].click()", viewmore);
		return bol;
		
	}
    
	public String printBikeDetails() throws InterruptedException, IOException {
		
		ExcelUtility et = new ExcelUtility(System.getProperty("user.dir")+"\\TestData\\bikeDetails.xlsx");
		int k=1;
		String models = " ";
		
		for(int i=0;i<bikePrice.size();i++) {
			
		double prices =Double.parseDouble(bikePrice.get(i).getAttribute("data-price"));
		models=bikeModels.get(i).getText();
		String dates=launchDates.get(i).getText();
		
		if(prices < 400000) {
			
			System.out.println("Bike name: "+models);
			System.out.println("Price: "+prices);
			System.out.println(dates);
			
			System.out.println("--------------------");
			et.setCellData("HondaBike", k, 0, models);
			et.setCellData("HondaBike", k, 1, String.valueOf(prices));
			et.setCellData("HondaBike", k, 2, dates);
		 }
		}
		
		return models;
	}
}
