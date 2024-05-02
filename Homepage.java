package website;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Homepage {
	WebDriver driver;
	//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
	
	
	@BeforeTest
	public void beforeTest()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://dev-web.trybeem.com/");
		driver.manage().window().maximize();
		
	}
	
	@Test(enabled=false)
	public void Features()
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'ng-binding')]"))).click();
		WebElement features= driver.findElement(By.xpath("//span[contains(@class,'flex items-center')]"));
		Actions action = new Actions(driver);
		action.moveToElement(features).perform();
	}
	
	@Test 
	public void subscriptionPlanChange() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'ng-binding')]"))).click();
		driver.findElement(By.xpath("//span[contains(@class ,'opacity-1') and text()='Log in']")).click();
		WebElement phone = driver.findElement(By.xpath("//input[contains(@id ,'phone')]"));
		phone.sendKeys("8193344354");
		driver.findElement(By.xpath("//span[contains(@class , 'opacity-1') and text()='Send verification code']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'ng-binding')]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style = 'display: flex; align-items: center;'][1]//input"))).sendKeys("9");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style = 'display: flex; align-items: center;'][2]//input"))).sendKeys("1");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style = 'display: flex; align-items: center;'][3]//input"))).sendKeys("9");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style = 'display: flex; align-items: center;'][4]//input"))).sendKeys("1");
		driver.findElement(By.xpath("//span[contains(@class , 'opacity-1') and text()='Verify code']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class ,'mt-4 text-base text-gray-500 md:mx-auto md:max-w-3xl') and text()='Welcome back']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
		
		List<WebElement> element = driver.findElements(By.xpath("//div[contains(@class ,'flex flex-grow basis-0 flex-col rounded-lg border-2 px-6 py-8 font-semibold')]//span[text() = 'Update subscription']"));
		if(element.isEmpty())
		{
			driver.findElement(By.xpath("//span[contains(@class , 'opacity-1'] and text() = 'Logout'")).click();
		}
		else
		{
			driver.findElement(By.xpath("//div[@class ='flex flex-grow basis-0 flex-col rounded-lg border-2 px-6 py-8 font-semibold']//span[text() = 'Update subscription']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(@class ,' font-medium tracking-tight text-gray-900 font-heading text-5xl') and text()='Introducing new plans']")));
			js.executeScript("window.scrollBy(0,300)", "");
			WebElement disabledSubscribeButton = driver.findElement(By.xpath("//div[contains(@class , ' flex justify-between items-center px-6')]/child::button" ));
			String disabledButtonText = disabledSubscribeButton.getText();
			System.out.println(disabledButtonText);
			if(disabledButtonText.contains("Pro"))
			{
				driver.findElement(By.xpath("//h3[contains(text(),'Plus')]/parent::div[contains(@class , 'flex flex-col items-center justify-center mb-5')]")).click();
				driver.findElement(By.xpath("//div[contains(@class , ' flex justify-between items-center px-6')]/child::button")).click();
						
			}
		}
		
		
		
		
		
		
	}
}
