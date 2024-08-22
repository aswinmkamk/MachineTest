package automation.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import automation.AbstractComponents.AbstractComponent;

public class BookSelectionPage extends AbstractComponent{
	
	WebDriver driver;
	
	//Created  constructor to give life to driver
	public BookSelectionPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//a[normalize-space()='Book']")
	WebElement bookName;
	
	public void fictionalBookSelection()
	{
		//2. Clicks the books drop down menu
		bookName.click();
		

		//3. In the list appearing move the mouse pointer over fiction for the subcategories to appear
		WebElement fictionElement = driver.findElement(By.xpath("//ul[@class='list-unstyled maincategory dropdown']//a[@onclick='getSearchResult()'][normalize-space()='Fiction']"));  
		
        Actions actions = new Actions(driver);
        
        actions.moveToElement(fictionElement).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='list-unstyled booksubcategory']//a[normalize-space()='Historical fiction']")));

        //4.Click on the subcategory historical fiction
        WebElement historicalFictionElement = driver.findElement(By.xpath("//ul[@class='list-unstyled booksubcategory']//a[normalize-space()='Historical fiction']"));  // Replace with the actual XPath

        historicalFictionElement.click();
        
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='ddlSort']")));
	
	}
	
	public void bookSorting()
	{
        //5. On the result appearing sort the result using option A-Z
        
        driver.findElement(By.xpath("//select[@id='ddlSort']")).click();
        
        WebElement sortDropdown = driver.findElement(By.xpath("//select[@id='ddlSort']")); 

        Select selectSort = new Select(sortDropdown);
        selectSort.selectByVisibleText("Title - A to Z");
		
        WebElement firstBook = driver.findElement(By.xpath("//a[normalize-space()='1918']"));
        String bookName = firstBook.getText();
        System.out.println(bookName);
        
        WebElement priceElement = driver.findElement(By.xpath("//div[@class='search-results fltrs demo3']//div[1]//div[3]//div[5]//div[1]//div[1]")); 
        String bookPrice = priceElement.getText();
        System.out.println(bookPrice);
        

        // Verify if the first book appearing is "1918"
        if (firstBook.isDisplayed()) {
            System.out.println("Verification Passed: The first book is titled '1918'.");
        } else {
            System.out.println("Verification Failed: The first book is not titled '1918'.");
        }
	}
	

	


}
