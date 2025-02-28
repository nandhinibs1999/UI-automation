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

    // XPath to capture search results
    private By searchResultsLocator = By.xpath("//div[contains(@class, 'search-results')]//a");

    // Constructor
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Getter for the search results locator
    public By getSearchResultsLocator() {
        return searchResultsLocator;
    }

    public List<String> getTopSearchResults(int maxResults) {
        List<String> topResults = new ArrayList<>();

        // Wait for search results to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsLocator));

        List<WebElement> results = driver.findElements(searchResultsLocator);

        System.out.println("Number of search results found: " + results.size());

        if (!results.isEmpty()) {
            for (int i = 0; i < Math.min(results.size(), maxResults); i++) {
                String resultText = results.get(i).getText().trim();
                System.out.println("Search Result " + (i + 1) + ": " + resultText);
                topResults.add(resultText);
            }
        } else {
            System.err.println("ERROR: No search results found!");
        }

        return topResults;
    }

    // Validate if expected values exist in actual results
   public boolean validateResults(List<String> actualResults, List<String> expectedResults) {
        for (String expected : expectedResults) {
            if (actualResults.contains(expected)) {
                return true;  
            }
        }
        return false;
    }
}
