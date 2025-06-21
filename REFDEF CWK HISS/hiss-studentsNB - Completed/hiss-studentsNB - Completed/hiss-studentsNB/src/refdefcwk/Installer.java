/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refdefcwk;

/**
 *
 * @author Jordan A
 * version - 20/06/25
 * The installer class represents a type of staff who is in responsible for installation jobs,
 * and maintenance jobs if they are CORGI trained
 *this class extends Staff class and has a Boolean methods to indicate whether an installer staff is CORGI trained or not 
 */
public class Installer extends Staff {
    private boolean corgiTrained;

    /**
     * Constructs a new installer staff with the provided attributes, which are its attributes
     * the hourly rate for installers staff is set as 20
     * @param name
     * @param experience
     * @param retainer
     * @param corgiTrained 
     */
    public Installer(String name, int experience, int retainer, boolean corgiTrained) {
        super(name, experience, retainer, 20);
        this.corgiTrained = corgiTrained;
    }

    /**
     * determines if an installer can do the provided job
     * installer can always do jobs that are of jobtype installations and also do maintenance jobs if they are CORGI trained
     * @param job - the job to be checked
     * @return true  if the installer can do the job, false otherwise
     */
    @Override
    public boolean canDoJob(Job job) {
        if (job.getType() == JobType.INSTALLATION) return true;
        if (job.getType() == JobType.MAINTENANCE && corgiTrained) return true;
        return false;
    }

    /**
     * returns a string representation of the Installer, suitable for display. 
     * @return A string values containing every details or attributes of an Installer staff 
     */
    @Override
    public String toString() {
        return name + ",Installer," + experience + "," + retainer + "," + getHourlyRate() + ",Available";
    }
}
