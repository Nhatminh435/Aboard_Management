package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import model.AboardProgram;
import model.Registration;
import model.Student;
import tool.Adder;

/**
 * The class {@code RegistrationManagement} provides methods to 
 * address logic of programs including display menu of main missions
 * of this class, add new registrations, save registrations
 * and load them from file.
 * @author N.Minh
 */
public class RegistrationManagement extends HashMap<String, Registration> {

    private final String REGISTRATION_PATH = "\\output\\Registions.dat";
    private final String REGISTRATION_FORM_PATH = "\\RegistrationForm";
    private StudentManagement studentManagement;
    private AboardProgramManagement aboardProgramManagement;

    /**
     * Default constructor of RegistrationManagement
     */
    public RegistrationManagement() {
    }
    
    /**
     * Constructor of RegistrationManagement
     * Transmit data from studentManagement to get StudentManagement's information
     * Transmit data from aboardProgramManagement to get AboardProgramManagement's information
     * @param studentManagement
     * @param aboardProgramManagement 
     */
    public RegistrationManagement(StudentManagement studentManagement, AboardProgramManagement aboardProgramManagement) {
        this.studentManagement = studentManagement;
        this.aboardProgramManagement = aboardProgramManagement;
    }

    /**
     * Display sub menu of function 3: [Register 
     * a program for students] and perform sub
     * functions such as:
     * Add a new registration
     * Save registrationo the text file(.doc or 
     * .txt) that is named format as “StudentID_ProgramID.doc”.
     * @param filePath
     * @throws Exception 
     */
    public void displayMenu(String filePath) throws Exception{
        try {
            if (addRegistration(filePath)) {
                System.out.println("");
                System.out.println("Save successfully.");
            }
        } catch (Exception e) {
            System.out.println("Display menu failed.");
        }   
    }
    
    /**
     * A text scanner which takes {@linkplain Adder#addStringWithOutBlank()} 
     * as basic and can parse student id whether
     * it exists in StudentProgramManagement and 
     * return student id.
     * If student id does not meet conditions, this method
     * will force user re-enter until it is right.
     * @return student id
     */
    public Student addStudent() {
        System.out.print("Student ID: ");
        String id = Adder.addStringWithOutBlank();
        if (!this.studentManagement.isStudentExist(id)) {
            do {                
                System.out.println("Cannot find student.");
                System.out.print("➜ Please enter again: ");
                id = Adder.addStringWithOutBlank();
            } while (!this.studentManagement.isStudentExist(id));
        }
        return this.studentManagement.get(id);
    }

    /**
     * A text scanner which takes {@linkplain Adder#addStringWithOutBlank() } 
     * as basic and can parse aboard program whether
     * it exists in AboardProgramManagement and 
     * return aboard program id.
     * If aboard program id does not meet conditions, this method
     * will force user re-enter until it is right.
     * @return aboard program ids
     */
    public AboardProgram addRegistProgram() {
        System.out.print("Program ID: ");
        String id = Adder.addStringWithOutBlank();
        if (!this.aboardProgramManagement.isProgramExist(id)) {
            do {                
                System.out.println("Cannot find program.");
                System.out.print("➜ Please enter again: ");
                id = Adder.addStringWithOutBlank();
            } while (!this.aboardProgramManagement.isProgramExist(id));
        }
        return this.aboardProgramManagement.get(id);
    }

