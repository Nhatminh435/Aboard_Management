package viewer;

import tool.Adder;

public class Menu {
    private static final int WIDTH_OF_TABLE = 71;
    private static final String[] YES_NO_QUESTION = {"Yes", "No"};
    
    public static void printTitle(String systemName){
        printDashType1();
        System.out.printf("|" + getAlignMiddleExpression(systemName, WIDTH_OF_TABLE) + "|\n", "", systemName, "");
        printDashType2();
    }
    
    public static void printDashType1(){
        System.out.print(" ");
        for (int i = 0; i < WIDTH_OF_TABLE; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
    }
    
    public static void printDashType2(){
        System.out.print("|");
        for (int i = 0; i < WIDTH_OF_TABLE; i++) {
            System.out.print("-");
        }
        System.out.print("|\n");
    }
    
    public static void printOptionsInOneColumn(String[] options){
        for (int i = 0; i < options.length; i++) {
            System.out.printf("| %d. %-" + (WIDTH_OF_TABLE - 5) + "s |\n", (i+1), options[i]);
        }
        printDashType1();
    }
    
    public static void printOptionsInTwoColumn(String[] options){

        for (int i = 0; i < (options.length/2); i++) {
            System.out.printf("| %d. %-" + ((WIDTH_OF_TABLE - 11) / 2) +"s | %d. %-" + ((WIDTH_OF_TABLE - 11) / 2) +"s |\n", (i+1), options[i], (options.length/2 + i + 1), options[options.length/2 + i]);
        }
        printDashType2();
        System.out.printf("|" + getAlignMiddleExpression((" " + options.length + ". " + (String) options[options.length-1]), WIDTH_OF_TABLE) +"|\n", "", " " + options.length + ". " + options[options.length-1], "");
        printDashType1();
    }
    
    
    public static int getChoice(String systemName, String[] options, int column){
        printTitle(systemName);
        switch(column){
            case 1:
                printOptionsInOneColumn(options);
                break;
            case 2:
                printOptionsInTwoColumn(options);
                break;
            case 3:
                printOptionsInTwoColumn(YES_NO_QUESTION);
            default:
                System.out.println("Invalid request.");
                break;
        }
        System.out.print("Your choice: ");
        int getChoice = Adder.addPositiveIntegerNumber();
        if (!((getChoice >= 1) && (getChoice < (options.length + 1)))) {
            do {                
                System.out.println("Uvalid request.");
                System.out.print("➜ Please enter again: ");
                getChoice = Adder.addPositiveIntegerNumber();
            } while (!((getChoice >= 1) && (getChoice < (options.length + 1))));
        }
        return getChoice;
    }
    
    public static String getAlignMiddleExpression(String string, int interval){
        int space = interval - string.length();
        int leftPadding = space/2;
        int rightPadding = space - leftPadding;
        return "%" + leftPadding + "s%s%" + rightPadding + "s";
    }
}
