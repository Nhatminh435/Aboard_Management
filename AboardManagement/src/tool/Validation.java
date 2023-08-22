package tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides a method to check if a string matches certain conditions.
 * @author N.Minh
 */
public class Validation {
    private static final String SPECIAL_CHARACTER_EXPRESSION = "[!@#$%^&*(),'?\":{}|<>]";
    private static final String BLANK_EXPRESSION = "\\s";
    private static final String NON_DIGIT_EXPRESSION = "\\D";
    private static final String DIGIT_EXPRESSION = "\\d+";
    private static final String DOUBLE_NUMBER_EXPRESSION = "^\\d*\\.?\\d+$";
    private static final String LETTER_EXPRESSION = "\\s";
    private static final String DATE_FORMAT_PATTERN = "dd/MM/yyyy";
    private static final String DATE_FORMAT= "[0-9]{2}/[0-1][0-9]/[1-9][0-9]{3}";
    
    /**
     * Return true if string has special character(s).
     *
     * @param str
     * @return true if string has special character(s).
     */
    public static boolean checkSpecialCharacter(String str) {
        String regex = SPECIAL_CHARACTER_EXPRESSION;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.find();
    }
    
    /**
     * Return true if string has blank(s) inside.
     * @param str input string
     * @return Return true if string has blank(s) inside.
     */
    public static boolean checkHavingBlankInside(String str){
        String regex = BLANK_EXPRESSION;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
    
    /**
     * Return true if imported string is not empty, does not have special
     * character(s).
     *
     * @param str
     * @return true if imported string is not empty, does not have special
     * character(s).
     */
    public static boolean checkValidStringWithBlank(String str) {
        if (str.isEmpty()) {
            System.out.println("Cannot left blank.");
        }
        return (!str.isEmpty());
    }
    
    /**
     * Return true if imported string is not empty and is
     * a positive integer number, does not have special character(s) or blank inside.
     *
     * @param str
     * @return true if imported string is not empty is an integer number, does not have special character(s) or blank inside.
     */
    public static boolean checkPositiveIntegerNumber(String str) {
        Pattern pattern1 = Pattern.compile(NON_DIGIT_EXPRESSION);
        Matcher matcher1 = pattern1.matcher(str);
        Pattern pattern2 = Pattern.compile(DIGIT_EXPRESSION);
        Matcher matcher2 = pattern2.matcher(str);
        displayErrorForNumber("integer", str);
        return (!matcher1.find() && matcher2.find());
    }

    /**
     * Return true if imported string is not empty, has
     * only numbers and starts with "0"
     * @param phone
     * @return phone 
     */
    public static boolean checkPhone(String phone){
        Pattern pattern = Pattern.compile(NON_DIGIT_EXPRESSION);
        Matcher matcher = pattern.matcher(phone);
        return (!matcher.find() && (phone.startsWith("0")));
    }
    
    /**
     * Return true if imported string is not empty and is
     * a positive  double number, does not have special character(s) or blank inside.
     *
     * @param str
     * @return true if imported string is not empty and is an double number, does not have special character(s) or blank inside.
     * character(s).
     */
    
    public static boolean checkPositiveDoubleNumber(String str) {
        Pattern pattern1 = Pattern.compile(DOUBLE_NUMBER_EXPRESSION);
        Matcher matcher1 = pattern1.matcher(str);
        Pattern pattern2 = Pattern.compile(LETTER_EXPRESSION);
        Matcher matcher2 = pattern2.matcher(str);
        displayErrorForNumber("double", str);
        return (matcher1.find() && !matcher2.find());
    }

    /**
     * Return true if imported string is not empty, does not have special
     * character(s) and blank inside.
     *
     * @param str
     * @return true if imported string is not empty, does not have special
     * character(s) and blank inside.
     */
    public static boolean checkValidStringWithoutBlank(String str) {
        if (checkHavingBlankInside(str)) {
            System.out.println("Cannot contain blank inside.");
        }
        return (checkValidStringWithBlank(str) && !checkHavingBlankInside(str));
    }

    /**
     * Return true if the imported phone number 
     * meets the {@linkplain Validation#checkValidStringWithoutBlank(java.lang.String)}
     * conditions in  and starts with "0".
     * @param PhoneNumber
     * @return true if the imported phone number meets the conditions in ... and starts with "0".
     */
    public static boolean checkValidPhoneNumber(String PhoneNumber) {
        if (!PhoneNumber.startsWith("0")) {
            System.out.println("Invalid phone number.");
        }
        return (checkPositiveIntegerNumber(PhoneNumber) && PhoneNumber.startsWith("0"));
    }

    /**
     * Return true if the imported date is valid with
     * formate [dd/MM/yyyy] and accuracy of date field.
     * @param date
     * @return 
     */
    public static boolean checkDateFormat(String date) {
        SimpleDateFormat dateFormate = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        dateFormate.setLenient(false);                          //prevent accepting invalid dateformat.
        
        try {
            if(date.matches(DATE_FORMAT)){
                dateFormate.parse(date);
                return true;
            }else{
                System.out.println("Wrong date format.");
                return false;
            }
        } catch (ParseException e) {
            System.out.println("Wrong date format.");
            return false;
        }
    }
    /**
     * Display the error of the inputing string:
     * 
     * If string is empty, the method will display "Cannot be left blank.".
     * If string has special character(s), the method will display "Number cannot have special character.".
     * If string has blank inside, the method will display "Number must not contain blank.".
     * If string has blank inside, the method will display
     * If string has "-", the method will display "Must be positive.".
     * If string has character, the method will display "Cannot have characters".
     * Enter "integer" or "double" to choose the function.
     * 
     * @param role 
     * @param str 
     */
    public static void displayErrorForNumber(String role, String str){
        if (str.isEmpty()) {
            System.out.println("Cannot be left blank.");
        }
        if (checkSpecialCharacter(str) || str.contains("/")) {
            System.out.println("Number cannot have special character.");
        }
        if (str.contains(" ")) {
            System.out.println("Number must not contain blank.");
        }
        if (str.contains("-")) {
            System.out.println("Must be positive.");
        }
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(str);
        if (role.equals("integer")) {
            if (matcher.find() || checkSpecialCharacter(str) || str.contains(".")) {
            System.out.println("Cannot have characters");
            }
        }
        if (role.equals("double")) {
            if (matcher.find() || checkSpecialCharacter(str)) {
            System.out.println("Cannot have characters");
            }
        }
    }
}
