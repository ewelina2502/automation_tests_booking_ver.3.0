package booking.request;

import booking.model.Booking;
import booking.utils.BookingUrl;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import java.math.BigDecimal;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;

public class PutRequest {
    private PutRequest() { }

    private static String BOOKING_ID = "bookingid";

    public static String cookies() {
        return "token=f61416de503d436";
    }

    public static String authorization() {
        return "Basic YWRtaW46cGFzc3dvcmQxMjM=";
    }


    public static int createToPutBooking() {
        JSONObject bookingDatesJson = Booking.buildBookingDatesJson("2018-01-01", "2019-01-01");
        JSONObject bookingJson = Booking.buildBookingJson("Krystyna", "Demo",
                new BigDecimal("1000"), true, bookingDatesJson, "sauna");
        JsonPath json = PostRequest.createBooking(bookingJson);
        int idNo = json.getInt(BOOKING_ID);
        System.out.println("bookingId: " + idNo);
        System.out.println(bookingJson);
        return idNo;

    }
    public static JsonPath putBooking(JSONObject payload) {

        return given()
                .contentType(JSON)
                .header("Authorization", authorization())
                .cookie("token=f61416de503d436")
                .body(payload.toString())
                .when()
                .put(BookingUrl.BASE_URL + BookingUrl.BOOKING + "/" + createToPutBooking())
                .then()
                .statusCode(SC_OK)
                .extract()
                .response()
                .jsonPath();

    }


}
