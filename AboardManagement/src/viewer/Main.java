/*
    Xem enum hoặc range cho phương thức checkTime trong aboard management
    Bỏ những cái không thừa như 
    Xem lại hàm saveData 
*/

package viewer;

import controller.AboardProgramManagement;
import controller.StudentManagement;
import controller.RegistrationManagement;
import controller.ReportManagement;
import java.io.IOException;

public class Main {
    private static final String FILE_SRC_PATH = System.getProperty("user.dir") + "\\src";
    private static final String[] OPTIONS = {"Manage aboard programs", "Manage students", "Register a program for a student", "Report", "Exit program"};
    
    public static void main(String[] args) throws IOException, Exception {
        
        AboardProgramManagement aboardProgramManagement = new AboardProgramManagement();
        aboardProgramManagement.loadData(FILE_SRC_PATH);
        StudentManagement studentManagement = new StudentManagement();
        studentManagement.loadData(FILE_SRC_PATH);
        RegistrationManagement registrationManagement = new RegistrationManagement();
        ReportManagement reportManagement = new ReportManagement();
        
        int getChoice = 1;
        do {            
            getChoice = Menu.getChoice("Aboard Programs Management System", OPTIONS, 1);
            switch(getChoice){
                case 1:
                    aboardProgramManagement.displayMenu();
                    break;
                case 2:
                    studentManagement.displayMenu();
                    break;
                case 3:
                    registrationManagement = new RegistrationManagement(studentManagement, aboardProgramManagement);
                    registrationManagement.loadData(FILE_SRC_PATH);
                    registrationManagement.displayMenu(FILE_SRC_PATH);
                    break;
                case 4:
                    reportManagement = new ReportManagement(studentManagement, aboardProgramManagement);
                    reportManagement.displayMenu(FILE_SRC_PATH);
                    break;
                case 5:
                    if (aboardProgramManagement.saveData(FILE_SRC_PATH) && studentManagement.saveData(FILE_SRC_PATH) && registrationManagement.saveData(FILE_SRC_PATH)) {
                        System.out.println("Save successfully.");
                    }
                    break;
            }
        } while ((getChoice >= 1) && (getChoice < OPTIONS.length));
        System.out.println("===================================END PROGRAM==================================");
        System.out.println("                                    GOOD BYE                                    ");
    }
}
