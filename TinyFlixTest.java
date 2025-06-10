package tinyflix;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TinyFlixTest {
    public static void main(String[] args) throws InterruptedException {
        // Setup driver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Open your app
        driver.get("http://localhost:5173/");
        driver.manage().window().maximize();

        // Wait to load
        Thread.sleep(2000);

        // Test search functionality
        WebElement searchInput = driver.findElement(By.cssSelector("input[placeholder='Search videos, tags, or descriptions']"));
        searchInput.sendKeys("React");

        Thread.sleep(2000); // Wait for filter to apply

        WebElement result = driver.findElement(By.xpath("//*[contains(text(),'React Basics')]"));
        if (result.isDisplayed()) {
            System.out.println("✅ Search test passed: React video found");
        } else {
            System.out.println("❌ Search test failed");
        }

        // Close browser
        driver.quit();
    }
}
