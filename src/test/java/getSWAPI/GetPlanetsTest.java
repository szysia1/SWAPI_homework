package getSWAPI;

import base.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetPlanetsTest extends BaseTest {
    @Test
    public void readAllPlanets() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + PLANETS)
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
                .get(BASE_URL + PLANETS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(json.getString("name")).isEqualTo("Tatooine");
        assertThat(json.getString("rotation_period")).isEqualTo("23");
        assertThat(json.getString("orbital_period")).isEqualTo("304");
        assertThat(json.getString("diameter")).isEqualTo("10465");
        assertThat(json.getString("climate")).isEqualTo("arid");
        assertThat(json.getString("gravity")).isEqualTo("1 standard");
        assertThat(json.getString("terrain")).isEqualTo("desert");
        assertThat(json.getString("surface_water")).isEqualTo("1");
        assertThat(json.getString("population")).isEqualTo("200000");
        assertThat(json.getList("residents")).hasSize(10).contains(
                "https://swapi.py4e.com/api/people/1/",
                "https://swapi.py4e.com/api/people/2/",
                "https://swapi.py4e.com/api/people/4/",
                "https://swapi.py4e.com/api/people/6/",
                "https://swapi.py4e.com/api/people/7/",
                "https://swapi.py4e.com/api/people/8/",
                "https://swapi.py4e.com/api/people/9/",
                "https://swapi.py4e.com/api/people/11/",
                "https://swapi.py4e.com/api/people/43/",
                "https://swapi.py4e.com/api/people/62/");
        assertThat(json.getList("films")).hasSize(5).contains(
                "https://swapi.py4e.com/api/films/1/",
                "https://swapi.py4e.com/api/films/3/",
                "https://swapi.py4e.com/api/films/4/",
                "https://swapi.py4e.com/api/films/5/",
                "https://swapi.py4e.com/api/films/6/"
        );
        assertThat(json.getString("created")).isEqualTo("2014-12-09T13:50:49.641000Z");
        assertThat(json.getString("edited")).isEqualTo("2014-12-20T20:58:18.411000Z");
        assertThat(json.getString("url")).isEqualTo("https://swapi.py4e.com/api/planets/1/");
    }
}
