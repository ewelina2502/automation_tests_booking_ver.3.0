package booking.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class BookingFakers {
    public static String printDate() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        return ft.format(dNow);
    }

    public static String printTomorrow() {
        LocalDate today = LocalDate.now();
        return String.valueOf(today.plusDays(1));
    }

    public static String printFirstNameFaker() {
        com.github.javafaker.Faker faker = new com.github.javafaker.Faker(new Locale("pl"));
        return faker.name().firstName();
    }
    public static String printLastNameFaker() {
        com.github.javafaker.Faker faker = new com.github.javafaker.Faker(new Locale("pl"));
        return faker.name().lastName();
    }

    public static int printGenerator() {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(9999);
    }


}
