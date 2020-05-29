package departments;

import org.apache.http.client.utils.URIBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Engine {
    private Page page;
    private WebDriver driver;

    public Engine(WebDriver driver, Page page) {
        this.driver = driver;
        this.page = page;
    }

    public List<Link> searchLinks() {
        List<Link> links = new ArrayList<>();
        if(page != null && !StringUtils.isEmpty(page.getLink())) {
            driver.get(page.getLink());
            driver.get(page.getLink());
            WebElement form = driver.findElement(By.xpath(page.getFormSelector()));
            String action = form.getAttribute("action");

            WebElement selectElement = driver.findElement(By.xpath(page.getDepartmentSelector()));
            String paramName = selectElement.getAttribute("name");
            Select select = new Select(selectElement);

            List<WebElement> options = select.getOptions();

            for(WebElement element : options) {
                String title = element.getText();
                String link = null;
                try {
                    link = new URIBuilder(action).addParameter(paramName, element.getAttribute("value")).build().toString();
                } catch (URISyntaxException e) {
                    System.out.println(String.format("%s: URISyntaxException - %s", title, e.getMessage()));
                }
                links.add(new Link(title, link));
            }
        }
        return links;
    }


    public List<VisitedLink> checkLinks() {
        List<VisitedLink> links = new ArrayList<>();
        for (Link link : searchLinks()) {
            links.add(checkLink(link));
        }
        return links;
    }

    public VisitedLink checkLink(Link link) {
        boolean result = false;
        try {
            System.out.println(String.format("Checking link[%s]...", link.getLink()));
            URL url = new URL(link.getLink());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.connect();
            if(conn.getResponseCode() == 200) {
                result = true;
            }
            System.out.println(String.format("Checking link[%s] finished.", link.getLink()));
        } catch (IOException exception) {
            System.out.println(String.format("Connection to link[%s] failed", link.getLink()));
        }

        return new VisitedLink(link.getLink(), link.getTitle(), result);
    }

}
