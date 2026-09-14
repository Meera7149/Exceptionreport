package exceptionreport;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Exception_Runner {
    @Test
	public static void exception_runner() throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--user-data-dir=C:\\JenkinsChromeProfile");
		options.addArguments("--disable-features=PasswordLeakDetection");

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		options.setExperimentalOption("prefs", prefs);

		ChromeDriver driver = new ChromeDriver(options);
		List<WebElement> rows;
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(60));
		driver.get("http://100.100.50.14/srx/dashboard#!");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@name='sign_in_username_email']")).sendKeys("Bt_support");
		driver.findElement(By.xpath("//*[@name='sign_in_password']")).sendKeys("OTA@#$2024");
		driver.findElement(By.xpath("//*[text()=' Sign In ']")).click();
		driver.findElement(By.xpath("//*[text()='OK']")).click();
	
		// Mouse over example - hover over an element
		Actions actions = new Actions(driver);
		WebElement elementToHover = driver.findElement(By.xpath("(//*[@class='material-icons'])[6]"));
		actions.moveToElement(elementToHover).build().perform();
		
		//scrolldown example
		//WebElement elementToScroll = driver.findElement(By.xpath("//*[text()='EBS']"));
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));

		

		WebElement elementToHover2 = wait.until(
		    ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//*[text()='EBS']")
		    )
		);
		
		
		
		actions.scrollToElement(elementToHover2).perform();
		actions.click(elementToHover2).perform();
		driver.findElement(By.xpath("//*[text()=' Exception Reports']")).click();
		driver.findElement(By.xpath("//*[text()='Amount mismatch between Flight Booking and Sold Report']")).click();
		driver.findElement(By.xpath("//*[@id='from_date']")).sendKeys("01-01-2023");
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		
		FileWriter writer = new FileWriter("automation-result.txt");

		writer.write("Amount mismatch between Flight Booking and Sold Report\n");
		
		int rowCount = driver.findElements(
		        By.xpath("//*[@id='example']/tbody/tr")
		).size();

		for (int i = 1; i <= rowCount; i++) {

		    WebElement column = driver.findElement(
		        By.xpath("//*[@id='example']/tbody/tr[" + i + "]/td[2]")
		    );

		    System.out.println(column.getText());
		    writer.write(column.getText() + "\n");
		}
		writer.close();

	}
}
