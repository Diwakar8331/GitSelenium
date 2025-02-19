package selenium.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	//In this class we will hold all the reusibale stuffs
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
	}

	public void waitForElementToAppear(By findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
}
