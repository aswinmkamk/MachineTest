package automation.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.AbstractComponents.AbstractComponent;

public class NewPage extends AbstractComponent{
	
	WebDriver driver;
	
	//Created  constructor to give life to driver
	public NewPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Locators using page factory method
	@FindBy(xpath="//input[@placeholder='Email id']")
	WebElement useremail;
	
	
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement userPassword;
	
	@FindBy(xpath="//button[normalize-space()='Sign in']")
	WebElement loginButton;
	
	
	public void loginApplication(String email, String password)
	{
//		
	}
	

	


}
