package getSWAPI;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetPeopleTest extends BaseTest {
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
                .get(BASE_URL + PEOPLE + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> films = json.getList("films");

        assertThat(json.getString("name")).isEqualTo("Luke Skywalker");
        assertThat(films).hasSize(5).contains("https://swapi.py4e.com/api/films/1/","https://swapi.py4e.com/api/films/2/","https://swapi.py4e.com/api/films/3/","https://swapi.py4e.com/api/films/6/","https://swapi.py4e.com/api/films/7/");

    }
}
