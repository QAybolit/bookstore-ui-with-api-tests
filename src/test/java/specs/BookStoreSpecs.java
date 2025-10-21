package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;

public class BookStoreSpecs {

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .log().all();

    public static RequestSpecification getAuthRequestSpec(String token) {
        return with()
                .filter(withCustomTemplates())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .log().all();
    }

    public static ResponseSpecification getResponseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .log(ALL)
                .expectStatusCode(statusCode)
                .build();
    }
}
