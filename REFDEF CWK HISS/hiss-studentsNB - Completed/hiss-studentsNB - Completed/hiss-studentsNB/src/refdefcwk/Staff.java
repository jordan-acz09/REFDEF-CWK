/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refdefcwk;
import java.io.Serializable;
/**
 * This Staff class is an Abstract class representing a staff member in the system,  
 * it stores common attributes like name, experience, retainers, etc
 *
 * all staff members are in the AVAILABLE state and must implement CanDoJon method 
 * to be able to determine if they are eligible to do a job. 
 * @author Jordan A
 * version - 20/06/2025
 */
public abstract class Staff implements Serializable {
    protected String name;
    protected int experience;
    protected int retainer;
    protected int hourlyRate;
    protected StaffState state;

    /**
     * constructors for a new staff member with the specified attributes
     * @param name
     * @param experience
     * @param retainer
     * @param hourlyRate 
     */
    public Staff(String name, int experience, int retainer, int hourlyRate) {
        this.name = name;
        this.experience = experience;
        this.retainer = retainer;
        this.hourlyRate = hourlyRate;
        this.state = StaffState.AVAILABLE;
    }

    /**
     * 
     * @return name of Staff member
     */
    public String getName() { 
        return name;
    }
    
    /**
     * 
     * @return years of experience a staff has 
     */
    public int getExperience() {
        return experience;
    }
    
    /**
     * 
     * @return retainer fee for a staff member 
     */
    public int getRetainer() { 
        return retainer;
    }
    
    /**
     * 
     * @return the hourly rate to hire a staff member 
     */
    public int getHourlyRate() {
        return hourlyRate;
    }
    
    /**
     * 
     * @return the current state of a staff member 
     */
    public StaffState getState() {
        return state;
    }
    
    /**
     * sets the current state of the staff member
     * @param s 
     */
    public void setState(StaffState s) { 
        this.state = s; 
    }
    
    /**
     * 
     * @param job - the job to check 
     * @return true if a staff member can do a job assigned to them, false if else
     */
    public abstract boolean canDoJob(Job job);

    /**
     * returns a string representation of staff members, entailing staff details.
     * @return a string format of staff details 
     */
    public String toString() {
        return name + " [" + getClass().getSimpleName() + "], Exp: " + experience + ", State: " + state;
    }
}
