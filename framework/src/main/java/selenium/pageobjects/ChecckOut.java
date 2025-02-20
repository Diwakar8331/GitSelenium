package selenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import selenium.AbstractComponents.AbstractComponents;

public class ChecckOut extends AbstractComponents {

	WebDriver driver;

	public ChecckOut(WebDriver driver) {
		super(driver); // we are sending driver from child to parent so we have to create constructor
						// in parent to catch
		// initialize at the beggning
		this.driver = driver;
		PageFactory.initElements(driver, this); // by using method initElements it will intialize the driver by
												// PageFactory

	}
	   
	   @FindBy(xpath="//input[@placeholder='Select Country']")
	   WebElement country;
	   
	   @FindBy(css=".action__submit")
	   WebElement submit;
	   
	   @FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	   WebElement select;
	   
	   By cart=By.cssSelector(".ta-results");
	   
	public void selectCountry(String countryName) 
	{
		Actions a =new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(cart);
		select.click();
	}
	
	public ConfirmationPage submitClick()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}

}
