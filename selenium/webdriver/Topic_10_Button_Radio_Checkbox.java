package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Radio_Checkbox {
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
	
	
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		By loginButton = By.cssSelector("button.fhs-btn-login");
		//Verify login button is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
//		String loginButtonBackground = driver.findElement(loginButton).getCssValue("background-color");
		String loginButtonBackground = driver.findElement(loginButton).getCssValue("background-image");
		Assert.assertTrue(loginButtonBackground.contains("rgb(224, 224, 224)"));
		System.out.println(loginButtonBackground);
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0905123456");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
		sleepInSecond(2);
		
		//Verify login button is enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		loginButtonBackground = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println(loginButtonBackground);
		
		Color loginButtonBackgroundColour = Color.fromString(loginButtonBackground);
		Assert.assertEquals(loginButtonBackgroundColour.asHex().toUpperCase(), "#C92127");
		Assert.assertEquals(loginButtonBackgroundColour.asRgb().toUpperCase(), "rgb(201, 33, 39)");
				
	}
	
	
	public void TC_02_Default_CheckBox_Radio_Single() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		//Click 1 checkbox
		driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
		//Click 1 radio
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).isSelected());
		//Checkbox can unselected
		driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).isSelected());
		//Radio can not unselected
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).isSelected());
	}
	
	
	public void TC_03_Default_CheckBox_Multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		//Use a loop to find all elements and click
		for (WebElement checkbox : allCheckboxes) {
			checkbox.click();
			sleepInSecond(1);
		}
		//Verify all elements in checkbox is selected
		for (WebElement checkbox : allCheckboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}
	}
	
	
	public void TC_04_Default_CheckBox_Select_Condition() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		//Use a loop to find all elements and click
		for (WebElement checkbox : allCheckboxes) {
			if (checkbox.getAttribute("value").equals("Arthritis")) {
				checkbox.click();
			}
		}
	}
	
	
	public void TC_05_Default_CheckBox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		if (!driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
			//Click 1 checkbox
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		}
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		//Checkbox can unselected
		if (driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
			//Click 1 checkbox
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		}
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
	}
	
	@Test
	public void TC_06_Default_CheckBox_Function() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		checkToCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		//Checkbox can unselected
		unCheckToCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
	}
	
	public void checkToCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void unCheckToCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
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
		//driver.quit();
	}
}
