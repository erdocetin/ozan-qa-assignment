package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickLogin() {
        driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]")).click();
    }
}
