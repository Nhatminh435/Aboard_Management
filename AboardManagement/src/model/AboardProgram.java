package model;

import java.io.Serializable;

/**
 * This is a class representing a Aboard Program.
 * It contains properties such as id, name, time, From Registration Date,
 * End Registration Date, days, location, cost, content.
 * It implements the Serializable interface to enable object serialization.
 * The class provides methods to get and set these properties.
 */
public class AboardProgram implements Serializable{
    private String id;
    private String name;
    private String time;
    private String dateRegistrationStart;
    private String dateRegistrationEnd;
    private int days;
    private String location;
    private double cost;
    private String content;

    /**
     * Default constructor of AboardProgram class.
     */
    public AboardProgram() {
    }

    /**
     * Constructor of AboardProgram class.
     * @param id
     * @param name
     * @param time
     * @param dateRegistrationStart
     * @param dateRegistrationEnd
     * @param days
     * @param location
     * @param cost
     * @param content 
     */
    public AboardProgram(String id, String name, String time, String dateRegistrationStart, String dateRegistrationEnd, int days, String location, double cost, String content) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.dateRegistrationStart = dateRegistrationStart;
        this.dateRegistrationEnd = dateRegistrationEnd;
        this.days = days;
        this.location = location;
        this.cost = cost;
        this.content = content;
    }

    /**
     * Set the name of the AboardProgram
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the time of the AboardProgram
     * @param time 
     */
    public void setTime(String time) {
        this.time = time;
    }
    /**
     * Set the From Registration Date of the AboardProgram
     * @param dateRegistrationStart 
     */
    public void setDateRegistrationStart(String dateRegistrationStart) {
        this.dateRegistrationStart = dateRegistrationStart;
    }

    /**
     * Set the End Registration Date of the AboardProgram
     * @param dateRegistrationEnd 
     */
    public void setDateRegistrationEnd(String dateRegistrationEnd) {
        this.dateRegistrationEnd = dateRegistrationEnd;
    }

    /**
     * Set the days of the AboardProgram
     * @param days 
     */
    public void setDays(int days) {
        this.days = days;
    }
    
    /**
     * Set the location of the AboardProgram
     * @param location 
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Set the cost of the AboardProgram
     * @param cost 
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    /**
     * Set the content of the AboardProgram
     * @param content 
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns the ID of AboardProgram.
     * @return ID of AboardProgram
     */
    public String getID() {
        return id;
    }

    /**
     * Returns the name of AboardProgram.
     * @return name of AboardProgram
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the time of AboardProgram.
     * @return time of AboardProgram
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns the From Registration Date of AboardProgram.
     * @return From Registration Date of AboardProgram
     */
    public String getDateRegistrationStart() {
        return dateRegistrationStart;
    }

    /**
     * Returns the End Registration Date of AboardProgram.
     * @return End Registration Date of AboardProgram
     */
    public String getDateRegistrationEnd() {
        return dateRegistrationEnd;
    }

    /**
     * Returns the days of AboardProgram.
     * @return days of AboardProgram
     */
    public int getDays() {
        return days;
    }

    /**
     * Returns the location of AboardProgram.
     * @return ID of AboardProgram
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the cost of AboardProgram.
     * @return cost of AboardProgram
     */
    public double getCost() {
        return cost;
    }

    /**
     * Returns the content of AboardProgram.
     * @return content of AboardProgram
     */
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return getID() + ", " + getName() + ", " + getTime() + ", " + getDateRegistrationStart() + ", " + getDateRegistrationEnd() + ", " + getDays() + ", " + getLocation() + ", "+ getCost() + ", " + getContent();
    }
}
