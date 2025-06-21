/*import java.io.Serializable;
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refdefcwk;
import java.io.Serializable;
/**
 *This Job Class represents a work assignment in the HISS system 
 *each job has its own unique job number, a type (installation, maintenance, design)
 * 
 * @author Jordan A
 * version - 20/06/25
 */
public class Job implements Serializable {
    private int jobNumber;
    private JobType type;
    private String location;
    private int experienceRequired;
    private int hours;
    private int penalty;

    /**
     * constructs a new job with the specified details
     * 
     * @param jobNumber
     * @param type
     * @param location
     * @param experienceRequired
     * @param hours
     * @param penalty 
     */
    public Job(int jobNumber, JobType type, String location, int experienceRequired, int hours, int penalty) {
        this.jobNumber = jobNumber;
        this.type = type;
        this.location = location;
        this.experienceRequired = experienceRequired;
        this.hours = hours;
        this.penalty = penalty;
    }

    /**
     * 
     * @return the job number  
     */
    public int getJobNumber() { 
        return jobNumber; 
    }
    
    /**
     * 
     * @return the type of the job 
     */
    public JobType getType() { 
        return type; 
    }
    
    /**
     * 
     * @return location of the job 
     */
    public String getLocation() { 
        return location;
    }
    
    /**
     * 
     * @return years of experience required for a staff to do the job 
     */
    public int getExperienceRequired() {
        return experienceRequired; 
    }
    
    /**
     * 
     * @return an estimate of hours required for a job to be done  
     */
    public int getHours() { 
        return hours;
    }
    
    /**
     * 
     * @return penalty fee that is applied, when a staff is unable to do a job due to 
     * unfulfilled requirements or unavailability of staffs in team list
     */
    public int getPenalty() { 
        return penalty;
    }

    /**
     * a string representation of the job, providing main details of a job
     * @return a formatted string describing the job with its attributes. 
     */
    public String toString() {
        return "Job#" + jobNumber + " [" + type + "] Loc: " + location + " ExpReq: " + experienceRequired +
                " Hrs: " + hours + " Penalty: " + penalty;
    }
}
