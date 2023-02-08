package webdriver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	WebDriverWait expliciWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String authenFirefox = projectPath + "\\autoIT\\authen_firefox.exe";
	String authenChrome = projectPath + "autoIT\\authen_chrome.exe";
	String username = "admin";
	String password = "admin";
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
//		driver = new ChromeDriver();
		driver = new FirefoxDriver();
//		System.out.println(driver.toString());
		expliciWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
//	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		//way 1
//		alert = driver.switchTo().alert();
		//way 2: should use
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	}
	
//	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		//way 1
//		alert = driver.switchTo().alert();
		//way 2: should use
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		//Cancel
		alert.dismiss();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}
	
//	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);
		//way 1
//		alert = driver.switchTo().alert();
		//way 2: should use
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Prompt");
		String courseName = "FullStack";
		//input text
		alert.sendKeys(courseName);
		//accept
		alert.accept();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + courseName);
	}
	
//	@Test
	public void TC_04_Authentication_Alert_I() {
		driver.get("http://the-internet.herokuapp.com");
		String authenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
//		driver.get("http://the-internet.herokuapp.com/basic_auth");
		driver.get(passUserAndPassToUrl(authenUrl, "admin", "admin"));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations!']")).isDisplayed());

	}
	
	@Test
	public void TC_05_Authentication_Alert_II() throws IOException {
		
		if(driver.toString().contains("firefox")) {
			//Runtime.getRuntime().exec: this code to excute 1 flie exe in Java
			Runtime.getRuntime().exec(new String[] {authenFirefox, username, password });
		}	else if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] {authenChrome, username, password});
		}
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations!']")).isDisplayed());

	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	public String passUserAndPassToUrl(String url, String username, String password) {
		String[] arrayUrl = url.split("//");
		return arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
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
