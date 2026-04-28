package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

public class AuditReportPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By auditReportingLink = By.xpath("//a[text()='Audit Reports']");
    private By dateRangeFromField = By.xpath("//input[@id='date-from']");
    private By dateRangeToField = By.xpath("//input[@id='date-to']");
    private By exportFormatDropdown = By.xpath("//select[@id='export-format']");
    private By generateReportButton = By.xpath("//button[@id='generate-report']");
    private By downloadButton = By.xpath("//button[@id='download-report']");
    private By reportGeneratedMessage = By.xpath("//div[contains(@class, 'success-message')]");
    private By reportSizeInfo = By.xpath("//span[@class='file-size']");
    
    public AuditReportPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void accessAuditReporting() {
        wait.until(ExpectedConditions.elementToBeClickable(auditReportingLink)).click();
    }
    
    public void setDateRange(String fromDate, String toDate) {
        wait.until(ExpectedConditions.elementToBeClickable(dateRangeFromField)).clear();
        driver.findElement(dateRangeFromField).sendKeys(fromDate);
        
        wait.until(ExpectedConditions.elementToBeClickable(dateRangeToField)).clear();
        driver.findElement(dateRangeToField).sendKeys(toDate);
    }
    
    public void selectExportFormat(String format) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(exportFormatDropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText(format);
    }
    
    public void generateReport() {
        wait.until(ExpectedConditions.elementToBeClickable(generateReportButton)).click();
    }
    
    public boolean isReportGenerated() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(reportGeneratedMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void downloadReport() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadButton)).click();
    }
    
    public boolean isFileSizeValid() {
        try {
            WebElement sizeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(reportSizeInfo));
            String sizeText = sizeElement.getText();
            // Extract size and check if > 100KB - simplified for this example
            return sizeText.contains("KB") || sizeText.contains("MB");
        } catch (Exception e) {
            return false;
        }
    }
}