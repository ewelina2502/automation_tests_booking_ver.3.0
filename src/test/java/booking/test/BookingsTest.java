package booking.test;

import booking.model.Booking;
import booking.request.GetRequest;
import booking.request.PostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

public class BookingsTest {

    public static String printFirstNameFaker() {
        com.github.javafaker.Faker faker = new com.github.javafaker.Faker(new Locale("pl"));
        return faker.name().firstName();
    }

    private static String BOOKING_ID = "bookingid";
    private static String FIRST_NAME = printFirstNameFaker();
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
    public void createBooking() throws JsonProcessingException {
        JSONObject bookingDatesJson = Booking.buildBookingDatesJson("2018-01-01", "2019-01-01");
        JSONObject bookingJson = Booking.buildBookingJson(printFirstNameFaker(), "Demo",
                new BigDecimal("1000"), true, bookingDatesJson, "sauna");

        JsonPath json = PostRequest.createBooking(bookingJson);
        assertThat(json.getString(BOOKING + FIRST_NAME)).isEqualTo(bookingJson.getString(FIRST_NAME));
        assertThat(json.getString(BOOKING + LAST_NAME)).isEqualTo(bookingJson.getString(LAST_NAME));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(JsonParser.parseString(String.valueOf(bookingJson))));

//        assertThat(json.getString(BOOKING));
//        System.out.println(bookingJson);
//        int idNo = json.getInt(BOOKING_ID);
//        System.out.println("bookingid: " + idNo);


    }
}