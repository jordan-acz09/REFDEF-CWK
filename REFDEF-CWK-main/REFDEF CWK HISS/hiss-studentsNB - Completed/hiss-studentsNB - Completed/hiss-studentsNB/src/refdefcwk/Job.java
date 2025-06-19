/*import java.io.Serializable;
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refdefcwk;
import java.io.Serializable;
/**
 *
 * @author Ja22aaj
 */
public class Job implements Serializable {
    private int jobNumber;
    private JobType type;
    private String location;
    private int experienceRequired;
    private int hours;
    private int penalty;

    public Job(int jobNumber, JobType type, String location, int experienceRequired, int hours, int penalty) {
        this.jobNumber = jobNumber;
        this.type = type;
        this.location = location;
        this.experienceRequired = experienceRequired;
        this.hours = hours;
        this.penalty = penalty;
    }

    public int getJobNumber() { return jobNumber; }
    public JobType getType() { return type; }
    public String getLocation() { return location; }
    public int getExperienceRequired() { return experienceRequired; }
    public int getHours() { return hours; }
    public int getPenalty() { return penalty; }

    public String toString() {
        return "Job#" + jobNumber + " [" + type + "] Loc: " + location + " ExpReq: " + experienceRequired +
                " Hrs: " + hours + " Penalty: " + penalty;
    }
}