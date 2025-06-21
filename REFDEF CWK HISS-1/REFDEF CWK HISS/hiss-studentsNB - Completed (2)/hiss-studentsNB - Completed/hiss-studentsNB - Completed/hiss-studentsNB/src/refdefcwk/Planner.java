/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refdefcwk;

/**
 *This Planner class represents a specific type of staff member, responsible for designing jobs
 * A planner can only do jobs of Design Types, as specified by the specification.
 * 
 * 
 * @author Jordan A
 *version - 20/06/25
 */
public class Planner extends Staff {
    private String make;

    /**
     * 
     * @param name - name of staff
     * @param experience - number of year of experience
     * @param retainer - retainer fee for each planner
     * @param make - area of expertise or equipment associated with each staff Planner
     * Constructor
     * provides the attributes assigned to a staff who is a planner, like name of staff, experience, hourly rate which 
     * is calculated as experience * 15 etc. 
     */
    public Planner(String name, int experience, int retainer, String make) {
        super(name, experience, retainer, experience * 15);
        this.make = make;
    }

    /**
     * 
     * @param job
     * @return if a staff that is a planner can do a specific job if it matches the jobtype "DESIGN"
     * true if the job is design, false otherwise
     */
    @Override
    public boolean canDoJob(Job job) {
        return job.getType() == JobType.DESIGN;
    }

    /**
     * Returns a string representation of the planner
     *
     * @return A string containing planner details like experience, retainer, etc.
     */
    @Override
    public String toString() {
        return name + ",Planner," + experience + "," + retainer + "," + getHourlyRate() + ",Available," + make;
    }
}
