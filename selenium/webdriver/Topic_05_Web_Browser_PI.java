package webdriver;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_PI {
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
		
		//Interactive with Browser via WebDriver driver variable
		//Interactive with Element via WebElement element variable
	}
	
	@Test
	public void TC_01_() {
		//close the current tab
		driver.close();
		//close all tabs,window/browser
		driver.quit();
		//find 1 element
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		emailTextbox.clear();
		emailTextbox.sendKeys("loan hoang");
		
		driver.findElement(By.xpath("//button[@id=login']")).click();
		//find many elements
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));
		//Open 1 url
		driver.get("https://www.facebook.com/");
		driver.getCurrentUrl();
		
		//save to a variable
		String vietnamesePage = driver.getCurrentUrl();
		Assert.assertEquals(vietnamesePage, "https://vi-vn.facebook.com/");
		
		//use directly, not using variable
		Assert.assertEquals(driver.getCurrentUrl(), "https://vi-vn.facebook.com/");
		
		//Return source code HTML of current page
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("Facebook giúp bạn kết nối"));
		//Return current page title
		driver.getTitle();
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		//take ID of window/tab of driver stands(active)
		String loginWindowID = driver.getWindowHandle();
		//take ID of all Windows
		Set<String> allIDs = driver.getWindowHandles();
				
		driver.manage().window().maximize();
		driver.manage().timeouts();
		
		Options opt = driver.manage();
		opt.getCookies();
		opt.logs();
		Timeouts time = opt.timeouts();
		//wait for an element to be displayed
		time.implicitlyWait(5, TimeUnit.SECONDS);
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		time.implicitlyWait(5000000, TimeUnit.MICROSECONDS);
		//Wait time for a page to load
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		//Wait time for a script to excute
		time.setScriptTimeout(5, TimeUnit.SECONDS);
		
		Window win = opt.window();
		win.fullscreen();
		win.maximize();
		//Test GUI: Font/Size/Color/Position/Location
		win.setPosition(null);
		win.setSize(null);
		win.getSize();
		win.getPosition();
		
		Navigation nav = driver.navigate();
		nav.back();
		nav.refresh();
		nav.forward();
		nav.to("https://vi-vn.facebook.com/");
		
		TargetLocator tar = driver.switchTo();
		tar.alert();
		tar.frame("");
		tar.window("");
	}
	
	@Test
	public void TC_02_() {
		
	}
	
	@Test
	public void TC_03_() {
		
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
