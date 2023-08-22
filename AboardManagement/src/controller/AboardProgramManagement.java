package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import model.AboardProgram;
import tool.Adder;
import viewer.Menu;

/**
 * The class {@code AboardProgramManagement} provides methods to 
 * address logic of programs including display menu of main missions
 * of this class, add new aboard program, edit, search, save aboard
 * programs and load them from file.
 * @author N.Minh
 */
public class AboardProgramManagement extends HashMap<String, AboardProgram>{
    private final String PROGRAM_PATH = "\\output\\AboardPrograms.dat";
    private String[] MENU_OPTIONS = {"Displays all aboard programs", "Add a new aboard program", "Edit information a program by id", "Search and display a program by id", "Back to main menu"};
    private String[] EDIT_PROGRAM_OPTIONS = {"Name", "Time", "From Registration Date", "End Registration Date", "Days", "Location", "Cost", "Content", "Exit"};
    private String [] VALID_MONTH = {"January", "March", "May", "July", "September", "November"};
    
    /**
     * Display sub menu of function 1: [Manage aboard 
     * programs] and perform sub functions such as:
     * Displays all aboard programs, 
     * Add a new aboard program, 
     * Edit information a program by id, 
     * Search and display a program by id, 
     */
    public void displayMenu(){
        int getChoice = 1;
        do {            
            System.out.println("");
            getChoice = Menu.getChoice("Manage aboard programs", MENU_OPTIONS, 1);
            switch(getChoice){
                case 1:
                    displayAboardProgam();
                    break;
                case 2:
                    if (addNewAboardProgram()) {
                        System.out.println("Add successfully.");
                    }
                    break;
                case 3:
                    System.out.println("");
                    if (editAboardProgram()) {
                        System.out.println("Edit successfully.");
                    }
                    break;
                case 4:
                    searchAboardProgram();
                    break;
                case 5:
                    break;
            }
        } while ((getChoice > 0) && (getChoice < MENU_OPTIONS.length));
    }
    
    /**
     * Display all information of
     * aboard programs that are saved in 
     * AboardProgramManagement following the template:
     * 
     * Details of the study programs at a foreign school:
     * ID and Name: 
     * Time : 
     * Day : 
     * Location:
     * Cost:
     * Content: 
     */
    public void displayAboardProgam(){
        System.out.println("\nDetails of the study programs at a foreign school:");
        int i = 0;
        for (AboardProgram aboardProgram : this.values()) {
            System.out.println(++i + ".");
            System.out.println("ID and Name: " + aboardProgram.getID() + "-" + aboardProgram.getName());
            System.out.println("Time: " + aboardProgram.getTime());
            System.out.println("Day: " + aboardProgram.getDays() + " days");
            System.out.println("Location: " + aboardProgram.getLocation());
            System.out.println("Cost: " + aboardProgram.getCost());
            System.out.println("Content: " + aboardProgram.getContent());
            System.out.println(aboardProgram.getDateRegistrationStart() + "\n");
        }
    }   
    
    /**
     * Return true if the aboard program id exist in AboardProgramManagement
     * @param programID
     * @return true if the aboard program id exist in AboardProgramManagement
     */
    public boolean isProgramExist(String programID){
        return this.containsKey(programID);
    }
    
    /**
     * A text scanner which takes {@linkplain Adder#addStringWithOutBlank() } 
     * as basic and can parse aboard program whether
     * it does not exist in AboardProgramManagement and 
     * return new aboard program id.
     * If aboard program id does not meet conditions, this method
     * will force user re-enter until it is right.
     * @return new aboard program id
     */
    private String addNewProgramID(){
        String programID = Adder.addStringWithOutBlank();
        if (isProgramExist(programID)) {
            do {                
                System.out.println("ID exist.");
                System.out.print("➜ Please enter again: ");
                programID = Adder.addStringWithOutBlank();
            } while (isProgramExist(programID));
        }
        return programID;
    }
    
    /**
     * Return true if time is January, March, May, July, September or November.
     * @param time
     * @return true if time is  January, March, May, July, September or November.
     */
    private boolean checkTime(String time){
        return Arrays.asList(VALID_MONTH).contains(time);
    }
    
