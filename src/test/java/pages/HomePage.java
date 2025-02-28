package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Updated locators
    private By searchIcon = By.xpath("//div[@class='main']//*[name()='svg']");
    private By searchInput = By.xpath("//input[@type='search' or @placeholder='Search']");
    private By searchButton = By.xpath("//button[contains(@id, 'button-addon')]");
    private By searchResults = By.xpath("//div[contains(@class,'search-results')]//a | //h3//a");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait for up to 10 seconds
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
    }

    // Method to perform search with explicit wait after scrolling
    public void search(String keyword) {
        WebElement searchIconElement = driver.findElement(searchIcon);
        Actions actions = new Actions(driver);
        actions.moveToElement(searchIconElement).click().perform();

        // Enter search keyword
        WebElement searchBoxElement = driver.findElement(searchInput);
        searchBoxElement.sendKeys(keyword);

        // Wait for the search button to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));

        // Click the search button
        driver.findElement(searchButton).click();

        // Scroll down to make search results visible
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600);");

        // Wait for search results to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));

        System.out.println("Search results are now visible.");
    }
}
