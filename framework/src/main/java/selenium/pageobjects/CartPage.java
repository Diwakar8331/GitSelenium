package selenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import selenium.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver); // we are sending driver from child to parent so we have to create constructor
						// in parent to catch
		// initialize at the beggning
		this.driver = driver;
		PageFactory.initElements(driver, this); // by using method initElements it will intialize the driver by
												// PageFactory

	}
	   @FindBy(css=".cartSection h3")
	   private List<WebElement> productTitle;
	   
	   @FindBy(xpath="//button[contains(text(),'Checkout')]")
	   WebElement checkout;
	   
	public Boolean productDisplay(String productName)
	{
		Boolean match=productTitle.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public ChecckOut checkOut()
	{
		checkout.click();
		return new ChecckOut(driver);
	}

}
