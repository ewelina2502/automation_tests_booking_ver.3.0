package booking.request;

import booking.utils.BookingUrl;
import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assert;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;

public class PostRequest {

    private PostRequest() { }

    public static JsonPath createBooking(JSONObject payload) {

        return given()
                .contentType(JSON)
                .body(payload.toString())
                .when()
                .post(BookingUrl.BASE_URL + BookingUrl.BOOKING)
                .then()
                .statusCode(SC_OK)
                .extract()
                .response()
                .jsonPath();

    }
}