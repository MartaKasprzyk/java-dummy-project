package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class AuditReportingPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By auditReportingInterface = By.xpath("//div[@id='audit-reporting-interface']");
    private By startDateField = By.xpath("//input[@id='start-date']");
    private By endDateField = By.xpath("//input[@id='end-date']");
    private By generateReportButton = By.xpath("//button[@id='generate-report']");
    private By downloadButton = By.xpath("//button[@id='download-report']");
    private By reportStatus = By.xpath("//div[@id='report-status']");
    private By reportProgress = By.xpath("//div[@class='progress-bar']");
    
    public AuditReportingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void accessAuditReportingInterface() {
        wait.until(ExpectedConditions.elementToBeClickable(auditReportingInterface));
    }
    
    public void selectLargeDateRange(String startDate, String endDate) {
        WebElement startField = wait.until(ExpectedConditions.elementToBeClickable(startDateField));
        startField.clear();
        startField.sendKeys(startDate);
        
        WebElement endField = wait.until(ExpectedConditions.elementToBeClickable(endDateField));
        endField.clear();
        endField.sendKeys(endDate);
    }
    
    public void generateReport() {
        WebElement generateBtn = wait.until(ExpectedConditions.elementToBeClickable(generateReportButton));
        generateBtn.click();
    }
    
    public boolean isReportGenerated() {
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(reportStatus, "Report generated successfully"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isDownloadButtonVisible() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(downloadButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void downloadReport() {
        WebElement downloadBtn = wait.until(ExpectedConditions.elementToBeClickable(downloadButton));
        downloadBtn.click();
    }
    
    public boolean isSystemResponsive() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(reportProgress));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}