/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refdefcwk;

/**
 *
 * @author Jordan A
 * Version - 20/06/25
 * The Consultant class represents a type of staff that is responsible for all jobs type
 * consultants can do all job, despite the jobTypes and consultant may also be CORGI trained which
 * increases their hourly rate from 30 to 40.
 */
public class Consultant extends Staff {
    private boolean corgiTrained;

    /**
     * 
     * @param name - name of staff
     * @param experience - years of experience the Consultant has 
     * @param retainer - retainer fee of Consultant
     * @param corgiTrained - whether the Consultant is trained or not
     * provides the attributes assigned to a staff who is a planner, like name of staff, experience, 
     * whether the specific staff is corgiTrained or not
     * 
     */
    public Consultant(String name, int experience, int retainer, boolean corgiTrained) {
        super(name, experience, retainer, corgiTrained ? 40 : 30);
        this.corgiTrained = corgiTrained;
    }

    /**
     * returns either true or false if a consultant is can do a job, based on the jobtype
     * @param job
     * @return True for all jobs, as consultant can do all jobs, irrespective of jobType
     */
    @Override
    public boolean canDoJob(Job job) {
        return true; 
    }

    /**
     * 
     * @return A string values containing every details or attributes of a Consultant staff 
     */
    @Override
    public String toString() {
        return name + ",Consultant," + experience + "," + retainer + "," + getHourlyRate() + ",Available," + corgiTrained;
    }
}
