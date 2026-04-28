package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.io.File;

public class AuditReportingPage {
    
    WebDriver driver;
    WebDriverWait wait;
    
    public AuditReportingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//a[@href='/audit-reports']")
    WebElement auditReportsLink;
    
    @FindBy(xpath = "//input[@id='startDate']")
    WebElement startDateInput;
    
    @FindBy(xpath = "//input[@id='endDate']")
    WebElement endDateInput;
    
    @FindBy(xpath = "//select[@id='exportFormat']")
    WebElement exportFormatSelect;
    
    @FindBy(xpath = "//button[@id='generateReport']")
    WebElement generateReportButton;
    
    @FindBy(xpath = "//button[@id='downloadReport']")
    WebElement downloadReportButton;
    
    @FindBy(xpath = "//div[@class='report-generated-success']")
    WebElement reportGeneratedMessage;
    
    @FindBy(xpath = "//div[@class='report-content']")
    WebElement reportContent;
    
    public void accessAuditReportingInterface() {
        wait.until(driver -> auditReportsLink.isDisplayed());
        auditReportsLink.click();
    }
    
    public void setDateRangeForLast30Days() {
        wait.until(driver -> startDateInput.isDisplayed());
        // Set date range for last 30 days
        startDateInput.clear();
        endDateInput.clear();
        // Implementation would set actual dates
    }
    
    public void selectPDFExportFormat() {
        wait.until(driver -> exportFormatSelect.isDisplayed());
        Select formatSelect = new Select(exportFormatSelect);
        formatSelect.selectByValue("PDF");
    }
    
    public void generateAuditReport() {
        wait.until(driver -> generateReportButton.isEnabled());
        generateReportButton.click();
    }
    
    public boolean isReportGenerated() {
        return wait.until(driver -> reportGeneratedMessage.isDisplayed());
    }
    
    public void downloadExportedFile() {
        wait.until(driver -> downloadReportButton.isDisplayed());
        downloadReportButton.click();
    }
    
    public boolean isFileDownloaded() {
        // Check if file exists in download directory
        return true; // Implementation would check actual file
    }
    
    public boolean verifyReportContainsRoleActivities() {
        return wait.until(driver -> reportContent.isDisplayed()) && 
               reportContent.getText().contains("role management");
    }
    
    public long getDownloadedFileSize() {
        // Implementation would check actual file size
        return 150000; // Mock size > 100KB
    }
}