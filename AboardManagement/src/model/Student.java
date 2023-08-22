package model;

import java.io.Serializable;

/**
 * This is a class representing a Student.
 * It contains properties such as id, name, major, email, phone, passport, address. 
 * It implements the Serializable interface to enable object serialization.
 * The class provides methods to get and set these properties.
 */
public class Student implements Serializable{
    private String id;
    private String name;
    private String major;
    private String email;
    private String phone;
    private String passport;
    private String address;

    /**
     * Default constructor of Student class.
     */
    public Student() {
    }

    /**
     * Constructor of Student class.
     * @param id
     * @param name
     * @param major
     * @param email
     * @param phone
     * @param passport
     * @param address 
     */
    public Student(String id, String name, String major, String email, String phone, String passport, String address) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.email = email;
        this.phone = phone;
        this.passport = passport;
        this.address = address;
    }

    /**
     * Set the name of Student class.
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the major of Student class.
     * @param major 
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Set the email of Student class.
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set the phone of Student class.
     * @param phone 
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Set the passport of Student class.
     * @param passport 
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Set the address of Student class.
     * @param address 
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the ID of Student.
     * @return ID of Student
     */
    public String getID() {
        return id;
    }

    /**
     * Returns the name of Student.
     * @return name of Student
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the major of Student.
     * @return major of Student
     */
    public String getMajor() {
        return major;
    }

    /**
     * Returns the email of Student.
     * @return email of Student
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the phone of Student.
     * @return phone of Student
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Returns the passport of Student.
     * @return passport of Student
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Returns the address of Student.
     * @return address of Student
     */
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return getID() + "," + getName() + "," + getMajor() + "," + getEmail() + "," + getPhone() + "," + getPassport() + "," + getAddress();
    }
    
}
