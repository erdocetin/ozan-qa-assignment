package departments;

import departments.storage.LinkStatusDropboxSaver;
import departments.storage.LinkStatusSaver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class DeadLinkTest {

    private WebDriver driver;
    private LinkStatusSaver linkStatusSaver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        Page page = new Page("https://www.amazon.com","//*[@id=\"nav-search\"]/form", "//*[@id=\"searchDropdownBox\"]");
        Engine engine = new Engine(driver, page);
        List<VisitedLink> visitedLinks = engine.checkLinks();

        linkStatusSaver =  new LinkStatusDropboxSaver("osman.sirin@openpayd.com","Password1!", driver);
        try {
            linkStatusSaver.save(visitedLinks);
        } catch (Exception e) {
            System.out.println("Exception while saving file: " + e.getMessage());
        }
    }
}
