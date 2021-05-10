package getSWAPI;

import base.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class GetStarshipsTest extends BaseTest {
    @Test
    public void readAllStarships() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + STARSHIPS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }

    @Test
    public void readOneStarship() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + STARSHIPS + "/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(json.getString("name")).isEqualTo("CR90 corvette");
        assertThat(json.getString("model")).isEqualTo("CR90 corvette");
        assertThat(json.getString("manufacturer")).isEqualTo("Corellian Engineering Corporation");
        assertThat(json.getString("cost_in_credits")).isEqualTo("3500000");
        assertThat(json.getString("length")).isEqualTo("150");
        assertThat(json.getString("max_atmosphering_speed")).isEqualTo("950");
        assertThat(json.getString("crew")).isEqualTo("30-165");
        assertThat(json.getString("passengers")).isEqualTo("600");
        assertThat(json.getString("cargo_capacity")).isEqualTo("3000000");
        assertThat(json.getString("consumables")).isEqualTo("1 year");
        assertThat(json.getString("hyperdrive_rating")).isEqualTo("2.0");
        assertThat(json.getString("MGLT")).isEqualTo("60");
        assertThat(json.getString("starship_class")).isEqualTo("corvette");
        assertThat(json.getList("pilots")).isEmpty();
        assertThat(json.getList("films")).hasSize(3).containsExactly(
                "https://swapi.py4e.com/api/films/1/",
                "https://swapi.py4e.com/api/films/3/",
                "https://swapi.py4e.com/api/films/6/");
        assertThat(json.getString("created")).isEqualTo("2014-12-10T14:20:33.369000Z");
        assertThat(json.getString("edited")).isEqualTo("2014-12-20T21:23:49.867000Z");
        assertThat(json.getString("url")).isEqualTo("https://swapi.py4e.com/api/starships/2/");
    }
}
