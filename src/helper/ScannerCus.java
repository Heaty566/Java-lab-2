/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author heaty
 */
public class ScannerCus {

    public int getInt(int min, int max, String msg) {
        int input;

        do {
            Scanner sc = new Scanner(System.in);
            //prevent wrong type
            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println(msg);
                System.out.print("Please enter again: ");
            } else {
                input = sc.nextInt();

                //checking for min max
                if (!Validator.maxMinInt(input, max, min)) {
                    System.out.println(msg);
                    System.out.print("Please enter again: ");
                } else {
                    break;
                }
            }

        } while (true);

        return input;
    }

    public boolean getBoolean() {
        MenuHelper menu = new MenuHelper(2);
        menu.add("Yes");
        menu.add("No");
        boolean value = false;
        int choice;
        System.out.println("Please select your command: ");
        do {

            choice = menu.getChoice();

            switch (choice) {
                case 1:
                    value = true;
                    break;
                case 2:
                    value = false;
                    break;

            }

        } while (choice != 1 && choice != 2);
        return value;
    }

    public double getDouble(double min, double max, String msg) {
        double input;
        Scanner sc = new Scanner(System.in);
        do {

            //prevent wrong type
            if (!sc.hasNextDouble()) {
                sc.next();
                System.out.println(msg);
                System.out.print("Please enter again: ");
            } else {
                input = sc.nextDouble();
                //checking for min max
                if (!Validator.maxMinDouble(input, max, min)) {
                    System.out.println(msg);
                    System.out.print("Please enter again: ");
                } else {
                    break;
                }
            }
        } while (true);

        return input;
    }

    public String getMatches(String pattern, String msg) {
        String str;
        Scanner sc = new Scanner(System.in);
        do {

            str = sc.nextLine();
            if (str.equals(pattern)) {
                break;
            } else {
                System.out.println(msg);
                System.out.print("Please enter again: ");
            }

        } while (true);

        return str;
    }

    public static boolean Password_Validation(String password) {

        if (password.length() >= 6) {
            Pattern lowerCharecter = Pattern.compile("[a-z]");
            Pattern upperCharecter = Pattern.compile("[A-Z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

            Matcher hasUpperLetter = upperCharecter.matcher(password);
            Matcher hasLowerLetter = lowerCharecter.matcher(password);
            Matcher hasDigit = digit.matcher(password);
            Matcher hasSpecial = special.matcher(password);

            return hasUpperLetter.find() && hasDigit.find() && hasSpecial.find() && hasLowerLetter.find();

        } else {
            return false;
        }

    }

    public String getString(Validator.StringType type, int min, int max, String msg) {
        String str;
        Scanner sc = new Scanner(System.in);
        do {

            str = sc.nextLine();

            //check for length
            if (str.length() > max || str.length() < min) {
                System.out.println(msg);
                System.out.print("Please enter again: ");

            } else {

                //checking string follow pattern
                if (type.equals(Validator.StringType.ALPHABET)) {

                    if (!Validator.isAlphabet(str)) {
                        System.out.println(msg);
                        System.out.print("Please enter again: ");
                    } else {
                        break;
                    }
                }

                if (type.equals(Validator.StringType.STRING)) {
                    if (!Validator.isString(str)) {
                        System.out.println(msg);
                        System.out.print("Please enter again: ");
                    } else {
                        break;
                    }
                }

                if (type.equals(Validator.StringType.ALPHANUM)) {
                    if (!Validator.isAlphaNum(str)) {
                        System.out.println(msg);
                        System.out.print("Please enter again: ");
                    } else {
                        break;
                    }
                }

                if (type.equals(Validator.StringType.ALPHANUMWITHSPACE)) {
                    if (!Validator.isAlphaNumWithSpace(str)) {
                        System.out.println(msg);
                        System.out.print("Please enter again: ");
                    } else {
                        break;
                    }
                }
            }

        } while (true);

        return str;
    }

    public String getPattern(String[] pattern, String msg) {
        String str;
        Scanner sc = new Scanner(System.in);
        do {

            str = sc.nextLine();
            if (Arrays.asList(pattern).contains(str)) {
                break;
            } else {
                System.out.println(msg);
                System.out.print("Please enter again: ");
                for (String string : pattern) {
                    System.out.println(string);
                }

            }

        } while (true);

        return str;
    }

    public String getPassword(String msg) {
        String str;
        Scanner sc = new Scanner(System.in);
        do {

            str = sc.nextLine();
            if (Password_Validation(str)) {
                break;
            } else {
                System.out.println(msg);
                System.out.print("Please try agian:");
            }

            //check for length
        } while (true);

        return str;
    }

}
