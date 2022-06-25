package booking.model;

import org.json.JSONObject;

import java.math.BigDecimal;

public class BookingUpdate {

    private BookingUpdate() {
    }

    public static JSONObject buildBookingPutJson(String firstName, String lastName,
                                              BigDecimal totalPrice, boolean depositPaid,
                                              JSONObject bookingDates, String additionalNeeds) {
        JSONObject bookingJson = new JSONObject();
        bookingJson.put("firstname", firstName);
        bookingJson.put("lastname", lastName);
        bookingJson.put("totalprice", totalPrice);
        bookingJson.put("depositpaid", depositPaid);
        bookingJson.put("bookingdates", bookingDates);
        bookingJson.put("additionalneeds", additionalNeeds);
        return bookingJson;
    }

    public static JSONObject buildBookingPutDatesJson(String checkin, String checkout) {

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        return bookingDates;

    }
}
