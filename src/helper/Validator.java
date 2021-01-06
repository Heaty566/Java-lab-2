/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

/**
 *
 * @author heaty
 */
public class Validator {

    //type for checking string
    public static enum StringType {
        ALPHABET,
        ALPHANUM,
        ALPHANUMWITHSPACE,
        STRING,
    }

    //handle checking string with pattern
    public static boolean isAlphabet(String str) {
        return (str.matches("^[a-zA-Z ]*$"));

    }

    public static boolean isAlphaNum(String str) {
        return (str.matches("^[a-zA-Z0-9]*$"));

    }

    public static boolean isAlphaNumWithSpace(String str) {
        return (str.matches("^[a-zA-Z0-9 ]*$"));
    }

    public static boolean isString(String str) {
        return !str.equals("");

    }
    //-----------------------------------------------

    //handle checking number with min and max
    public static boolean maxMinInt(int number, int max, int min) {
        return number <= max && number >= min;
    }

    public static boolean maxMinDouble(double number, double max, double min) {
        return number <= max && number >= min;
    }
    //-----------------------------------------------

    public static boolean isEnum(String str, String[] pattern) {
        boolean isValid = false;
        for (String pattern1 : pattern) {
            if (str.equals(pattern1)) {
                isValid = true;
                break;
            }
        }

        return isValid;
    }
}
