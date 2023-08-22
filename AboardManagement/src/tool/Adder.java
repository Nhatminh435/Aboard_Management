package tool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * This class provides a method to scan and parse string using conditions in {@linkplain Validation}
 * @author N.Minh
 */
public class Adder {
    private static Scanner sc = new Scanner(System.in);
    
    /**
     * About this method:
     * 
     * A text scanner which can parse string whether
     * it meets conditions and return string.
     * If string does not meet conditions, this method
     * will force user re-enter until it is right.
     * To see the conditions, click this 
     * {@linkplain Validation#checkValidStringWithBlank(java.lang.String) }
     * 
     * @return string
     */
    public static String addStringWithBlank(){
        String str = sc.nextLine().trim();

        if (!Validation.checkValidStringWithBlank(str)) {
            do {                    
                System.out.print("➜ Please enter again: ");
                str = sc.nextLine().trim();
            } while (!Validation.checkValidStringWithBlank(str));
        }
        return str;
    }
    
    /**
     * About this method:
     * 
     * A text scanner which can parse string whether
     * it meets conditions and return string.
     * If string does not meet conditions, this method
     * will force user re-enter until it is right.
     * To see the conditions, click this 
     * {@linkplain Validation#checkValidStringWithBlank(java.lang.String) }
     * 
     * @return string
     */
    public static String addStringWithOutBlank(){
        String str = sc.nextLine().trim();

        if (!Validation.checkValidStringWithoutBlank(str)) {
            do {                    
                System.out.print("➜ Please enter again: ");
                str = sc.nextLine().trim();
            } while (!Validation.checkValidStringWithoutBlank(str));
        }
        return str;
    }
    
    /**
     * About this method:
     * 
     * A text scanner which takes {@linkplain Scanner} as basic and can parse integer number whether
     * it meets conditions and return integer number.
     * If integer number does not meet conditions, this method
     * will force user re-enter until it is right.
     * To see the conditions, click this 
     * {@linkplain Validation#checkPositiveIntegerNumber(java.lang.String)}
     * 
     * @return integer number
     */
    public static int addPositiveIntegerNumber(){
        String number = sc.nextLine().trim();
        if (!Validation.checkPositiveIntegerNumber(number)) {
                do {                    
                    System.out.print("➜ Please enter again: ");
                    number = sc.nextLine().trim();
                } while (!Validation.checkPositiveIntegerNumber(number));
            }
        return Integer.parseInt(number);
    }
    
     /**
     * About this method:
     * 
     * A text scanner which takes {@linkplain Scanner} as basic and can parse date (string) whether
     * it meets conditions and return date.
     * If date does not meet conditions, this method
     * will force user re-enter until it is right.
     * To see the conditions, click this 
     * {@linkplain Validation#checkDateFormat(java.lang.String)}
     * 
     * @return date (string)
     */
    public static String addDate(){
        String date = sc.nextLine().trim();
        if (!Validation.checkDateFormat(date)) {
                do {                    
                    System.out.print("➜ Please enter again: ");
                    date = sc.nextLine();
                } while (!Validation.checkDateFormat(date));
            }
        return date;
    }
    
    /**
     * Return the current date, which is display on your 
     * computer, following the string format [dd/MM/yyyy].
     * @return the current date
     */
    public static String addCurrentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        String date = simpleDateFormat.format(currentDate);
        return date;
    }
    
    /**
     * About this method:
     * 
     * A text scanner which takes {@linkplain Scanner} as basic and can parse double number whether
     * it meets conditions and return double number.
     * If double number does not meet conditions, this method
     * will force user re-enter until it is right.
     * To see the conditions, click this 
     * {@linkplain Validation#checkValidPositiveDoubleNumber(java.lang.String)}
     * 
     * @return double number
     */
    //Return the entered Price of Flower
    public static double addPositiveDoubleNumber() {
        String priceStr = sc.nextLine().trim();          
            if (!Validation.checkPositiveDoubleNumber(priceStr)) {
                do {
                    System.out.print("➜ Please try again: ");
                    priceStr = sc.nextLine().trim();
                } while (!Validation.checkPositiveDoubleNumber(priceStr));
            }
        return Double.parseDouble(priceStr);
    }
    
    /**
     * About this method:
     * 
     * A text scanner which takes {@linkplain Scanner} as basic and can parse phone number whether
     * it meets conditions and return phone number.
     * If double number does not meet conditions, this method
     * will force user re-enter until it is right.
     * To see the conditions, click this 
     * {@linkplain Validation#checkPhone(java.lang.String)}
     * 
     * @return phone
     */
    public static String addPhone(){
        String phone = addStringWithOutBlank();
        if (!Validation.checkPhone(phone)) {
            do {                
                System.out.println("Invalid phone number.");
                System.out.print("➜ Please try again: ");
                phone = addStringWithOutBlank();
            } while (!Validation.checkPhone(phone));
        }
        return phone;
    }
}
