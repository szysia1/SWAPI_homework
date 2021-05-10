package getSWAPI;

import base.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetFilmsTest extends BaseTest {

    @Test
    public void readAllFilms() {
        Response response = given()
                .spec(reqSpec)
                .when()
                .get(BASE_URL + FILMS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }

    @Test
    public void readOneFilm() {
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
        assertThat(json.getInt("episode_id")).isEqualTo(4);
        assertThat(json.getString("opening_crawl")).isEqualTo("It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy....");
        assertThat(json.getString("director")).isEqualTo("George Lucas");
        assertThat(json.getString("producer")).isEqualTo("Gary Kurtz, Rick McCallum");
        assertThat(json.getString("release_date")).isEqualTo("1977-05-25");
        assertThat(json.getList("characters")).hasSize(18).containsExactly(
                "https://swapi.py4e.com/api/people/1/",
                "https://swapi.py4e.com/api/people/2/",
                "https://swapi.py4e.com/api/people/3/",
                "https://swapi.py4e.com/api/people/4/",
                "https://swapi.py4e.com/api/people/5/",
                "https://swapi.py4e.com/api/people/6/",
                "https://swapi.py4e.com/api/people/7/",
                "https://swapi.py4e.com/api/people/8/",
                "https://swapi.py4e.com/api/people/9/",
                "https://swapi.py4e.com/api/people/10/",
                "https://swapi.py4e.com/api/people/12/",
                "https://swapi.py4e.com/api/people/13/",
                "https://swapi.py4e.com/api/people/14/",
                "https://swapi.py4e.com/api/people/15/",
                "https://swapi.py4e.com/api/people/16/",
                "https://swapi.py4e.com/api/people/18/",
                "https://swapi.py4e.com/api/people/19/",
                "https://swapi.py4e.com/api/people/81/"
        );
        assertThat(json.getList("planets")).hasSize(3).containsExactly(
                "https://swapi.py4e.com/api/planets/1/",
                "https://swapi.py4e.com/api/planets/2/",
                "https://swapi.py4e.com/api/planets/3/"
        );
        assertThat(json.getList("starships")).hasSize(8).containsExactly(
                "https://swapi.py4e.com/api/starships/2/",
                "https://swapi.py4e.com/api/starships/3/",
                "https://swapi.py4e.com/api/starships/5/",
                "https://swapi.py4e.com/api/starships/9/",
                "https://swapi.py4e.com/api/starships/10/",
                "https://swapi.py4e.com/api/starships/11/",
                "https://swapi.py4e.com/api/starships/12/",
                "https://swapi.py4e.com/api/starships/13/"
        );
        assertThat(json.getList("vehicles")).hasSize(4).containsExactly(
                "https://swapi.py4e.com/api/vehicles/4/",
                "https://swapi.py4e.com/api/vehicles/6/",
                "https://swapi.py4e.com/api/vehicles/7/",
                "https://swapi.py4e.com/api/vehicles/8/"
        );
        assertThat(json.getList("species")).hasSize(5).containsExactly(
                "https://swapi.py4e.com/api/species/1/",
                "https://swapi.py4e.com/api/species/2/",
                "https://swapi.py4e.com/api/species/3/",
                "https://swapi.py4e.com/api/species/4/",
                "https://swapi.py4e.com/api/species/5/"
        );
        assertThat(json.getString("created")).isEqualTo("2014-12-10T14:23:31.880000Z");
        assertThat(json.getString("edited")).isEqualTo("2014-12-20T19:49:45.256000Z");
        assertThat(json.getString("url")).isEqualTo("https://swapi.py4e.com/api/films/1/");

    }
}