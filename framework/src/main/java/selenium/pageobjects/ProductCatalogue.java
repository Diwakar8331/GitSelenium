package selenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);  //every child has to give the super keyword to send data to the parent 
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> dashboard = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> dashboard;
	
	By dashboardBy=By.cssSelector(".mb-3");

	public List<WebElement> getProductList()
	{
		waitForElementToAppear(dashboardBy);
		return dashboard;
	}
	
}
