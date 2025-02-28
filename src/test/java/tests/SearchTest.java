package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(SearchTest.class);

    @DataProvider(name = "searchKeywords")
    public Object[][] getKeywords() {
        return new Object[][] {
            {"mobile"},
            {"python"},
            {"job"}
        };
    }

    @Test(dataProvider = "searchKeywords")
    public void testSearchFunctionality(String keyword) {
        logger.info("=== Starting search test for keyword: " + keyword + " ===");

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        // Perform search
        homePage.search(keyword);
        logger.info("Search performed for: " + keyword);

        // Capture search results
        List<String> results = searchResultsPage.getTopSearchResults(1); // Fetch at least one result

        if (results.isEmpty()) {
            logger.error("❌ No search results found for keyword: " + keyword);
        } else {
            logger.info("✅ Search result found: " + results.get(0));
        }

        // Assert that at least one result is found
        Assert.assertFalse(results.isEmpty(), "❌ No search results found for keyword: " + keyword);
    }

}
