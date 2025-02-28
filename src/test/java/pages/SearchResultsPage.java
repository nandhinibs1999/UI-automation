package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locator for search results
    private By searchResults = By.xpath("//div[contains(@class,'search-results')]//a | //h3//a");

    // Constructor
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to get top search results
    public List<String> getTopSearchResults(int maxResults) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));

        List<WebElement> resultElements = driver.findElements(searchResults);
        List<String> resultTexts = new ArrayList<>();

        for (int i = 0; i < Math.min(resultElements.size(), maxResults); i++) {
            resultTexts.add(resultElements.get(i).getText().trim());
        }

        return resultTexts;
    }
}
