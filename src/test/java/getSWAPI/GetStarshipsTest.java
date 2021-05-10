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
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GetStarshipsTest extends BaseTest {
        @Test
        public void readAllStarships(){
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
        public void readOneStarship(){
            Response response = given()
                    .spec(reqSpec)
                    .when()
                    .get(BASE_URL + STARSHIPS + "/2")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .response();

            JsonPath json = response.jsonPath();
            List<String > films = json.getList("films");

            assertThat(json.getString("name")).isEqualTo("CR90 corvette");
            assertThat(films).hasSize(3).contains("https://swapi.py4e.com/api/films/1/","https://swapi.py4e.com/api/films/3/","https://swapi.py4e.com/api/films/6/");
        }
}
