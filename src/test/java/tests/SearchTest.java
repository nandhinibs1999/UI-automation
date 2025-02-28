package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public void testSearchFunctionality(String keyword) throws InterruptedException {
        logger.info("=== Starting search test for keyword: " + keyword + " ===");

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        // Perform search
        homePage.search(keyword);
        logger.info("Search performed for: " + keyword);

        // Capture search results (fetch top 2 results)
        List<String> results = searchResultsPage.getTopSearchResults(2); 

        if (results.isEmpty()) {
            logger.error("❌ No search results found for keyword: " + keyword);
        } else {
            logger.info("✅ First Search Result: " + results.get(0));
            if (results.size() > 1) {
                logger.info("✅ Second Search Result: " + results.get(1));
            }
        }

        // Assert that at least one result is found
        Assert.assertFalse(results.isEmpty(), "❌ No search results found for keyword: " + keyword);
    }
}
