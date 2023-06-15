package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }



    @Value
    public static class CardInfo {
        private String cardNumber;

    }

    public static CardInfo getFirstCardNumber() {
        return new CardInfo("4444 4444 4444 4441");
    }

    public static CardInfo getSecondCardNumber() {
        return new CardInfo("4444 4444 4444 4442");
    }
    public static CardInfo randomInvalidCardNumber() {
        return new CardInfo(faker.business().creditCardNumber());
    }

    public static String generateValidMonth(int addMonths) {
        return LocalDate.now().plusMonths(addMonths).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateValidYear(int addYears) {
        return LocalDate.now().plusYears(addYears).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateInvalidMonth() {
        List<String> months = Arrays.asList("13", "14", "15", "16", "17", "18", "19", "20", "21", "31", "55", "64", "88");
        Random random = new Random();
        int randomMonth = random.nextInt(months.size());
        String randomElement = months.get(randomMonth);
        return randomElement;
    }

    public static String generateInValidYear(int minusYears) {
        return LocalDate.now().minusYears(minusYears).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getRandomValidUser() {
        String login = faker.name().fullName();
        return login;
    }

    public static String generateRandomCVC() {
        int value = ThreadLocalRandom.current().nextInt(100, 1000);
        String cvc = String.valueOf(value);
        return cvc;
    }
    public static String wrongCardNumber(){
        String trashCardNumber = ";помтва№!*";
        return trashCardNumber;
    }
    public static String wrongMonth(){
        String trashMonth = "dfыл";
        return trashMonth;
    }
    public static String wrongYear(){
        String trashYear = "yuшд";
        return trashYear;
    }
    public static String wrongUser(){
        String trashUser = "34прое89*";
        return trashUser;
    }
    public static String wrongCVC(){
        String trashCVC = "GП*";
        return trashCVC;
    }
    public static String invalidTooLongCVC(){
        String tooLongCVC = "5678";
        return tooLongCVC;
    }
    public static String zeroMonth(){
        String zeroMonth = "00";
        return zeroMonth;
    }
    public static String zeroCVC(){
        String zeroCVC = "000";
        return zeroCVC;
    }
}
