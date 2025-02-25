package selenium.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	// In this class we will hold all the reusibale stuffs

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//driver.findElement(By.xpath("//button[@class='btn btn-custom']//i[@class='fa fa-shopping-cart']")).click();
	@FindBy(xpath="//button[@class='btn btn-custom']//i[@class='fa fa-shopping-cart']")
	WebElement cartHeader;
	
	
	
	public void waitForElementToAppear(By findBy) // here we use By findBy beacuse in until(expected.......) there is
													// By.css so we use By Findby
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForElementToDisaaper(WebElement element) // here we user Webelement instead of findBy because ther
																// is driver.findElement
	{
		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void goToCart()
	{
		cartHeader.click();
	}
	
}
