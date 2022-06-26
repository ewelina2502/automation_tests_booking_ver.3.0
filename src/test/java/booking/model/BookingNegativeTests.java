package booking.model;

import org.json.JSONObject;
import java.math.BigDecimal;

public class BookingNegativeTests {

    private BookingNegativeTests() {
    }

    public static JSONObject buildBookingBadJson(String additionalNeeds,
                                                 JSONObject bookingDates, BigDecimal totalPrice, boolean depositPaid,
                                                 String lastName) {

        JSONObject bookingUpdateJson = new JSONObject();
        bookingUpdateJson.put("lastname", lastName);
        bookingUpdateJson.put("totalprice", totalPrice);
        bookingUpdateJson.put("depositpaid", depositPaid);
        bookingUpdateJson.put("bookingdates", bookingDates);
        bookingUpdateJson.put("additionalneeds", additionalNeeds);
        return bookingUpdateJson;
    }

    public static JSONObject buildBookingDatesJson(String checkin, String checkout) {

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        return bookingDates;

    }
}