    /**
     * A text scanner which takes {@linkplain Adder#addStringWithOutBlank() }
     * as basic and can parse time whether
     * it is January, March, May, July, September or November and return time.
     * If time does not meet conditions, this method
     * will force user re-enter until it is right.
     * @return time
     */
    private String addTime(){
        String time = Adder.addStringWithOutBlank();
        if (!checkTime(time)) {
            do {        
                System.out.println("Only accept as: January, March, May, July, September, November.");
                System.out.print("➜ Please enter again: ");
                time = Adder.addStringWithOutBlank();
            } while (!checkTime(time));
        }
        
        return time;
    }
    
    /**
     * A text scanner which takes {@linkplain Adder#addPositiveIntegerNumber() }
     * as basic and can parse days whether
     * it is from 30 - 40 and return days.
     * If days does not meet conditions, this method
     * will force user re-enter until it is right.
     * @return days
     */
    private int addDays(){
        int days = Adder.addPositiveIntegerNumber();
        if ((days < 30) || (days > 40)) {
            do {                
                System.out.println("Must from 30 to 40 days");
                System.out.print("➜ Please enter again: ");
                days = Adder.addPositiveIntegerNumber();
            } while ((days < 30) || (days > 40));
        }
        return days;
    }
    
    /**
     * A text scanner which takes {@linkplain Adder#addStringWithBlank()}
     * as basic and can parse content whether
     * it has ".doc" or ".pdf" extension and return content.
     * If content does not meet conditions, this method
     * will force user re-enter until it is right.
     * @return content
     */
    private String addContent(){
        String content = Adder.addStringWithBlank();
        if (!(content.endsWith(".doc") || content.endsWith(".pdf"))) {
            do {                
                System.out.println("Unvalid file.");
                System.out.print("➜ Please enter again: ");
                content = Adder.addStringWithBlank();
            } while (!(content.endsWith(".doc") || content.endsWith(".pdf")));
        }
        return content;
    }
    
    /**
     * Create and add new {@code AboardProgram} to AboardProgramManagement
     * Return true if create and add aboard program successfully.
     * @return true if create and add aboard program successfully.
     */
    public boolean addNewAboardProgram(){
        System.out.print("ID: ");
        String aboardProgramID = addNewProgramID();
        System.out.print("Name: ");
        String aboardProgramName = Adder.addStringWithBlank();
        System.out.print("Time: ");
        String aboardProgramTime = addTime();
        System.out.print("From Registration Date (dd/MM/yyyy): ");
        String aboardProgramRegistStart = Adder.addDate();
        System.out.print("End Registration Date (dd/MM/yyyy): ");
        String aboardProgramRegistEnd = Adder.addDate();
        System.out.print("Days: ");
        int aboardProgramDays = addDays();
        System.out.print("Location: ");
        String aboardProgramLocation = Adder.addStringWithBlank();
        System.out.print("Cost: ");
        double aboardProgramCost = Adder.addPositiveDoubleNumber();
        System.out.print("Content: ");
        String aboardProgramContent = addContent();
        AboardProgram aboardProgram = new AboardProgram(aboardProgramID, aboardProgramName, aboardProgramTime, aboardProgramRegistStart, aboardProgramRegistEnd, aboardProgramDays, aboardProgramLocation, aboardProgramCost, aboardProgramContent);
        this.put(aboardProgram.getID(), aboardProgram);
        return true;
    }
    
