package testCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObject.UpcomingBikes;
import testBase.BaseClass;

public class TC001_UpcomingBikes extends BaseClass {

	@Test(priority=1 , groups = {"sanity", "regression"})
	public void clickOnUpcomingBike() throws InterruptedException, IOException{
		logger.info("***** starting TC001_UpcomingBikes *****");
		logger.info("Clicking on Upcoming Bikes");
		UpcomingBikes bikes = new UpcomingBikes(driver);
		boolean upcomingBikes = bikes.clickOnUpcomingBikes();
		Assert.assertEquals(upcomingBikes, true);
		
//		take screenshot
//		captureScreen("//screenShots//UpcomingBikes");

	}
	
	@Test(priority=2, groups = {"regression"})
	public void selectHondaBrand() throws InterruptedException {
		logger.info("Clicking on honda brand");
		
		UpcomingBikes bikes = new UpcomingBikes(driver);
		boolean sb=bikes.selectBrand();
		Assert.assertEquals(sb, true);
		bikes.viewMoreBikes();
	}
	
	@Test(priority=3 , groups = {"regression"})
	public void viewMoreBikeBtn() throws InterruptedException {
		logger.info("Clicking on view more ");
		
		UpcomingBikes bikes = new UpcomingBikes(driver);
		boolean vm=bikes.viewMoreBikes();
		Assert.assertEquals(vm, true);
		
	}
	
	@Test(priority=4 , groups = {"regression"})
	public void bikeDetails() throws InterruptedException, IOException {
		logger.info("Printing bike details");
		UpcomingBikes bikes = new UpcomingBikes(driver);
		String lastBike=bikes.printBikeDetails();
		Assert.assertEquals(lastBike, "Honda Rebel 300");
	}
	
}
