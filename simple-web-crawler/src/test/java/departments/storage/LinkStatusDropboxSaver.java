package departments.storage;

import departments.VisitedLink;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Collection;

public class LinkStatusDropboxSaver extends LinkStatusFileSaver {

    public static final String DROPBOX_URL = "https://www.dropbox.com";
    private String username;
    private String password;
    private WebDriver driver;

    public LinkStatusDropboxSaver(String username, String password, WebDriver driver) {
        this.username = username;
        this.password = password;
        this.driver = driver;
    }

    @Override
    public File save(Collection<VisitedLink> links) throws InterruptedException {
        File file = super.save(links);
        uploadDropbox(file);
        return file;

    }

    public void uploadDropbox(File file) throws InterruptedException {
        driver.get(DROPBOX_URL);
        driver.findElement(By.xpath("//*[@id=\"sign-up-in\"]")).click();

        WebElement loginForm = driver.findElement(By.className("login-form"));

        loginForm.findElement(By.name("login_email")).sendKeys(username);
        loginForm.findElement(By.name("login_password")).sendKeys(password);
        loginForm.findElement(By.className("signin-button")).submit();

        WebDriverWait wait = new WebDriverWait(driver, 60);

        wait.until(ExpectedConditions.urlContains("/h"));

        String expectedUrl= driver.getCurrentUrl();
        String actualUrl="https://www.dropbox.com/h";
        Assert.assertEquals(expectedUrl,actualUrl);

        driver.findElement(By.xpath("//a[contains(@href, 'https://www.dropbox.com/home/results')]")).click();
        wait.until(ExpectedConditions.urlContains("/home/results"));

        expectedUrl= driver.getCurrentUrl();
        actualUrl="https://www.dropbox.com/home/results";
        Assert.assertEquals(expectedUrl,actualUrl);

        WebElement container = driver.findElement(By.id("flash-upload-container"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.display = 'block';", container);

        WebElement uploadFileButton = driver.findElement(By.className("uee-AppActionsView-SecondaryActionMenu-text-upload-file"));
        uploadFileButton.click();

        WebElement fileElem = container.findElement(By.xpath("//input[@type='file']"));
        fileElem.sendKeys(file.getAbsolutePath());
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
