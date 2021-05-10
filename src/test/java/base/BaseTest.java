package base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    protected final String BASE_URL = "https://swapi.py4e.com/api/";
    protected final String FILMS = "films";
    protected final String PEOPLE = "people";
    protected final String PLANETS = "planets";
    protected final String SPECIES = "species";
    protected final String STARSHIPS = "starships";
    protected final String VEHICLES = "vehicles";

    protected static RequestSpecBuilder reqBuilder;
    protected static RequestSpecification reqSpec;

    @BeforeAll
    public static void beforeAll() {
        reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(ContentType.JSON);
        reqBuilder.addFilter(new AllureRestAssured());

        reqSpec = reqBuilder.build();
    }
}