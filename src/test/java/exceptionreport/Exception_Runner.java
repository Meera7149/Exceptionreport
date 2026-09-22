package exceptionreport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
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
	public static void exception_runner() throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		/*
		 * ChromeOptions options = new ChromeOptions();
		 * 
		 * options.addArguments("--user-data-dir=C:\\JenkinsChromeProfile");
		 * options.addArguments("--disable-features=PasswordLeakDetection");
		 * 
		 * Map<String, Object> prefs = new HashMap<>();
		 * prefs.put("profile.password_manager_leak_detection", false);
		 * prefs.put("credentials_enable_service", false);
		 * prefs.put("profile.password_manager_enabled", false);
		 * 
		 * options.setExperimentalOption("prefs", prefs);
		 */

		ChromeDriver driver = new ChromeDriver();
	
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(60));
		driver.get("http://100.100.50.14/srx/dashboard#!");
		//driver.get("http://100.100.60.116:8090/srx/dashboard#!");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@name='sign_in_username_email']")).sendKeys("Bt_support");
		driver.findElement(By.xpath("//*[@name='sign_in_password']")).sendKeys("OTA@#$2024");
		driver.findElement(By.xpath("//*[text()=' Sign In ']")).click();
		driver.findElement(By.xpath("//*[text()='OK']")).click();
	
		// Mouse over example - hover over an element
		Actions actions = new Actions(driver);
		WebElement elementToHover1 = driver.findElement(By.xpath("(//*[text()='Booking Reports'])[2]"));
		actions.moveToElement(elementToHover1).build().perform();
		WebElement elementToHover = driver.findElement(By.xpath("(//*[@class='material-icons'])[6]"));
		actions.moveToElement(elementToHover).build().perform();
		
		//scrolldown example
		//WebElement elementToScroll = driver.findElement(By.xpath("//*[text()='EBS']"));
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));

		

		WebElement elementToHover2 = wait.until(
		    ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//*[normalize-space()='EBS']/ancestor::*[self::a or self::li or self::button][1]")
		    )
		);
		
		
		
		actions.scrollToElement(elementToHover2).perform();
		actions.click(elementToHover2).perform();
		driver.findElement(By.xpath("//*[text()=' Exception Reports']")).click();
		driver.findElement(By.xpath("//*[text()='Amount mismatch between Flight Booking and Sold Report']")).click();
		driver.findElement(By.xpath("//*[@id='from_date']")).sendKeys("01-01-2025");
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		
		/*
		 * FileWriter writer = new FileWriter("automation-result.txt");
		 * 
		 * writer.write("Amount mismatch between Flight Booking and Sold Report\n");
		 * 
		 * int rowCount = driver.findElements( By.xpath("//*[@id='example']/tbody/tr")
		 * ).size();
		 * 
		 * for (int i = 1; i <= rowCount; i++) {
		 * 
		 * WebElement column = driver.findElement(
		 * By.xpath("//*[@id='example']/tbody/tr[" + i + "]/td[2]") );
		 * 
		 * System.out.println(column.getText()); writer.write(column.getText() + "\n");
		 * } writer.close();
		 */
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));

		By rowsLocator = By.xpath("//*[@id='example']/tbody/tr");

		// Wait until at least one row appears
		wait2.until(ExpectedConditions.presenceOfElementLocated(rowsLocator));
		int previousCount = 0;
		int stableCount = 0;

		while (stableCount < 5) {

		    int currentCount = driver.findElements(rowsLocator).size();

		    System.out.println("Current rows: " + currentCount);

		    if (currentCount == previousCount) {
		        stableCount++;
		    } else {
		        stableCount = 0;
		    }

		    previousCount = currentCount;

		    Thread.sleep(1000);
		}

		// Get all rows
		List<WebElement> rows = driver.findElements(rowsLocator);

		System.out.println("Total rows found: " + rows.size());

		File resultFile = new File("automation-result.txt");

		//System.out.println("File path: " + resultFile.getAbsolutePath());

		try (FileWriter writer = new FileWriter(resultFile, false)) {

		    
		    writer.write(System.lineSeparator());

		    for (WebElement row : rows) {

		        List<WebElement> cells = row.findElements(By.tagName("td"));

		        if (cells.size() > 1) {

		            String value = cells.get(1).getText().trim();

		            System.out.println("Saving: " + value);

		            writer.write(value);
		            writer.write(System.lineSeparator());
		        }
		    }

		    writer.flush();
		}
		}

}
