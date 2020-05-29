import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ApiTest {

    public static final String baseURI = "https://jsonplaceholder.typicode.com/posts";

    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().contentType(ContentType.JSON).extract().response();
    }

    @Test
    public void testRequestForStatusCode(){
        RestAssured
                .given()
                .when()
                .get(baseURI)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testContentType(){
        RestAssured
                .given()
                .when()
                .get(baseURI)
                .then()
                .contentType(ContentType.JSON);
    }

    @Test
    public void testNumberOfPosts(){
        // Extract
        Response response = doGetRequest(baseURI);
        List<String> userIds = response.jsonPath().getList("userId");

        Assert.assertEquals(10, Collections.frequency(userIds, 5));
        Assert.assertEquals(10, Collections.frequency(userIds, 7));
        Assert.assertEquals(10, Collections.frequency(userIds, 9));
    }

    @Test
    public void testUniqueIdOfPosts(){
        // Extract
        Response response = doGetRequest(baseURI);
        List<Integer> ids = response.jsonPath().getList("id");

        Map<Integer, Long> frequencyMap = ids.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (Map.Entry<Integer, Long> entry : frequencyMap.entrySet()) {
            Assert.assertTrue(entry.getValue().compareTo(1l) == 0, "blog post should have a unique ID");
        }
    }
}
