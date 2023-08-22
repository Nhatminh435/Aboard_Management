package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import model.Student;
import tool.Adder;
import viewer.Menu;

/**
 * The class {@code StudentManagement} provides methods to 
 * address logic of programs including display menu of main missions
 * of this class, add new aboard program, edit, search, save student 
 * and load them from file.
 * @author N.Minh
 */
public class StudentManagement extends HashMap<String, Student>{
    private final String STUDENT_PATH = "\\output\\Students.dat";
    private final String[] MENU_OPTIONS = {"Displays all students", "Add a new student", "Edit information a student by id", "Back to main menu"};
    private final String[] EDIT_STUDENT_OPTIONS = {"Name", "Major", "Email", "Phone number", "Passport", "Address", "Exit"};
    
    /**
     * Display sub menu of function 2: [Manage students] 
     * and perform sub functions such as:
     * Displays all students, 
     * Add a new student, 
     * Edit information a student by id, 
     */
    public void displayMenu(){
        int getChoice = 1;
        do {            
            getChoice = Menu.getChoice("Manage students", MENU_OPTIONS, 1);
            switch(getChoice){
                case 1:
                    displayStudent();
                    break;
                case 2:
                    if (addStudent()) {
                        System.out.println("Add successfully!");
                    }
                    break;
                case 3:
                   if (editStudent()) {
                        System.out.println("Edit successfully!");
                    }
                    break;
                case 4:
                    break;
            }
        } while ((getChoice > 0) && (getChoice < MENU_OPTIONS.length));
    }
    
    /**
     * Displays all students following elements: ID, name, major, email, phone, passport, address
     */
    public void displayStudent(){
            System.out.println(" -----------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %5s | %20s | %6s | %31s | %15s | %8s | %20s |\n", "ID", "NAME", "MAJOR", "EMAIL", "PHONE", "PASSPORT", "ADDRESS");
        for (Student student : this.values()) {
            System.out.println("|-----------------------------------------------------------------------------------------------------------------------------|"); 
            System.out.printf("| %5s | %20s | %6s | %31s | %15s | %8s | %20s |\n", student.getID(), student.getName(), student.getMajor(), student.getEmail(), student.getPhone(), student.getPassport(), student.getAddress());
        }
            System.out.println(" -----------------------------------------------------------------------------------------------------------------------------");
    }
    
