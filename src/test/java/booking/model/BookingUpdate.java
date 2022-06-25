package booking.model;

import org.json.JSONObject;

import java.math.BigDecimal;

public class BookingUpdate {

    private BookingUpdate() {
    }

    public static JSONObject buildBookingPutJson(String firstName, String additionalNeeds,
                                                 JSONObject bookingDates, BigDecimal totalPrice, boolean depositPaid,
                                                 String lastName) {

        JSONObject bookingUpdateJson = new JSONObject();
        bookingUpdateJson.put("firstname", firstName);
        bookingUpdateJson.put("lastname", lastName);
        bookingUpdateJson.put("totalprice", totalPrice);
        bookingUpdateJson.put("depositpaid", depositPaid);
        bookingUpdateJson.put("bookingdates", bookingDates);
        bookingUpdateJson.put("additionalneeds", additionalNeeds);
        return bookingUpdateJson;
    }

    public static JSONObject buildBookingPutDatesJson(String checkin, String checkout) {

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        return bookingDates;

    }
}