    /**
     * Convert string following format [yy/MM/yyyy]
     * to type {@code Date}
     * @param dateString
     * @return date
     * @throws ParseException 
     */
    public Date changeToDate(String dateString) throws ParseException {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = simpleDateFormat.parse(dateString);
            return date;
        } catch (ParseException e) {
            System.out.println("Failed to change to type Date");
            return null;
        }
    }
    
    /**
     * A text scanner which takes {@linkplain Adder#addStringWithOutBlank()} 
     * as basic and can parse registration date whether it is between begin
     * and end of the program’s registration date in AboardProgramManagement 
     * and return registration date.
     * If registration date does not meet conditions, this method
     * will force user re-enter until it is right.
     * @param aboardProgram
     * @return registration date
     * @throws ParseException 
     */
    public String addRegistDate(AboardProgram aboardProgram) throws ParseException {
        try {
            System.out.print("Registration date: ");
            String registDate = Adder.addDate();
            if (!((changeToDate(registDate).after(changeToDate(aboardProgram.getDateRegistrationStart())) && changeToDate(registDate).before(changeToDate(aboardProgram.getDateRegistrationEnd())))
                || registDate.equalsIgnoreCase(aboardProgram.getDateRegistrationStart()) || registDate.equalsIgnoreCase(aboardProgram.getDateRegistrationEnd()))) {
                do {
                    System.out.println("Unvalid date.");
                    System.out.print("➜ Please enter again: ");
                    registDate = Adder.addDate();
                } while (!((changeToDate(registDate).after(changeToDate(aboardProgram.getDateRegistrationStart())) && changeToDate(registDate).before(changeToDate(aboardProgram.getDateRegistrationEnd())))
                    || registDate.equalsIgnoreCase(aboardProgram.getDateRegistrationStart()) || registDate.equalsIgnoreCase(aboardProgram.getDateRegistrationEnd())));
            }
            return registDate;
        } catch (ParseException e) {
            System.out.println("Failed to create regist date.");
            return null;
        }
        
        
    }
    
    /**
     * A text scanner which takes {@linkplain Adder#addStringWithOutBlank()} 
     * as basic and return parent email.
     * If parent email does not meet conditions, this method
     * will force user re-enter until it is right.
     * @return parent email
     */
    public String addParentEmail() {
        System.out.print("Parent email: ");
        String email = Adder.addStringWithOutBlank();
        return email;
    }
    
    /**
     * A text scanner which takes {@linkplain Adder#addStringWithOutBlank()} 
     * as basic and return parent phone.
     * If parent email does not meet conditions, this method
     * will force user re-enter until it is right.
     * @return parent phone
     */
    public String addParentPhone() {
        System.out.print("Parent phone: ");
        String phone = Adder.addPhone();
        return phone;
    }
    
    /**
     * Create and add new {@code Registration} to RegistrationManagement.
     * Then, it will save the registration to the text file(.doc or .txt)
     * that is named format as “StudentID_ProgramID.doc” which has content
     * following the template:
     *                      Aboard Program Registration Form
     * Information Student:
     * Student id:       Student name:
     * Major:            Email:                     Phone:12345         Passport:
     * Address:          Email of the parents:      Phone of the parents:
     * Information of the aboard program:
     * Program’s id:     Program’s name: 
     * Time:       Days:    Location:          Cost:
     * Information of the registration:
     * registration date: 12/4/
     * 
     * Return true if create, add and save registration successfully.
     * @param filePath
     * @return true if create, add and save registration successfully.
     * @throws Exception 
     */
    public boolean addRegistration(String filePath) throws Exception {
        try {
            Student student = addStudent();
            AboardProgram aboardProgram = addRegistProgram();
            String registDate = addRegistDate(aboardProgram);
            String parentEmail = addParentEmail();
            String parentPhone = addParentPhone();
            Registration registration = new Registration(registDate, parentEmail, parentPhone, student, aboardProgram);
            if (!this.containsKey(student.getID()+ "_" + aboardProgram.getID())) {
                this.put(student.getID()+ "_" + aboardProgram.getID(), registration);
                if (saveRegistrations(filePath)) {
                    return true;
                } else {
                    System.out.println("Fail to save.");
                    return false;
                }
            }else{
                System.out.println("Be duplicated. Fail to save.");
                return false;
            }   
        } catch (Exception e) {
            System.out.println("Fail to save.");
            return false;
        }
        
    }
    
    /**
     * Save the registration to the text file(.doc or .txt)
     * that is named format as “StudentID_ProgramID.doc” which has content
     * following the template:
     *                      Aboard Program Registration Form
     * Information Student:
     * Student id:       Student name:
     * Major:            Email:                     Phone:12345         Passport:
     * Address:          Email of the parents:      Phone of the parents:
     * Information of the aboard program:
     * Program’s id:     Program’s name: 
     * Time:       Days:    Location:          Cost:
     * Information of the registration:
     * registration date: 12/4/
     * 
     * @param filePath
     * @return true if saving the registration successfully. 
     * @throws Exception 
     */
    public boolean saveRegistrations(String filePath) throws Exception {
        try {
            for (Registration registration : this.values()) {
                Student student = registration.getStudent();
                AboardProgram aboardProgram = registration.getAboardProgram();
                String fileName = student.getID() + "_" + aboardProgram.getID() + ".txt";
                File file = new File(filePath + REGISTRATION_FORM_PATH + "\\" + fileName);
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(String.format("%42s %-32s %42s\n\n", "", "Aboard Program Registration Form", ""));
                fileWriter.write("Information Student: \n\n");
                fileWriter.write(String.format("%-25s %-30s\n\n", ("Student id: " + student.getID()), ("Student name: " + student.getName())));
                fileWriter.write(String.format("%-25s %-35s %-30s %-25s\n\n", ("Major: " + student.getMajor()), ("Email: " + student.getEmail()), ("Phone: " + student.getPhone()), ("Passport: " + student.getPassport())));
                fileWriter.write(String.format("%-25s %-55s %-55s\n\n", ("Address: " + student.getAddress()), ("Email of the parents: " + registration.getParentEmail()), ("Phone of the parents:" + registration.getParentPhone())));
                fileWriter.write("Information of the aboard program:\n\n");
                fileWriter.write(String.format("%-25s %-30s\n\n", ("Program’s id: " + aboardProgram.getID()), ("Program’s name: " + aboardProgram.getName())));
                fileWriter.write(String.format("%-25s %-10s %-25s %-15s\n\n", ("Time: " + aboardProgram.getTime()), ("Days: " + aboardProgram.getDays()), ("Location: " + aboardProgram.getLocation()), ("Cost: " + String.format("%.0f$", aboardProgram.getCost()))));
                fileWriter.write("Information of the registration:\n\n");
                fileWriter.write("registration date:      " + registration.getRegistDate() + "\n");
                fileWriter.close();
            }

            return true;
        } catch (IOException e) {
            System.out.println("Fail to save.");
            return false;
        }
    }
    
    /**
     * This method will:
     * Saves the collection of registration one by one to the binary file
     * Registration.dat.
     * Return true if it saves successfully.
     * Return false with message "Fail to save registration's information to
     * Registration.dat." if it saves failed.
     * @param filePath
     * @return if saving successfully.
     */
    public boolean saveData(String filePath) {
        try {
            FileOutputStream fileOutput = new FileOutputStream(new File(filePath + REGISTRATION_PATH));
            ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
            for (Registration registration : this.values()) {
                objOutput.writeObject(registration);
            }
            objOutput.flush();
            objOutput.close();
            fileOutput.close();
            return true;
        } catch (FileNotFoundException f) {
            System.out.println("Fail to save registration's information to Registions.dat");
            return false;
        } catch (IOException e) {
            System.out.println("Fail to save registration's information to Registions.dat");
            return false;
        }
    }
    
    /**
     * This method will:
     * 
     * Get registrations' information from the binary file Registration.dat through
     * file path and add them one by one to a RegistrationManagement.
     * Return true with message "Load information from Registration.dat successfully!" if it successes.
     * Return false with message "Fail to load Registration.dat" if it fail.
     * @param filePath
     * @return true if loading successfully
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public boolean loadData(String filePath) throws FileNotFoundException, IOException{
        try {
            FileInputStream fileInput = new FileInputStream(new File(filePath + REGISTRATION_PATH));
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            while (true) {
                try {
                    Object object = objectInput.readObject();
                    if (object != null) {
                        if (object instanceof Registration) {
                            Registration registration = (Registration) object;
                            this.put(registration.getStudent().getID()+ "_" + registration.getAboardProgram().getID(), registration);
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
            System.out.println("Fail to load Registions.dat: " + f.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Fail to load Registions.dat: " + e.getMessage());
            return false;
        }
    }
}
