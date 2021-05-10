package getSWAPI;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetFilmsTest extends BaseTest {

    @Test
    public void readAllPlanets() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + PEOPLE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }

    @Test
    public void readOnePlanet() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + FILMS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(json.getString("title")).isEqualTo("A New Hope");
        assertThat(json.getString("director")).isEqualTo("George Lucas");

    }
}