    /**
     * Search aboard program by inputting aboard program id
     * and edit its name, time, From Registration Date, End 
     * Registration Date, days, location, cost, content.
     * Return true if edit aboard program's information successfully.
     * @return true if edit aboard program's information successfully.
     */
    public boolean editAboardProgram(){
        System.out.print("Enter aboard program id: ");
        String editAboardProgramID = Adder.addStringWithOutBlank();
        if (isProgramExist(editAboardProgramID)) {
                for (AboardProgram aboardProgram : this.values()) {
                if (aboardProgram.getID().equals(editAboardProgramID)) {
                    int getChoice = Menu.getChoice("Choose field to edit [" + aboardProgram.getID() + "]", EDIT_PROGRAM_OPTIONS, 2);
                    switch(getChoice){
                        case 1:
                           System.out.print("Enter new name: ");
                            String newName = Adder.addStringWithBlank();
                            aboardProgram.setName(newName);
                            break;
                        case 2:
                            System.out.print("Enter new time: ");
                            String newTime = addTime();
                            aboardProgram.setTime(newTime);
                            break;
                        case 3:
                            System.out.print("Enter new From Registration Date: ");
                            String newRegistDateStart = Adder.addDate();
                            aboardProgram.setDateRegistrationStart(newRegistDateStart);
                            break;
                        case 4:
                            System.out.print("Enter new End Registration End: ");
                            String newRegistDateEnd = Adder.addDate();
                            aboardProgram.setDateRegistrationEnd(newRegistDateEnd);
                            break;
                        case 5:
                            System.out.print("Enter new days: ");
                            int newDays = addDays();
                            aboardProgram.setDays(newDays);
                            break;
                        case 6:
                            System.out.print("Enter new location: ");
                            String newLocation = Adder.addStringWithBlank();
                            aboardProgram.setLocation(newLocation);
                            break;
                        case 7:
                            System.out.print("Enter new cost: ");
                            double newCost = Adder.addPositiveDoubleNumber();
                            aboardProgram.setCost(newCost);
                            break;
                        case 8:
                            System.out.print("Enter new content: ");
                            String newContent = addContent();
                            aboardProgram.setContent(newContent);
                            break;
                        default:
                            break;
                    }
                }   
            }
                return true;
        }else{
            System.out.println("Program does not exist.");
            return false;
        }  
    }
    
    /**
     * Search aboard program by inputting aboard program id
     * and display its information following the template:
     * ID and Name: 
     * Time : 
     * Day : 
     * Location .
     * Cost
     * Content 
     */
    public void searchAboardProgram(){
        System.out.print("Enter the program ID: ");
        String searchID = Adder.addStringWithOutBlank();
        for (AboardProgram aboardProgram : this.values()) {
            if (aboardProgram.getID().equalsIgnoreCase(searchID)) {
                System.out.println("ID and Name: " + aboardProgram.getID() + "-" + aboardProgram.getName());
                System.out.println("Time: " + aboardProgram.getTime());
                System.out.println("Day: " + aboardProgram.getDays() + " days");
                System.out.println("Location: " + aboardProgram.getLocation());
                System.out.println("Cost: " + aboardProgram.getCost());
                System.out.println("Content: " + aboardProgram.getContent());
            }
        }
    }
    
    /**
     * This method will:
     * Saves the collection of aboard program one by one to the binary file
     * AboardProgram.dat.
     * Return true if it saves successfully.
     * Return false with message "Fail to save aboard program's information to
     * AboardProgram.dat." if it saves failed.
     * 
     * @param filePath
     * @return  true if saving successfully.
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public boolean saveData(String filePath) throws FileNotFoundException, IOException{
        try {
            FileOutputStream fileOutput = new FileOutputStream(new File(filePath + PROGRAM_PATH));
            ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
            for (AboardProgram aboardProgram : this.values()) {
                objOutput.writeObject(aboardProgram);
            }
            objOutput.flush();
            objOutput.close();
            fileOutput.close();
            return true;
        } catch (FileNotFoundException f) {
            System.out.println("Fail to save aboard program's information to AboardProgram.dat");
            return false;
        } catch (IOException e) {
            System.out.println("Fail to save aboard program's information to AboardProgram.dat");
            return false;
        }
    }
    
    /**
     * This method will:
     * 
     * Get aboard programs' information from the binary file AboardProgram.dat through
     * file path and add them one by one to a AboardProgramManagement.
     * Return true with message "Load information from AboardProgram.dat successfully!" if it successes.
     * Return false with message "Fail to load AboardProgram.dat" if it fail.
     * 
     * @param filePath
     * @return true if loading successes.
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public boolean loadData(String filePath) throws FileNotFoundException, IOException{
        try {
            FileInputStream fileInput = new FileInputStream(new File(filePath + PROGRAM_PATH));
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            while (true) {
                try {
                    Object object = objectInput.readObject();
                    if (object != null) {
                        if (object instanceof AboardProgram) {
                            AboardProgram aboardProgram = (AboardProgram) object;
                            this.put(aboardProgram.getID(), aboardProgram);
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
            System.out.println("Fail to load AboardProgram.dat: " + f.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Fail to load AboardProgram.dat: " + e.getMessage());
            return false;
        }
    }
}
