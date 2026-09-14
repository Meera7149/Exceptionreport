package exceptionreport;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Exception_Runner {
    @Test
	public static void exception_runner() throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		List<WebElement> rows;
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(20));
		driver.get("http://100.100.50.14/srx/login/?continue=http%3A%2F%2F100.100.50.14%2Fsrx%2Fdashboard");
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
		WebElement elementToScroll = driver.findElement(By.xpath("//*[text()='EBS']"));
		actions.scrollToElement(elementToScroll).perform();
		actions.click(elementToScroll).perform();
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
