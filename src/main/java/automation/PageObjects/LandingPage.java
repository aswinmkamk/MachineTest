package automation.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Created for all login page Webelements and action classes
public class LandingPage {
	
	WebDriver driver;
	
	//Created  constructor to give life to driver
	public LandingPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void goTo()
	{
		driver.get("https://www.bookswagon.com/");
	}
	
	//
	


}
