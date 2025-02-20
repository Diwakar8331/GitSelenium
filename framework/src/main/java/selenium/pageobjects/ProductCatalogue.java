package selenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver); // every child has to give the super keyword to send data to the parent
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> dashboard = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
	List<WebElement> dashboard;

	@FindBy(css = ".ng-animating") // wherever driver.findElement use page factory
	WebElement spinner;
	
 
	By dashboardBy = By.cssSelector(".mb-3");   //where only  By.xpath/css/class use this 
	By addtoCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.xpath("//div[contains(@class, 'toast')]");
	

	
	public List<WebElement> getProductList() {
		waitForElementToAppear(dashboardBy);
		return dashboard;
	}

	public WebElement getProductByName(String productName) {
//		WebElement prod = null;
//		String product="ZARA COAT 3";
//		for (WebElement dashboards : dashboard) {
//			// System.out.println("Checking: " + dashboards.getText());
//			if (dashboards.findElement(By.cssSelector("b")).getText().trim().equalsIgnoreCase(product)) {
//				prod = dashboards;
//				break; // Exit loop after finding the first match
//			}
//		}
//		// in this we will find the element in the dashboards and it the text which we
//		// get in b tag then if it equals to QWERT then it will print or else it is
//		// passing null
//		if (prod != null) {
//			prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//		} else {
//			System.out.println("No product found with text: "+product);
//		}

		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) {
//		if (prod != null) {
//			prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//		} else {
//			System.out.println("No product found with text: "+productName);
//		}

		WebElement prod = getProductByName(productName);
		prod.findElement(addtoCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisaaper(spinner);

	}
}
