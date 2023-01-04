package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_PII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextBox = By.cssSelector("#disable_password");
	By biographyTextArea = By.cssSelector("#bio");
	By developmentCheckBox = By.cssSelector("#development");
	
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
	public void TC_01_display() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//textbox
		if(driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Loan Hoang's email");
			System.out.println("Email textbox is displayed");
		}else {
			System.out.println("Email textbox is not displayed");
		}
		//textarea
		if (driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Loan Hoang's education");
			System.out.println("Education text area is displayed");			
		} else {
			System.out.println("Education text area is not displayed");
		}
		//radio button
		if (driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Age under 18 is displayed");			
		} else {
			System.out.println("Age under 18 is not displayed");
		}
		//name
		if (driver.findElement(nameUser5Text).isDisplayed()) {
			System.out.println("name User 5 is displayed");			
		} else {
			System.out.println("name User 5 is not displayed");
		}
		
	}
	
	@Test
	public void TC_02_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(passwordTextBox).isEnabled()) {
			System.out.println("Password textbox is ebabled");
		} else {
			System.out.println("Password textbox is disabled");
		}
		if (driver.findElement(biographyTextArea).isEnabled()) {
			System.out.println("Biography TextArea is ebabled");
		} else {
			System.out.println("Biography TextArea is disabled");
		}		
		if (driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("Email Textbox textbox is ebabled");
		} else {
			System.out.println("Email Textbox is disabled");
		}
	}
	
	@Test
	public void TC_03_selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertFalse(driver.findElement(developmentCheckBox).isSelected());
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(developmentCheckBox).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		Assert.assertTrue(driver.findElement(developmentCheckBox).isSelected());
	}
	
	@Test
	public void TC_04_() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("loanhoang2022");
		By passwordTextBox =  By.id("new_password");
		By signupButton = By.id("create-account-enabled");
		
		driver.findElement(passwordTextBox).sendKeys("abc");
//		driver.findElement(signupButton).click();
		sleepInSecond(3);
		//verify lower case
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("ABC");
//		driver.findElement(signupButton).click();
		sleepInSecond(3);
		//verify upper case
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("123");
//		driver.findElement(signupButton).click();
		sleepInSecond(3);
		//verify number
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("!@#");
//		driver.findElement(signupButton).click();
		sleepInSecond(3);
		//verify special characters
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("ABCDEFGH");
//		driver.findElement(signupButton).click();
		sleepInSecond(3);
		//verify char >=8
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys("123abcABC!@#");
//		driver.findElement(signupButton).click();
		sleepInSecond(3);
		//verify char >=8
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
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
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}