package selenium.framework;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

	public static void main(String[] args) throws InterruptedException, IOException {

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

		// Placeorder
		cheeckout.selectCountry(countryName);
		ConfirmationPage confirmPage = cheeckout.submitClick();

		String confirMessage = confirmPage.messageConfirm();
		Thread.sleep(1000);
		Assert.assertTrue(confirMessage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println("Passed");

		// Getting the order id
		WebElement order = driver.findElement(By.cssSelector("label[class*='ng-star-inserted']"));
		String orderid = order.getText().trim();
		String orderidd = orderid.replaceAll("[|]", "").trim();
		System.out.println(orderidd);

		// WebElement Capture
		WebElement element = driver.findElement(By.cssSelector(".title:first-child"));
		File srcFile = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("C:\\Users\\Diwakar.Kumar\\Downloads\\screen1.png"));
		System.out.println("WebElement capture");

		// WebPage Capture
		File srcFiile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFiile, new File("C:\\Users\\Diwakar.Kumar\\Downloads\\screen2.png"));
		System.out.println("WebPage capture");

		Thread.sleep(2000);

		// confirming order item is same in the checkout page
		WebElement oderconf = driver.findElement(By.cssSelector("div td:nth-child(2)"));
		String orderName = oderconf.findElement(By.xpath("//div[@class='title']")).getText();
		Assert.assertEquals(orderName, productName);
		System.out.println("Order item is same as the item selected : " + orderName + " Added product: " + productName);

		
		//opening a new tab using javascriptexecutor
		// Open a new tab using JavaScript
		((JavascriptExecutor) driver).executeScript("window.open('https://amazon.com', '_blank');");

		// Switch to the new tab
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		
		
	//WebPage Capture
		File srcFl=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFl, new File("C:\\Users\\Diwakar.Kumar\\Downloads\\screen56.png"));
		System.out.println("New Tab Web Page Captured");
		driver.quit();
	}

}
