package getSWAPI;

import base.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;


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

        assertThat(json.getString("name")).isEqualTo("Sand Crawler");
        assertThat(json.getString("model")).isEqualTo("Digger Crawler");
        assertThat(json.getString("manufacturer")).isEqualTo("Corellia Mining Corporation");
        assertThat(json.getString("cost_in_credits")).isEqualTo("150000");
        assertThat(json.getString("length")).isEqualTo("36.8 ");
        assertThat(json.getString("max_atmosphering_speed")).isEqualTo("30");
        assertThat(json.getString("crew")).isEqualTo("46");
        assertThat(json.getString("passengers")).isEqualTo("30");
        assertThat(json.getString("cargo_capacity")).isEqualTo("50000");
        assertThat(json.getString("consumables")).isEqualTo("2 months");
        assertThat(json.getString("vehicle_class")).isEqualTo("wheeled");
        assertThat(json.getList("pilots")).isEmpty();
        assertThat(json.getList("films")).hasSize(2).contains(
                "https://swapi.py4e.com/api/films/1/",
                "https://swapi.py4e.com/api/films/5/");
        assertThat(json.getString("created")).isEqualTo("2014-12-10T15:36:25.724000Z");
        assertThat(json.getString("edited")).isEqualTo("2014-12-20T21:30:21.661000Z");
        assertThat(json.getString("url")).isEqualTo("https://swapi.py4e.com/api/vehicles/4/");
    }
}