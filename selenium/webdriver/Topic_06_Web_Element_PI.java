package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_PI {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_() {
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		WebElement emailTextbox = driver.findElement(By.id("Email"));
		emailTextbox.isDisplayed();
		emailTextbox.clear();
		emailTextbox.sendKeys("");
		WebElement passwordTextbox = driver.findElement(By.id("Password"));
		
	}
	
	@Test
	public void TC_02_() {
		WebElement element = driver.findElement(By.className(""));
		//Use for textbox/textarea/dropdown(Editable)
		element.clear();
		element.sendKeys("");
		element.click();
		//find element 2 times
		driver.findElement(By.cssSelector("div.header-links a.ico-login"));
		element.findElement(By.cssSelector("div.header-links"))
		.findElement(By.cssSelector("a.ico-login"));
		String searchAttribute = element.getAttribute("placeholder");
		
		element.getCssValue("background-color");
		element.getLocation();
		element.getSize();
		element.getRect();
		element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.FILE);
		//Take tag name 
		element.getTagName();
		driver.findElement(By.id("Email")).getTagName();
		driver.findElement(By.name("Email")).getTagName();
		String emailTextboxTagname = driver.findElement(By.cssSelector("#Email")).getTagName();
		driver.findElement(By.xpath("//" + emailTextboxTagname + "[@id='email']"));
		
		//get text from error message/success message/label/header/...
		element.getText();
		//when the value is outside-> get text
		//when the value is inside -> get Attribute
		//check if the element is displayed. Apply for all elements
		element.isDisplayed();
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		//check if element is enable. Apply for all elements
		element.isEnabled();
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		//check if element is selected
		//Apply for checkbox/Radio
		element.isSelected();
		Assert.assertTrue(element.isSelected());
		Assert.assertFalse(element.isSelected());
		//Element is in form tag
		//It is like End user with pressing Enter
		element.submit();
	}
	
	@Test
	public void TC_03_() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
