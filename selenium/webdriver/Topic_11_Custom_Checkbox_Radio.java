package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Checkbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Default_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		sleepInSecond(2);
		//Select
		driver.findElement(By.cssSelector("input[value='Spring']")).click();
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input']")).isSelected());
	}
	
	
	
	@Test
	//Why the input is hiden?
	public void TC_02_Custome_Radio() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		//Select
//		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div']")).click();
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		//Verify
		Assert.assertTrue(driver.findElement(By.cssSelector("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
	}
	
	@Test
	public void TC_03_Javascript(){
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		//Select
//		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div']")).click();
//		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
//		sleepInSecond(3);
		By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		sleepInSecond(3);
		//Verify
		Assert.assertTrue(driver.findElement(radioButton).isSelected());
		
	}
	
	@Test
	public void TC_04_Javascript(){
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(5);
		//Select
//		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div']")).click();
//		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
//		sleepInSecond(3);
		By radioButton = By.cssSelector("div[aria-label='Hà Nội']");
		By checkbox = By.cssSelector("div[aria-label='Quảng Ngãi']");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkbox));
		sleepInSecond(2);
		//Verify
		//way 1
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Hà Nội'][aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Ngãi'][aria-checked='true']")).isDisplayed());
		//way 2
		Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(checkbox).getAttribute("aria-checked"), "true");
		
		
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//1000ms = 1s
	}
}
