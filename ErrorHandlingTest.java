package tinyflix;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class ErrorHandlingTest {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.get("http://localhost:5173/");
            driver.manage().window().maximize();

            // Simulate clicking a video ID that doesn't exist
            driver.navigate().to("http://localhost:5173/video/9999"); // Non-existent video (invalid route)
            Thread.sleep(2000); // Allow app to process the missing ID

            // Wait for the error component to show up
            WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error[role='alert']"))
            );

            String errorText = errorMessage.getText();
            System.out.println("❗ Error message displayed: " + errorText);

            if (errorText.toLowerCase().contains("not found") || errorText.toLowerCase().contains("error")) {
                System.out.println("✅ Error handling test passed.");
            } else {
                System.out.println("❌ Error handling test failed: Unexpected error text.");
            }

        } catch (Exception e) {
            System.out.println("❌ Test failed due to: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
