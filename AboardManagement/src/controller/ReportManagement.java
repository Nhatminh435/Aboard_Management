/*
    Xem enum hoặc range cho phương 
*/

package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import model.AboardProgram;
import model.Student;
import tool.Adder;
import viewer.Menu;

public class ReportManagement {
    private final String REGISTRATION_FORM_PATH = "\\RegistrationForm";
    private final String FILE_EXTENSION = "\\.(doc|txt)$"; 
    private final String[] options  = {"Print out the registration by student’s id", "Print out the students that registered more than 2 programs", "Count students that registered the program", "Back to main menu"};
    private StudentManagement studentManagement;
    private AboardProgramManagement aboardProgramManagement;
    
    /**
     * Default constructor of ReportManagement
     */
    public ReportManagement() {
    }
    
    /**
     * Constructor of ReportManagement
     * Transmit data from studentManagement to get StudentManagement's information
     * Transmit data from aboardProgramManagement to get AboardProgramManagement's information
     * @param studentManagement
     * @param aboardProgramManagement 
     */
    public ReportManagement(StudentManagement studentManagement, AboardProgramManagement aboardProgramManagement) {
        this.studentManagement = studentManagement;
        this.aboardProgramManagement = aboardProgramManagement;
    }
    
    /**
     * Display sub menu of function 3: [Register 
     * a program for students] and perform sub
     * functions such as:
     * Print out the registration by student’s id
     * Print out the students that registered more than 2 programs
     * Count students that registered the program
     * @param filePath
     * @throws FileNotFoundException 
     */
    public void displayMenu(String filePath) throws Exception{
        try {
            int getChoice = 1;
            do {            
                getChoice = Menu.getChoice("Report", options, 1);
                switch(getChoice){
                    case 1:
                        printOutRegistration(filePath);
                        break;
                    case 2:
                        printStudentRegisteredMoreThanTwoProgram(filePath);
                        break;
                    case 3:
                        printNumberOfStudentRegistProgram(filePath);
                        break;
                    case 4:
                        break;
                }
            } while ((getChoice > 0) && (getChoice < options.length));
        } catch (Exception e) {
            System.out.println("Failed to display sub menu");
        }
        
    }
    
    /**
     * Return array of files which appear in folder 
     * by inputting filePath of folder
     * @param filePath
     * @return array of files which appear in folder 
     * @throws java.lang.Exception
     */
    private ArrayList<File> getFile(String filePath) throws Exception{
        try {
            ArrayList<File> fileList = new ArrayList<File>();
            File directory = new File(filePath + REGISTRATION_FORM_PATH);
            if (directory.isDirectory()) {
                File[] files = directory.listFiles();
                for (File file : files) {
                    fileList.add(file);
                }
            }
        return fileList;
        } catch (Exception e) {
            return null;
        }
        
    }
    
    /**
     * Return array of files which appear in folder
     * and has needed student id by inputting filePath of
     * folder and student id
     * @param studentID
     * @param filePath
     * @return array of files which appear in folder and has needed student id
     * @throws java.lang.Exception
     */
    private ArrayList<File> getFileByStudentID(String studentID, String filePath) throws Exception{
        ArrayList<File> studentFile = new ArrayList<File>();
        for(File file : getFile(filePath)){
            if (file.getName().substring(0, file.getName().indexOf("_")).equalsIgnoreCase(studentID)) {
                studentFile.add(file);
            }
        }
        return studentFile;
    }
    
    /**
     * Print out the information of a file.
     * @param file
     * @throws FileNotFoundException 
     */
    private void printInformationOfFile(File file) throws FileNotFoundException{
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {            
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to print " + file.getName());
        }
    }
    
    /**
     * Print out the registrations of student by inputting student's id
     * @param filePath 
     * @throws java.lang.Exception
     */
    public void printOutRegistration(String filePath) throws Exception{
        try {
            System.out.print("Student ID: ");
            String studentID = Adder.addStringWithOutBlank();
            if (this.studentManagement.isStudentExist(studentID)) {
                if (!getFileByStudentID(studentID, filePath).isEmpty()) {
                    for(File file : getFileByStudentID(studentID, filePath)){
                        printInformationOfFile(file);
                    }
                }else{
                    System.out.println(studentID + " does not regist any program.");
                }
            }else{
                System.out.println("Student does not exist.");
            }
        } catch (Exception e) {
            System.out.println("Failed to print out the registration ");
        }
        
    } 
    
    /**
     * Return the array of file names which appear 
     * in folder and has tail ".doc" or .txt"
     * @param filePath
     * @return the array of file names 
     * @throws java.lang.Exception
     */
    private ArrayList<String> getFileName(String filePath) throws Exception{
        try {
            ArrayList<String> nameFileList = new ArrayList<String>();
            for (File file : getFile(filePath)) {
                if (file.isFile() && (file.getName().endsWith(".doc") || file.getName().endsWith(".txt"))) {
                    nameFileList.add(file.getName().replaceAll(FILE_EXTENSION, ""));
                }
            }
            return nameFileList;
        } catch (Exception e) {
            System.out.println("Failed to get file name.");
            return null;
        }
    }
    
