package selenium;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_08_Button_Checkbox_Alert_Radio {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor jsExecuter;
	Actions action;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver",
				".\\lib\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");

		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void Testcase01() throws InterruptedException {
		WebElement profile = driver.findElement(By.xpath("//span[contains(text(),'Profile')]"));
		action.moveToElement(profile).perform();

		WebElement loginBtn = driver
				.findElement(By.xpath("//a[@class='desktop-linkButton' and text()='login / Signup']"));
		action.moveToElement(loginBtn).perform();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='signInContainer']")).isDisplayed());
	}

	public void TC_multipleSelect() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

		System.out.println("List item chua chon" + listItems.size());
		action.clickAndHold(listItems.get(0)).moveToElement(listItems.get(3)).release().perform();

		List<WebElement> listSelected = driver
				.findElements(By.xpath("//ol[@id='selectable']/li[@class='ui-state-default ui-selectee ui-selected']"));
		System.out.println("List item chua chon" + listSelected.size());
	}

	public void TC_04_multipleSelectClick() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.keyDown(Keys.CONTROL).perform();
		action.click(listItems.get(0));
		action.click(listItems.get(2));
		action.click(listItems.get(5));
		action.click(listItems.get(6));
		action.keyUp(Keys.CONTROL).perform();
	}
	
	public void TC_01_hoverToElement() throws Exception {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		WebElement age = driver.findElement(By.xpath("//input[@id='age']"));
		WebElement tooltip = driver.findElement(By.xpath("//a[text()='Tooltips']"));
		action.moveToElement(tooltip).perform();

		action.moveToElement(age).perform();
		System.out.println("Tooltip la" + tooltip.getAttribute("title"));
		//Assert.assertEquals(age.getAttribute("title"),"We ask for your age only for statistical purposes.");
	}
	
	
	public void TC_02_hoverToElement2() {
		driver.get("http://www.myntra.com/");
		WebElement kid = driver.findElement(By.xpath("//a[text()='Kids']"));
		action.moveToElement(kid).perform();
		driver.findElement(By.xpath("//a[text()='Personal Care']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(text(),'Kids Wear Online Store')]")).getText(), "Kids Wear Online Store");
		
	}
	
	public void TC_03_hoverToElement3() throws Exception {
		driver.get("https://www.fahasa.com/");
		WebElement iconMenu = driver.findElement(By.xpath("//div[@class='container']//span[@class='icon_menu']"));
		action.moveToElement(iconMenu).perform();
		
		WebElement kiNangSong = driver.findElement(By.xpath("//span[contains(text(),'Sách Trong Nước')]"));
		action.moveToElement(kiNangSong).perform();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//a[text()='Tiểu Thuyết']")).click();
		//Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Tiểu Thuyết']")).isDisplayed());
	}
	
	
	public void TC_06_doubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement doubleClickBtn = driver.findElement(By.xpath("//button[contains(text(),'Double click me')]"));
		action.doubleClick(doubleClickBtn).perform();
		WebElement result = driver.findElement(By.xpath("//p[@id='demo']"));
		Assert.assertEquals(result.getText(), "Hello Automation Guys!");
	}
	
	public void TC_07_rightClickToElement() throws Exception {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightClickBtn = driver.findElement(By.xpath("//span[contains(text(),'right click me')]"));
		action.contextClick(rightClickBtn).perform();
		
		WebElement quitContext = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-paste')]"));
		action.moveToElement(quitContext).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-paste context-menu-hover context-menu-visible')]")).isDisplayed());
		
		quitContext.click();
		Thread.sleep(1500);

		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: paste");
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void TC_08_dragAndDrop() throws Exception {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement largeCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		action.dragAndDrop(smallCircle, largeCircle).perform();
		Thread.sleep(1500);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());
	}
	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
