package selenium.framework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import selenium.pageobjects.LandingPage;
import selenium.pageobjects.ProductCatalogue;

public class StandOrderTest {

	public static void main(String[] args) throws InterruptedException {

		// By this chromedriver will automaticaly set to the local:-
		// WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		LandingPage landingPage=new LandingPage(driver);
		landingPage.goTo();
		
		// implicitlyWait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// login
		landingPage.loginApp("dkoctober31@gmail.com", "Diwakar@123");
		
	
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		// grab all the items present in the dashboard
		List<WebElement> dashboard = driver.findElements(By.cssSelector(".mb-3"));
		if (dashboard.isEmpty()) {
			System.out.println("No elements found with class 'mb-3'.");
		}

		// using java streams to iterate every items
		Thread.sleep(2000);
		WebElement prod = null;
		String product="ZARA COAT 3";
		for (WebElement dashboards : dashboard) {
			// System.out.println("Checking: " + dashboards.getText());
			if (dashboards.findElement(By.cssSelector("b")).getText().trim().equalsIgnoreCase(product)) {
				prod = dashboards;
				break; // Exit loop after finding the first match
			}
		}
		// in this we will find the element in the dashboards and it the text which we
		// get in b tag then if it equals to QWERT then it will print or else it is
		// passing null
		if (prod != null) {
			prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		} else {
			System.out.println("No product found with text: "+product);
		}

		// confirmation popup after click on cart
		// using Explicit wait beacause popul is getting hidden after sometime so giving
		// cutom time so that driver will wait for 5 sec beacuse it failed the steps
		// we have to wait untile the animating icon disaaper beacuse after disappering
		// then only the add to cart popup is appearing
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'toast')]")));

		Thread.sleep(2000);
		// click on the cart button
		driver.findElement(By.xpath("//button[@class='btn btn-custom']//i[@class='fa fa-shopping-cart']")).click();


		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(product));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')] ")).click();
		
		//placeorder
		Actions a =new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Thread.sleep(1000);
		Assert.assertTrue(confirMessage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println("Passed");
		
	}

}