    /**
     * Separate the student id after getting file names.
     * @param filePath
     * @return array of the student id after getting file names
     * @throws java.lang.Exception
     */
    private ArrayList<String> getListOfStudentID(String filePath) throws Exception{
        try {
            ArrayList<String> studentIDLIst = new ArrayList<String>();
            for (String fileName : getFileName(filePath)) {
                studentIDLIst.add(fileName.substring(0, fileName.indexOf("_")));
            }
            return studentIDLIst;
        } catch (Exception e) {
            System.out.println("Failed to get student id");
            return null;
        }
    }
    
    /**
     * Delete duplicated student id after getting student id from file.
     * @param filePath
     * @return array of student id which does not have duplicated values.
     * @throws java.lang.Exception
     */
    private HashSet<String> deleteStudentIDDuplicate(String filePath) throws Exception{
        try {
            HashSet<String> studentSet = new HashSet<String>(getListOfStudentID(filePath));
            return studentSet;
        } catch (Exception e) {
            System.out.println("Failed to delete duplicated id.");
            return null;
        }
    }
    
    /**
     * Count the number of registration of an student
     * by inputting student id.
     * @param findStudentID
     * @param filePath
     * @return number of registration of an student
     * @throws java.lang.Exception
     */
    public int countRegistrationOfStudent(String findStudentID, String filePath) throws Exception{
        int count = 0;
        try {
            for (String studentID : getListOfStudentID(filePath)) {
                if (studentID.equalsIgnoreCase(findStudentID)) {
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println("There is an error.");
        }
        return count;
    }
    
    /**
     * Print out the students that registered more than 2 programs
     * following the elements: ID, name, major, email, phone, passport, address.
     * @param filePath 
     * @throws java.lang.Exception
     */
    public void printStudentRegisteredMoreThanTwoProgram(String filePath) throws Exception{
        try {
            System.out.println(" -----------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %5s | %20s | %6s | %31s | %15s | %8s | %20s |\n", "ID", "NAME", "MAJOR", "EMAIL", "PHONE", "PASSPORT", "ADDRESS");
            for (String studentID : deleteStudentIDDuplicate(filePath)) {
                if (countRegistrationOfStudent(studentID, filePath) > 2) {
                    Student student = this.studentManagement.get(studentID);
                    System.out.println("|-----------------------------------------------------------------------------------------------------------------------------|"); 
                    System.out.printf("| %5s | %20s | %6s | %31s | %15s | %8s | %20s |\n", student.getID(), student.getName(), student.getMajor(), student.getEmail(), student.getPhone(), student.getPassport(), student.getAddress());
                }
            }
            System.out.println(" -----------------------------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Failed to print out the students that registered more than 2 programs");
        }
        
    }
    
    /**
     * Separate the aboard program id after getting file names.
     * @param filePath
     * @return array of the aboard program id after getting file names
     * @throws java.lang.Exception
     */
    public ArrayList<String> getListOfProgramID(String filePath) throws Exception{
        try {
            ArrayList<String> programIDLIst = new ArrayList<String>();
            for (String fileName : getFileName(filePath)) {
                programIDLIst.add(fileName.substring(fileName.indexOf("_") + 1));
            }
            return programIDLIst;
        } catch (Exception e) {
            System.out.println("Failed to get aboard program id.");
            return null;
        }
        
    }
    
    /**
     * Delete duplicated aboard program id after getting student id from file.
     * @param filePath
     * @return array of aboard program id which does not have duplicated values.
     * @throws java.lang.Exception
     */
    public HashSet<String> deleteProgramIDDuplicate(String filePath) throws Exception{
        try {
            HashSet<String> programSet = new HashSet<String>();
            for (String programID : getListOfProgramID(filePath)) {
                programSet.add(programID);
            }
            return  programSet;
        } catch (Exception e) {
            System.out.println("Failed to delete duplicated id.");
            return null;
        }
    }
    
    /**
     * Count the number of students registing an 
     * aboard program by inputting aboard program id.
     * @param findProgramID
     * @param filePath
     * @return number of students registing an aboard program
     * @throws java.lang.Exception
     */
    public int countStudentRegistProgram(String findProgramID, String filePath) throws Exception{
        int count = 0;
        try {
            for(String programID : getListOfProgramID(filePath)){
                if (programID.equalsIgnoreCase(findProgramID)) {
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println("There is an error.");
        }
        return count;
    }
    
    /**
     * Print out the number of students registing an 
     * aboard program by inputting aboard program id.
     * @param filePath 
     * @throws java.lang.Exception
     */
    public void printNumberOfStudentRegistProgram(String filePath) throws Exception{
        try {
            System.out.print("Program ID: ");
            String programID = Adder.addStringWithOutBlank();
            if (this.aboardProgramManagement.isProgramExist(programID)) {
                AboardProgram aboardProgram = this.aboardProgramManagement.get(programID);
                
                System.out.println("Number of students registing " + programID + ": " + countStudentRegistProgram(programID, filePath));
            }else{
                System.out.println("Aboard program does not exist.");
            }
        } catch (Exception e) {
            System.out.println("Failed to count students that registered the program");
        }
    }
}
