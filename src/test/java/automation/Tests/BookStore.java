package automation.Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import automation.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BookStore {


	
	@Test
	public void addBookToCart() throws InterruptedException
	{
		//Webdriver initialization and window handling
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		LandingPage landingPage = new LandingPage(driver);
		
		//1. Open URL
		landingPage.goTo();
		
		//2. Clicks the books drop down menu
		driver.findElement(By.xpath("//a[normalize-space()='Book']")).click();
		

		//3. In the list appearing move the mouse pointer over fiction for the subcategories to appear
		WebElement fictionElement = driver.findElement(By.xpath("//ul[@class='list-unstyled maincategory dropdown']//a[@onclick='getSearchResult()'][normalize-space()='Fiction']"));  
		
        Actions actions = new Actions(driver);
        
        actions.moveToElement(fictionElement).perform();

        Thread.sleep(2000);  

        //4.Click on the subcategory historical fiction
        WebElement historicalFictionElement = driver.findElement(By.xpath("//ul[@class='list-unstyled booksubcategory']//a[normalize-space()='Historical fiction']"));  // Replace with the actual XPath

        historicalFictionElement.click();
        
        
        Thread.sleep(3000);
        
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
        
        //6. Click on cart and verify whether the cart count updated to 1
        driver.findElement(By.xpath("//div[@class='search-results fltrs demo3']//div[1]//div[4]//div[5]//input[1]")).click();
        
        Thread.sleep(2000);
        WebElement cartCount = driver.findElement(By.id("ctl00_lblTotalCartItems"));

        String cartCountText = cartCount.getText();
        System.out.println(cartCountText);
        if (cartCountText.equals("1")) {
            System.out.println("Verification Passed: The cart count is updated to 1.");
        } else {
            System.out.println("Verification Failed: The cart count is not updated to 1.");
        }
        
        driver.findElement(By.xpath("//ul[@class='list-inline m-0 signreflink position-relative']//li[@class='list-inline-item position-relative']//a")).click();
        
        Thread.sleep(5000);
        
        
        //7. Navgate to the cart and verify whether the book has been added and the title and price is same which is fetch from the previous page



        Thread.sleep(2000);  

        // Locate the book name and price in the cart
        WebElement cartBookName = driver.findElement(By.xpath("//label[@id='ctl00_phBody_BookCart_lvCart_ctrl0_lblProductTitle']"));
        WebElement cartBookPrice = driver.findElement(By.xpath("//label[@id='ctl00_phBody_BookCart_lvCart_ctrl0_lblActualPrice']"));  

        // Verify the book name
        if (cartBookName.getText().equals(bookName)) {
            System.out.println("Verification Passed: The book name in the cart matches the expected name.");
        } else {
            System.out.println("Verification Failed: The book name in the cart does not match the expected name.");
        }

        // Verify the book price
        if (cartBookPrice.getText().equals(bookPrice)) {
            System.out.println("Verification Passed: The book price in the cart matches the expected price.");
        } else {
            System.out.println("Verification Failed: The book price in the cart does not match the expected price.");
        }
        
        driver.close();

	}

}
