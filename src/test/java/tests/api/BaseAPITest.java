package tests.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

@Slf4j
public class BaseAPITest {
    @BeforeAll
    public static void preconditions() {
        RestAssured.baseURI = System.getProperty("base.api.url");
        RestAssured.basePath = "api/location/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        log.info("API URI: " + RestAssured.baseURI + RestAssured.basePath);
    }

    @AfterAll
    public static void cleanUP() {
        RestAssured.reset();
    }
}
