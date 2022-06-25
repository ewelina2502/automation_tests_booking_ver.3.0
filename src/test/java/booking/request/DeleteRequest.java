package booking.request;

import booking.model.Booking;
import booking.utils.BookingUrl;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.*;

public class DeleteRequest {


        private DeleteRequest() { }

        private static String BOOKING_ID = "bookingid";

        public static String cookies() {
            return "token=f61416de503d436";
        }

        public static String authorization() {
            return "Basic YWRtaW46cGFzc3dvcmQxMjM=";
        }

        public static String urlDelete = BookingUrl.BASE_URL + BookingUrl.BOOKING + "/" + createToPutBooking();

        public static int createToPutBooking() {
            JSONObject bookingDatesJson = Booking.buildBookingDatesJson("2018-01-01", "2019-01-01");
            JSONObject bookingJson = Booking.buildBookingJson("Krystyna", "Demo",
                    new BigDecimal("1000"), true, bookingDatesJson, "sauna");
            JsonPath json = PostRequest.createBooking(bookingJson);
            System.out.println(bookingJson);
            int idNo = json.getInt(BOOKING_ID);
            System.out.println("bookingId: " + idNo);
            return idNo;

        }
        public static JsonPath deleteBooking() {


            return given()
                    .contentType(JSON)
                    .header("Authorization", authorization())
                    .cookie("token=f61416de503d436")
                    .when()
                    .delete(urlDelete)
                    .then()
                    .statusCode(SC_CREATED)
                    .extract()
                    .response()
                    .jsonPath();
        }
    public static JsonPath getBookingById() {

        return given()
                .contentType(JSON)
                .header("Authorization", authorization())
                .cookie("token=f61416de503d436")
                .when()
                .get(urlDelete)
                .then()
                .statusCode(SC_NOT_FOUND)
                .extract()
                .response()
                .jsonPath();
    }

    }


