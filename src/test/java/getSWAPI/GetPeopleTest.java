package getSWAPI;

import base.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetPeopleTest extends BaseTest {
    @Test
    public void readAllPeople() {
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
    public void readOnePerson() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + PEOPLE + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertThat(json.getString("name")).isEqualTo("Luke Skywalker");
        assertThat(json.getString("height")).isEqualTo("172");
        assertThat(json.getString("mass")).isEqualTo("77");
        assertThat(json.getString("hair_color")).isEqualTo("blond");
        assertThat(json.getString("skin_color")).isEqualTo("fair");
        assertThat(json.getString("eye_color")).isEqualTo("blue");
        assertThat(json.getString("birth_year")).isEqualTo("19BBY");
        assertThat(json.getString("gender")).isEqualTo("male");
        assertThat(json.getString("homeworld")).isEqualTo("https://swapi.py4e.com/api/planets/1/");
        assertThat(json.getList("films")).hasSize(5).containsExactly(
                "https://swapi.py4e.com/api/films/1/",
                "https://swapi.py4e.com/api/films/2/",
                "https://swapi.py4e.com/api/films/3/",
                "https://swapi.py4e.com/api/films/6/",
                "https://swapi.py4e.com/api/films/7/");
        assertThat(json.getList("species")).hasSize(1).containsExactly("https://swapi.py4e.com/api/species/1/");
        assertThat(json.getList("vehicles")).hasSize(2).containsExactly(
                "https://swapi.py4e.com/api/vehicles/14/",
                "https://swapi.py4e.com/api/vehicles/30/");
        assertThat(json.getList("starships")).hasSize(2).containsExactly(
                "https://swapi.py4e.com/api/starships/12/",
                "https://swapi.py4e.com/api/starships/22/");
        assertThat(json.getString("created")).isEqualTo("2014-12-09T13:50:51.644000Z");
        assertThat(json.getString("edited")).isEqualTo("2014-12-20T21:17:56.891000Z");
        assertThat(json.getString("url")).isEqualTo("https://swapi.py4e.com/api/people/1/");


    }
}
