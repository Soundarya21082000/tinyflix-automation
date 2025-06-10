package tinyflix;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class FilterTest {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.get("http://localhost:5173/");
            driver.manage().window().maximize();

            // Step 1: Locate the filter dropdown
            WebElement filterDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("select[aria-label='Filter videos']")
            ));

            Select filterSelect = new Select(filterDropdown);

            // Step 2: Apply "Popular" filter
            filterSelect.selectByValue("popular");
            Thread.sleep(2000); // allow DOM to update

            List<WebElement> popularVideos = driver.findElements(By.cssSelector(".grid .video-card"));
            System.out.println("🎯 Popular Filter: Found " + popularVideos.size() + " video(s)");

            // Step 3: Apply "Recent" filter
            filterSelect.selectByValue("recent");
            Thread.sleep(2000);

            List<WebElement> recentVideos = driver.findElements(By.cssSelector(".grid .video-card"));
            System.out.println("🕒 Recent Filter: Found " + recentVideos.size() + " video(s)");

            // Step 4: Apply "All Videos" filter
            filterSelect.selectByValue("all");
            Thread.sleep(2000);

            List<WebElement> allVideos = driver.findElements(By.cssSelector(".grid .video-card"));
            System.out.println("📺 All Videos: Found " + allVideos.size() + " video(s)");

            // Step 5: Assertions
            if (allVideos.size() >= popularVideos.size() && allVideos.size() >= recentVideos.size()) {
                System.out.println("✅ Filter test passed.");
            } else {
                System.out.println("❌ Filter test failed: Filtered results count mismatch.");
            }

        } catch (Exception e) {
            System.out.println("❌ Test failed due to: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
