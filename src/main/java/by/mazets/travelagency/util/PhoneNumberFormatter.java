package by.mazets.travelagency.util;

public class PhoneNumberFormatter {
    private static final String NUMBER_PLUS_SYMBOL = "+";
    private static final String NUMBER_OPENING_PARENTHESIS_SYMBOL = "(";
    private static final String NUMBER_CLOSING_PARENTHESIS_SYMBOL = ")";
    private static final String NUMBER_DASH_SYMBOL = "-";

    private PhoneNumberFormatter() {
    }


    public static String format(Integer phoneNumber) {
        String number = String.valueOf(phoneNumber);
        number = NUMBER_PLUS_SYMBOL + number.substring(0, 3) + NUMBER_OPENING_PARENTHESIS_SYMBOL + number.substring(3, 5)
                + NUMBER_CLOSING_PARENTHESIS_SYMBOL + number.substring(5, 8) + NUMBER_DASH_SYMBOL + number.substring(8, 10)
                + NUMBER_DASH_SYMBOL + number.substring(10);
        return number;
    }
}