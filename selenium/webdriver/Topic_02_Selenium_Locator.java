package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	//Check why it does not work with windows, instead of os/Mac
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			//check why can not use with geckodriver
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Open Register page
		driver.get("https://demo.nopcommerce.com/register");
	}
	
	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("FirstName")).sendKeys("Loan Hoang");;
	}
	
	@Test
	public void TC_02_Class() {
		driver.get("https://demo.nopcommerce.com/search");
		driver.findElement(By.className("search-text")).sendKeys("Nunu");
	}
	
	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("advs")).click();
	}
	
	@Test
	//See result in the console of Eclipe
	public void TC_04_TagName() {
		System.out.println(driver.findElements(By.tagName("input")).size());
	}
	
	@Test
	public void TC_05_LinkText() {
		driver.findElement(By.linkText("Addresses")).click();
	}
	
	@Test
	public void TC_06_PartialLinkText()	{
		driver.findElement(By.partialLinkText("vendor account")).click();
	}
	
	@Test
	public void TC_07_Css()	{
		driver.get("https://demo.nopcommerce.com/register");
		//1st option
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Loan");
		//2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Hoang");
		//3
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("hoangphuongloan1304@gmai.com");
	}
	
	@Test
	public void TC_08_XPath()	{
		driver.get("https://demo.nopcommerce.com/register");
		//1st option
		driver.findElement(By.xpath("//input[@data-val-required='First name is required.']")).sendKeys("Loan 2");
		//2nd option
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Hoang 2");
		//3rd option
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("loan@123.12");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
