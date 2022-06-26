package booking.test;

import booking.model.Booking;
import booking.model.BookingNegativeTests;
import booking.request.DeleteRequest;
import booking.request.GetRequest;
import booking.request.PostRequest;
import booking.request.PutRequest;
import booking.utils.BookingFakers;
import booking.utils.BookingUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import io.restassured.path.json.JsonPath;
import org.codehaus.groovy.control.messages.Message;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static javax.swing.UIManager.getInt;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.assertj.core.api.Assertions.*;

public class BookingsTest extends BookingFakers {

    private static String BOOKING_ID = "bookingid";
    private static String FIRST_NAME = "firstname";
    private static String LAST_NAME = "lastname";
    private static String BOOKING = "booking.";

    @Test
    public void readAllBookings() {
        JsonPath json = GetRequest.readAllBookings();

        List<Integer> bookingIds = json.getList(BOOKING_ID);
        assertThat(bookingIds.size()).isPositive();
        System.out.println(bookingIds);
    }

    @Test
    public void createBookingWithAssertions()  {
        JSONObject bookingDatesJson = Booking.buildBookingDatesJson("2030-01-01", "2031-02-02");
        JSONObject bookingJson = Booking.buildBookingJson("John", "Kovalsky",
                new BigDecimal("1000"), true, bookingDatesJson, "Extra dinner");

        JsonPath json = PostRequest.createBooking(bookingJson);
        assertThat(json.getString(BOOKING + FIRST_NAME)).isEqualTo(bookingJson.getString(FIRST_NAME));
        assertThat(json.getString(BOOKING + LAST_NAME)).isEqualTo(bookingJson.getString(LAST_NAME));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(JsonParser.parseString(String.valueOf(bookingJson))));
        System.out.println(bookingJson);

        int idNo = json.getInt(BOOKING_ID);
        System.out.println("bookingid: " + idNo);
    }
    @Test
    public void createBooking() {
        JSONObject bookingDatesJson = Booking.buildBookingDatesJson(printDate(), printTomorrow());
        JSONObject bookingJson = Booking.buildBookingJson(printFirstNameFaker(), printLastNameFaker(),
                new BigDecimal(BookingFakers.printGenerator()), true, bookingDatesJson, "Extra_supper");

        JsonPath json = PostRequest.createBooking(bookingJson);
        int idNo = json.getInt(BOOKING_ID);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(JsonParser.parseString(String.valueOf(bookingJson))));
        System.out.println("bookingId: " + idNo);
    }

    @Test
    public void createAndPutBooking() {
        JSONObject bookingDatesJson = Booking.buildBookingDatesJson(printDate(), printTomorrow());
        JSONObject bookingJson = Booking.buildBookingJson(printFirstNameFaker(), printLastNameFaker(),
                new BigDecimal(BookingFakers.printGenerator()), true, bookingDatesJson, "Extra_supper");

        JsonPath json = PutRequest.putBooking(bookingJson);
        System.out.println(bookingJson);
    }

    @Test
    public void createAndDeleteBooking(){
        DeleteRequest.deleteBooking();
        DeleteRequest.getBookingById();
        System.out.println("Status code: " + SC_NOT_FOUND);

    }
    @Test
    public void createBookingWithBadJson() {
        JSONObject bookingDatesJson = BookingNegativeTests.buildBookingDatesJson(printDate(), printTomorrow());
        JSONObject bookingJson = BookingNegativeTests.buildBookingBadJson(printFirstNameFaker(),  bookingDatesJson,
                new BigDecimal(BookingFakers.printGenerator()), true, printLastNameFaker());

        JsonPath json = PostRequest.createBookingWithBadJson(bookingJson);
        System.out.println("Status code: " + SC_INTERNAL_SERVER_ERROR);
    }

}