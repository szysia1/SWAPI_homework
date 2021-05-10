package getSWAPI;

import base.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetSpeciesTest extends BaseTest {
    @Test
    public void readAllSpecies() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + SPECIES)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }

    @Test
    public void readOneSpecie() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + SPECIES + "/3")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(json.getString("name")).isEqualTo("Wookiee");
        assertThat(json.getString("classification")).isEqualTo("mammal");
        assertThat(json.getString("designation")).isEqualTo("sentient");
        assertThat(json.getString("average_height")).isEqualTo("210");
        assertThat(json.getString("skin_colors")).isEqualTo("gray");
        assertThat(json.getString("hair_colors")).isEqualTo("black, brown");
        assertThat(json.getString("eye_colors")).isEqualTo("blue, green, yellow, brown, golden, red");
        assertThat(json.getString("average_lifespan")).isEqualTo("400");
        assertThat(json.getString("homeworld")).isEqualTo("https://swapi.py4e.com/api/planets/14/");
        assertThat(json.getString("language")).isEqualTo("Shyriiwook");
        assertThat(json.getList("people")).hasSize(2).containsExactly(
                "https://swapi.py4e.com/api/people/13/",
                "https://swapi.py4e.com/api/people/80/");
        assertThat(json.getList("films")).hasSize(5).contains(
                "https://swapi.py4e.com/api/films/1/",
                "https://swapi.py4e.com/api/films/2/",
                "https://swapi.py4e.com/api/films/3/",
                "https://swapi.py4e.com/api/films/6/",
                "https://swapi.py4e.com/api/films/7/");
        assertThat(json.getString("created")).isEqualTo("2014-12-10T16:44:31.486000Z");
        assertThat(json.getString("edited")).isEqualTo("2014-12-20T21:36:42.142000Z");
        assertThat(json.getString("url")).isEqualTo("https://swapi.py4e.com/api/species/3/");

    }
}
