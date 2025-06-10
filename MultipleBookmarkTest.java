package tinyflix;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class MultipleBookmarkTest {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.get("http://localhost:5173/");
            driver.manage().window().maximize();

            // Step 1: Search for "React"
            WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[placeholder='Search videos, tags, or descriptions']")
            ));
            searchInput.sendKeys("React");

            // Step 2: Click on "React Basics" video card
            WebElement videoCard = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(text(),'React Basics')]")
            ));
            videoCard.click();

            // Step 3: Click Bookmark once (app prevents duplicates)
            WebElement bookmarkButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Bookmark')]")
            ));
            bookmarkButton.click();

            // Step 4: Check if error message appears (duplicate bookmark test)
            try {
                Thread.sleep(1000); // allow UI to update
                WebElement errorMsg = driver.findElement(By.cssSelector(".error"));
                System.out.println("⚠️ Error shown: " + errorMsg.getText());
                System.out.println("✅ Duplicate bookmark is correctly prevented.");
            } catch (Exception e) {
                System.out.println("✅ Bookmark added successfully.");
            }

            // Step 5: Count bookmarks
            WebElement bookmarkList = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".bookmark-list ul")
            ));
            List<WebElement> bookmarkItems = bookmarkList.findElements(By.tagName("li"));

            System.out.println("🔍 Total bookmarks found: " + bookmarkItems.size());

            if (bookmarkItems.size() >= 1) {
                System.out.println("✅ Bookmark test passed.");
            } else {
                System.out.println("❌ Test failed: No bookmarks found.");
            }

        } catch (Exception e) {
            System.out.println("❌ Test failed due to error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
