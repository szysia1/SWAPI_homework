package getSWAPI;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;

public class GetVehiclesTest extends BaseTest {
    @Test
    public void readAllVehicles() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + VEHICLES)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }

    @Test
    public void readOneVehicle() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + VEHICLES + "/4")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> films = json.getList("films");

        assertThat(json.getString("name")).isEqualTo("Sand Crawler");
        assertThat(films).hasSize(2).contains("https://swapi.py4e.com/api/films/1/", "https://swapi.py4e.com/api/films/5/");

    }
}