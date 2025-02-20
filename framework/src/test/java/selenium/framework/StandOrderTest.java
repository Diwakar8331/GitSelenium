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

import selenium.pageobjects.CartPage;
import selenium.pageobjects.ChecckOut;
import selenium.pageobjects.ConfirmationPage;
import selenium.pageobjects.LandingPage;
import selenium.pageobjects.ProductCatalogue;

public class StandOrderTest {

	public static void main(String[] args) throws InterruptedException {

		// By this chromedriver will automaticaly set to the local:-
		// WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();

		// implicitlyWait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// login
		landingPage.loginApp("dkoctober31@gmail.com", "Diwakar@123");

		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> dashboard = productCatalogue.getProductList();
		String productName = "ZARA COAT 3";
		String countryName = "india";
		productCatalogue.addProductToCart(productName);

		Thread.sleep(2000);
		productCatalogue.goToCart();

		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.productDisplay(productName);
		// assert will not go to page factory because in page factory only code will go
		// validation will start here
		Assert.assertTrue(match);
		ChecckOut cheeckout = cartPage.checkOut();

		// placeorder
		cheeckout.selectCountry(countryName);
		ConfirmationPage confirmPage = cheeckout.submitClick();

		String confirMessage = confirmPage.messageConfirm();
		Thread.sleep(1000);
		Assert.assertTrue(confirMessage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println("Passed");

	}

}
