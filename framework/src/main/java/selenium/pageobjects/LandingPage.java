package selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{

	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver); //we are sending driver from child to parent so we have to create constructor in parent to catch 
		//initialize at the beggning
		this.driver=driver;
		PageFactory.initElements(driver, this);  //by using method initElements it will intialize the driver by PageFactory
		
	}
	
	//WebElement email=driver.findElement(By.xpath("//input[@id='userEmail']"));
	//PageFactory
	
	//driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("dkoctober31@gmail.com");
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//driver.findElement(By.id("userPassword")).sendKeys("Diwakar@123");
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	//driver.findElement(By.xpath("//input[@value='Login']")).click();
	@FindBy(id="login")
	WebElement login;
	
	public void loginApp(String email,String passwword)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(passwword);
		login.click();
	}
	
	public void goTo()
	{
		//driver.get("https://rahulshettyacademy.com/client");
		driver.get("https://rahulshettyacademy.com/client");
	}
}

