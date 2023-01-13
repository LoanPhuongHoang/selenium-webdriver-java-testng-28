package webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait; 
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
		//new is create the variable
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		//Select a speed
		selectItemDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");
		
		selectItemDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");
		
		selectItemDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slow");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slow");
		
		selectItemDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Fast");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");
		//Select a title
		selectItemDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Prof.");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Prof.");
				
	}
	
	
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		//Select
		selectItemDropdown("i.dropdown.icon", "span.text", "Stevie Feliciano");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");
		
		selectItemDropdown("i.dropdown.icon", "span.text", "Matt");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
		
		selectItemDropdown("i.dropdown.icon", "span.text", "Jenny Hess");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
		
		
	}
	
	
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		//Select a speed
		selectItemDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		selectItemDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
		selectItemDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
	}
	
	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		//Select a speed
		enterItemDropdown("input.search", "span.text", "Angola");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Angola");
		
		selectItemDropdown("input.search", "span.text", "Australia");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Australia");
		
		selectItemDropdown("input.search", "span.text", "Belgium");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");
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
	
	public void selectItemDropdown(String parentCss, String allItemCss, String expectedTextItem) {
		driver.findElement(By.cssSelector(parentCss)).click();
		sleepInSecond(1);
		//Wait for all element to list
		//locator for all elements
		//take the tag contains text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		//Put all items to a list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		//Find if it is the item we need
		for (WebElement tempItem : speedDropdownItems) {
//			String itemText = tempItem.getText();
//			System.out.println(itemText);
			//verify if text of item is what we want
			if (tempItem.getText().trim().equals(expectedTextItem)) {
				//Print out
				System.out.println("Click item");
				sleepInSecond(1);
				//click to item
				tempItem.click();
				//exit loop
				break;
			}
		}
	}
	
	public void enterItemDropdown(String textboxCss, String allItemCss, String expectedTextItem) {
		//clear
		driver.findElement(By.cssSelector(textboxCss)).clear();
		//input expected item- list of matching item is displayed
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedTextItem);
		sleepInSecond(1);
		//Wait for all element to list
		//locator for all elements
		//take the tag contains text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		//Put all items to a list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		//Find if it is the item we need
		for (WebElement tempItem : speedDropdownItems) {
//			String itemText = tempItem.getText();
//			System.out.println(itemText);
			//verify if text of item is what we want
			if (tempItem.getText().trim().equals(expectedTextItem)) {
				//Print out
//				System.out.println("Click item");
				sleepInSecond(1);
				//click to item
				tempItem.click();
				//exit loop
				break;
			}
		}
	}
}