    /**
     * Return true if student id exists in StudentManagement
     * @param studentID
     * @return true if student id exists in StudentManagement
     */
    public boolean isStudentExist(String studentID){
        if (this.containsKey(studentID)) {
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * A text scanner which takes {@linkplain Adder#addStringWithOutBlank()} 
     * as basic and can parse student id whether
     * it does not exist in StudentProgramManagement and 
     * return new student id.
     * If student id does not meet conditions, this method
     * will force user re-enter until it is right.
     * @return new student id
     */
    public String addNewStudentID(){
        String studentID = Adder.addStringWithOutBlank();
        if (isStudentExist(studentID)) {
            do {                
                System.out.println("ID exist.");
                System.out.print("➜ Please enter again: ");
                studentID = Adder.addStringWithOutBlank();
            } while (isStudentExist(studentID));
        }
        return studentID;
    }
    
    /**
     * A text scanner which takes {@linkplain Adder#addStringWithOutBlank()}
     * as basic and can parse major whether
     * it is SE,SB,GD or MC and return major.
     * If major does not meet conditions, this method
     * will force user re-enter until it is right.
     * @return major
     */
    public String addMajor(){
        String major = Adder.addStringWithOutBlank();
        if (!(major.equalsIgnoreCase("SE") || major.equalsIgnoreCase("SB") || major.equalsIgnoreCase("GD") || major.equalsIgnoreCase("MC"))) {
            do {                
                System.out.println("Only accept as: SE,SB,GD,MC");
                System.out.print("➜ Please enter again: ");
                major = Adder.addStringWithOutBlank();
            } while (!(major.equalsIgnoreCase("SE") || major.equalsIgnoreCase("SB") || major.equalsIgnoreCase("GD") || major.equalsIgnoreCase("MC")));
        }
        return major;
    }
    
    /**
     * A text scanner which takes {@linkplain Adder#addStringWithOutBlank()}
     * as basic and can parse email whether
     * it has tail “@fpt.edu.vn” and return email.
     * If email does not meet conditions, this method
     * will force user re-enter until it is right.
     * @return email
     */
    public String addEmail(){
        String email = Adder.addStringWithOutBlank();
        if (!email.endsWith("@fpt.edu.vn")) {
            do {                
                System.out.println("Must contains “@fpt.edu.vn”");
                System.out.print("➜ Please enter again: ");
                email = Adder.addStringWithOutBlank();
            } while (!email.endsWith("@fpt.edu.vn"));
        }
        return email;
    }
    
    /**
     * Create and add new {@code Student} to StudentManagement
     * Return true if create and add student successfully.
     * @return true if create and add student successfully.
     */
    public boolean addStudent(){
        System.out.print("Enter student ID: ");
        String ID = addNewStudentID();
        System.out.print("Enter student's name: ");
        String name = Adder.addStringWithBlank();
        System.out.print("Enter student's major: ");
        String major = addMajor();
        System.out.print("Enter student's email: ");
        String email = addEmail();
        System.out.print("Enter student's phone number: ");
        String phone = Adder.addPhone();
        System.out.print("Enter student's passport: ");
        String passport = Adder.addStringWithOutBlank();
        System.out.print("Enter student's address: ");
        String address = Adder.addStringWithBlank();
        Student student = new Student(ID, name, major, email, phone, passport, address);
        this.put(student.getID(), student);
        return true;
    }
    
    /**
     * Search student by inputting student id
     * and edit its name, major, email, phone, passport, address
     * Return true if edit student's information successfully.
     * @return true if edit student's information successfully
     */
    public boolean editStudent(){
        System.out.print("Enter the student ID: ");
        String editStudentID = Adder.addStringWithOutBlank();
        if (isStudentExist(editStudentID)) {
            for (Student student : this.values()) {
                if (student.getID().equalsIgnoreCase(editStudentID)) {
                    int getChoice = Menu.getChoice("Choose field to edit [" + student.getName() + "]", EDIT_STUDENT_OPTIONS, 2);
                    switch(getChoice){
                        case 1:
                            System.out.print("Enter student's name");
                            String name = Adder.addStringWithBlank();
                            student.setName(name);
                            break;
                        case 2:
                            System.out.print("Enter student's major: ");
                            String major = addMajor();
                            student.setMajor(major);
                            break;
                        case 3:
                            System.out.print("Enter student's email: ");
                            String email = addEmail();
                            student.setEmail(email);
                            break;
                        case 4:
                            System.out.print("Enter student's phone number: ");
                            String phone = Adder.addPhone();
                            student.setPhone(phone);
                            break;
                        case 5:
                            System.out.print("Enter student's passport: ");
                            String passport = Adder.addStringWithOutBlank();
                            student.setPassport(passport);
                            break;
                        case 6:
                            System.out.print("Enter student's address: ");
                            String address = Adder.addStringWithBlank();
                            student.setAddress(address);
                            break;
                      default:
                            break;
                    }   
                }
            }
            return true;
        }else{
            System.out.println("Student does not exist.");
            return false;
        } 
    }
    
    /**
     * This method will:
     * Saves the collection of student one by one to the binary file
     * Student.dat.
     * Return true if it saves successfully.
     * Return false with message "Fail to save student's information to
     * Student.dat." if it saves failed.
     * @param filePath
     * @return if saving successfully.
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public boolean saveData(String filePath) throws FileNotFoundException, IOException{
        try {
            FileOutputStream fileOutput = new FileOutputStream(new File(filePath + STUDENT_PATH));
            ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
            for (Student student : this.values()) {
                objOutput.writeObject(student);
            }
            objOutput.flush();
            objOutput.close();
            fileOutput.close();
            return true;
        } catch (FileNotFoundException f) {
            System.out.println("Fail to save student's information to Students.dat");
            return false;
        } catch (IOException e) {
            System.out.println("Fail to save student's information to Students.dat");
            return false;
        }
    }
    
    /**
     * This method will:
     * 
     * Get students' information from the binary file Student.dat through
     * file path and add them one by one to a StudentManagement.
     * Return true with message "Load information from Student.dat successfully!" if it successes.
     * Return false with message "Fail to load Student.dat" if it fail.
     * 
     * @param filePath
     * @return true if loading successfully
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public boolean loadData(String filePath) throws FileNotFoundException, IOException{
        try {
            FileInputStream fileInput = new FileInputStream(new File(filePath + STUDENT_PATH));
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            while (true) {
                try {
                    Object object = objectInput.readObject();
                    if (object != null) {
                        if (object instanceof Student) {
                            Student student = (Student) object;
                            this.put(student.getID(), student);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    break;
                }
            }
            objectInput.close();
            fileInput.close();
            return true;
        } catch (FileNotFoundException f) {
            System.out.println("Fail to load Students.dat: " + f.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Fail to load Students.dat: " + e.getMessage());
            return false;
        }
    }
}
