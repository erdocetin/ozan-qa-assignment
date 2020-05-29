package login;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;

public class LoginStepdefs {

    private WebDriver webDriver;

    @Given("I am a user of amazon.com")
    public void iAmAUserOfAmazonCom() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://www.amazon.com/");
        HomePage homePage = new HomePage(webDriver);
        homePage.clickLogin();
    }

    @When("^I log in using valid credentials with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iLogInUsingValidCredentialsWithAnd(String username, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(username, password);
    }

    @Then("^I should be logged in$")
    public void iShouldBeLoggedIn() {
        String expectedUrl = "https://www.amazon.com/?ref_=nav_";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        String actualUrl = webDriver.getCurrentUrl();

        Assert.assertTrue("Login failed", actualUrl.startsWith(expectedUrl));
    }

    @When("^I log in using invalid credentials with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iLogInUsingInvalidCredentialsWithAnd(String username, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(username, password);
    }

    @Then("^I should not be logged in$")
    public void iShouldNotBeLoggedIn() {
        String expectedUrl = "https://www.amazon.com/ap/signin";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        String actualUrl = webDriver.getCurrentUrl();
        System.out.println("ActualUrl: " + actualUrl);

        if(webDriver != null) {
            webDriver.quit();
        }

        Assert.assertTrue("Invalid login failed", actualUrl.startsWith(expectedUrl));
    }
}
