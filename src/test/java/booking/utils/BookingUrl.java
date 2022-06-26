package booking.utils;

import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;

public class BookingUrl {

    public static final String BASE_URL = "https://restful-booker.herokuapp.com/";
    public static final String BOOKING = "booking";
    public static final int STATUS_CODE = (SC_INTERNAL_SERVER_ERROR);
    public static final String MESSAGE = "Internal Server Error";

    private BookingUrl() {
    }
}