package tests.api;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

public class APILocationSearchTests extends BaseAPITest {
    private static final String SEARCH_BASE_PATH = "search";

    @Test
    public void locationSearch_Kiev() {
        get(SEARCH_BASE_PATH + "/?query=kiev")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", is(1))
                .body("title[0]", equalTo("Kiev"))
                .body("location_type[0]", equalTo("City"))
                .body("woeid[0]", equalTo(924938))
                .body("latt_long[0]", equalTo("50.441380,30.522490"));
    }

    @Test
    public void locationSearch_Ki() {
        get(SEARCH_BASE_PATH + "/?query=ki")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", is(8));
    }

    @Test
    public void locationSearch_EmptyQuery() {
        get(SEARCH_BASE_PATH + "/?query=\"\"")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", is(0));
    }

    @Test
    public void locationSearch_NotCompletedQuery_403() {
        get(SEARCH_BASE_PATH + "/?query=")
                .then()
                .statusCode(403);
    }

    @Test
    public void locationSearch_MissedQuery_403() {
        get(SEARCH_BASE_PATH)
                .then()
                .statusCode(403);
    }

    @Test
    public void whenValidateResponseTime_thenMulty200() {
        get(SEARCH_BASE_PATH + "/?query=ki")
                .then()
                .statusCode(200)
                .time(lessThan(500L));
    }

    @Test
    public void whenValidateResponseTime_then403() {
        get(SEARCH_BASE_PATH + "/?query=")
                .then()
                .statusCode(403)
                .time(lessThan(500L));
    }

    @Test
    public void locationSearchByLattlong_200() {
        var rand = new Random();

        get(SEARCH_BASE_PATH + "/?lattlong=50.441380,30.522490")
                .then()
                .statusCode(200)
                .assertThat()
                .body("size()", is(10))
                .body("distance[" + rand.ints(1, 10).findFirst().getAsInt() + "]", greaterThan(0))
                .body("title[" + rand.ints(1, 10).findFirst().getAsInt() + "]", not(emptyString()))
                .body("location_type[" + rand.ints(1, 10).findFirst().getAsInt() + "]", not(emptyString()))
                .body("woeid[" + rand.ints(1, 10).findFirst().getAsInt() + "]", greaterThan(0))
                .body("latt_long[" + rand.ints(1, 10).findFirst().getAsInt() + "]", matchesPattern("\\d+\\.\\d+,[-]?\\d+\\.\\d+"))
        ;
    }

    //TODO it seems that it is a bug and return 500. Expected some 4XX response code
    @Test
    public void locationSearchByLattlong_EmptyLattlong_403() {
        get(SEARCH_BASE_PATH + "/?lattlong=\"\"")
                .then()
                .statusCode(anyOf(is(403), is(400), not(is(500))));
    }

    @Test
    public void locationSearchByLattlong_NotCompletedLattlong_403() {
        get(SEARCH_BASE_PATH + "/?lattlong=")
                .then()
                .statusCode(403);
    }

    @Test
    public void locationSearchByLattlong_ZeroLattlong_200() {
        get(SEARCH_BASE_PATH + "/?lattlong=0,0")
                .then()
                .statusCode(200)
                .body("size()", is(10));
    }

    @Test
    public void locationSearchByLattlong_NegativeLattlong_200() {
        get(SEARCH_BASE_PATH + "/?lattlong=-100,-100")
                .then()
                .statusCode(200)
                .body("size()", is(10));
    }

    @Test
    public void locationSearchByLattlong_MaxLattlong_200() {
        get(SEARCH_BASE_PATH + "/?lattlong=" + Integer.MIN_VALUE + "," + Integer.MIN_VALUE + "")
                .then()
                .statusCode(200)
                .body("size()", is(10));
    }
}
