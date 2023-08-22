package model;

import java.io.Serializable;

/**
 * This is a class representing a Registration.
 * It contains properties such as registration date, parent's email, parent's phone, student, aboard program. 
 * It implements the Serializable interface to enable object serialization.
 * The class provides methods to get and set these properties.
 */
public class Registration implements Serializable{
    private String registDate;
    private String parentEmail;
    private String parentPhone;
    private Student student;
    private AboardProgram aboardProgram;
    
    /**
     * Default constructor of Registration class.
     */
    public Registration() {
    }

    /**
     * Constructor of Registration class.
     * @param registDate
     * @param parentEmail
     * @param parentPhone
     * @param student
     * @param aboardProgram 
     */
    public Registration(String registDate, String parentEmail, String parentPhone, Student student, AboardProgram aboardProgram) {
        this.registDate = registDate;
        this.parentEmail = parentEmail;
        this.parentPhone = parentPhone;
        this.student = student;
        this.aboardProgram = aboardProgram;
    }

    /**
     * Set the registration date of Registration class.
     * @param registDate 
     */
    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }

    /**
     * Set the parent email of Registration class.
     * @param parentEmail 
     */
    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    /**
     * Set the parent phone of Registration class.
     * @param parentPhone 
     */
    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    /**
     * Set the student of Registration class.
     * @param student 
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Set the aboard program of Registration class.
     * @param aboardProgram 
     */
    public void setAboardProgram(AboardProgram aboardProgram) {
        this.aboardProgram = aboardProgram;
    }

    /**
     * Returns the registration date of the Registration class.
     *
     * @return The registration date of the Registration class.
     */
    public String getRegistDate() {
        return registDate;
    }

    /**
     * Returns the parent email of the Registration class.
     *
     * @return The parent email of the Registration class.
     */
    public String getParentEmail() {
        return parentEmail;
    }

    /**
     * Returns the parent phone of the Registration class.
     *
     * @return The parent phone of the Registration class.
     */
    public String getParentPhone() {
        return parentPhone;
    }

    /**
     * Returns the student of the Registration class.
     *
     * @return The student of the Registration class.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Returns the aboard program of the Registration class.
     *
     * @return The aboard program of the Registration class.
     */
    public AboardProgram getAboardProgram() {
        return aboardProgram;
    }

    @Override
    public String toString() {
        return getStudent().getID() + "," + getAboardProgram().getID() + "," + getRegistDate() + "," + getParentEmail() + "," + getParentPhone();
    }
    
}
