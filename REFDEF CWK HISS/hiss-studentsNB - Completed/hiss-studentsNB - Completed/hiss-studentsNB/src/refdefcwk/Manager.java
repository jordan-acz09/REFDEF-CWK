package refdefcwk;

import java.util.*;
import java.io.*;

/**
 * This class specifies the behaviour expected from the HISS system
 * as required for 5COM2007 Referred/Deferred Cwk - June 2025 
 * specified in the HISS interface
 * 
 * @author - Jordan Anyanwu 21037141
 * @author - if working as a pair, add your partner's name & SRN here
 *           (or leave blank, if working individually)
 * @version - add the submission date here
 */
public class Manager implements HISS,Serializable
{
   
    private String name;
    private double account;
    private Map<String, Staff> allStaff = new HashMap<>();
    private Map<String, Staff> team = new HashMap<>();
    private Map<Integer, Job> jobs = new HashMap<>();
 
//**************** HISS ************************** 
    /** Constructor requires the name of the trainee manager and initial budget. Staff
     * and jobs are also set up,  with all staff set to "available" for hire.
     * @param manager the name of the trainee manager running the simulation
     * @param budget the initial budget allocated to the project account
     */
    public Manager(String manager, double budget)
    {
       name = manager;
       account = budget;
       setupStaff();
       setupJobs();
    }
    
    /** Constructor requires the name of the trainee manager, initial budget 
     * and name of jobs file. Staff are also set up,  with all staff set to 
     * "available" for hire. Jobs are "read from a file
     * @param manager the name of the trainee manager running the simulation
     * @param budget the initial budget allocated to the project account
     * @param jobfile the name of the jobs file
     */
    public Manager(String manager, double budget, String jobfile)
    {
       name = manager;
       account = budget;
       setupStaff();
       readJobs(jobfile);
    }
    
    /**Returns a String representation of the state of the project,
     * including the name of the manager, state of the project account,
     * whether overdrawn or not, the staff in the team (or, "No staff" 
     * if team is empty)
     * @return a String representation of the state of the project,
     * including the name of the manager, state of the project account,
     * whether overdrawn or not, and the staff currently in the 
     * team,(or, "No staff" if team is empty)
     **/
    public String toString()
    {
        String s = "\nManager: " + name;
        s += "\nAccount = " + account;
        if (account < 0) {
            s += " (ITProject is overdrawn)";
        }
        s += "\n";
        if (team.isEmpty()) {
            s += "No staff";
        } else {
            s += "Team:";
            for (Staff staff : team.values()) {
                s += "\n" + staff.toString();
            }
        }
        return s;
    } 
    
    
    /** returns the amount of money in the account
     * @returns the amount of money in the account
     */
    public double getAccount()
    {
        return account;
    }

    
    /** returns true if project account <=0 and the team has no staff 
     * who can leave. 
     * @returns true if project account <=0 and the team has no staff 
     * who can leave. 
     */
    public boolean isOverdrawn()
    {
        if (account > 0) return false;
        for (Staff s : team.values()) {
            if (s.getState() == StaffState.ONLEAVE) return false;
        }
        return true;
    }
    
//**********************Jobs************************* 

    /** returns true if the number represents a job
     * @param num is the reference number of the job
     * @returns true if the reference number represents a job
     **/
     public boolean isJob(int num)
     {
        return jobs.containsKey(num);
     }

     
    /** Provides a String representation of all jobs 
     * @return returns a String representation of all jobs
     **/
    public String getAllJobs()
    {
    String s = "\n************ All Jobs ************\n";
    if (jobs.isEmpty()) {
        s += "No jobs available.";
    } else {
        for (Job job : jobs.values()) {
            s += job.toString() + "\n";
        }
    }
    return s;
    }
 
    /** Returns a String with information about specified job
     * @param no - number of the specified job
     * @return returns a String representation of all jobs
     **/
    public String getJob(int no)
    {
        Job job = jobs.get(no);
        if (job != null) return job.toString();
        return "No such job";  
    }
    //*********************** Staff *************************    

     /** Returns details of a staff member with the given name
     * (staff may be in or out of the team)
     * @param name the name of the required staff member
     * @return details of a staff member with the name specified 
     * in the parameter
     **/
    public String getStaff(String name)
    {
        Staff s = allStaff.get(name);
        if (s == null) s = team.get(name);
        if (s != null) return s.toString();    

        return "\nNo such staff";
    }
    

    /**Returns a String representation of all staff available for hire 
     * @return a String representation of all staff available for hire 
     **/
    public String getAllAvailableStaff()
    
     {//assumes allStaff is a Hashmap
        String s = "************ Staff for Hire ********";
        boolean found = false;
        for (Staff staff : allStaff.values()) {
            if (staff.getState() == StaffState.AVAILABLE) {
                s += "\n" + staff;
                found = true;
            }
        }
        if (!found) {
            s += "\nNo staff available for hire.";
        }
        return s;
    }
     
 // ***************** Team Staff ************************   

    /** Allows staff to be added to the team, if there is enough  
     * money in the account for the retainer.The hired staff member's 
     * state is set to "working" and their retainer is deducted from
     * the project account. Return the result of the hire; all messages 
     * should include the staff name and state of the project account
     * @param name is the name of the staff member
     * @return "Not found" if staff not found, "Already hired" if staff 
     * is already hired, "Not enough money" if not enough money in the 
     * account,"Hired" if staff are hired.All messages should include 
     * the staff name and state of the project account
     **/        
    public String hireStaff(String name)
{
    String result;
    Staff staff = allStaff.get(name);
    if (staff == null) {
        result = "Not found: " + name;
    } else if (team.containsKey(name)) {
        Staff teamStaff = team.get(name);
        if (teamStaff.getState() == StaffState.ONLEAVE) {
            result = name + " cannot be hired (not available)";
        } else {
            result = "Staff not available"; // Already working
        }
    } else if (account < staff.getRetainer()) {
        result = "Not enough money to hire " + name;
    } else if (staff.getState() != StaffState.AVAILABLE) {
        result = name + " cannot be hired (not available)";
    } else {
        account -= staff.getRetainer();
        staff.setState(StaffState.WORKING);
        team.put(name, staff);
        result = "Hired: " + name;
    }
    return result + "\nAccount = " + account;   
}
        
   /** Returns true if the staff with the specified name 
     * is in the team, false otherwise. 
     * @param name is the name of the staff
     * @return true if the staff with the name is in the team, 
     * false otherwise.
     **/
    public boolean isInTeam(String name)
    {
        return team.containsKey(name);
    }
    
    
    /**Returns a String representation of the staff in the project team
     * (including those on holiday), or the message "No staff hired"
     * @return a String representation of the staff in the project team
     **/
    public String getTeam()
    {
          String s = "************ TEAM ********";
          if (team.isEmpty()) {
            s += "\nNo staff hired";
        } else {
            for (Staff staff : team.values()) {
                s += "\n" + staff.toString();
            }
        }
        return s;
    }
  

    /** Retrieves the job represented by the job number, or returns "No 
     * such job".If job exists, finds a staff member in the team who can 
     * do the job.The results of doing a job will be one of the following: 
     * " Job completed by..." - adds the cost of the job to account and include 
     *                           name of staff staff go "on holiday", 
     * " Job not completed as no staff available" - deduct job penalty from account,
     * " Job not completed due to staff inexperience" - deduct penalty from 
     *                           the account.
     * If a job is not completed and the project account becomes negative, 
     * add "ITProject is overdrawn " to the output. 
     * @param jbNo is the reference number of the job
     * @return a String showing the result of doing the job(as above)
     */ 
    public String doJob(int jbNo)
{
    String outcome = "\nJob No " + jbNo;
    Job job = getAJob(jbNo); // helper method to fetch job

    if (job == null) {
        outcome += "\nNo such job";
        return outcome;
    }

    Staff staff = getStaffForJob(job); // helper method to find suitable staff

    if (staff != null) {
        if (staff.getExperience() >= job.getExperienceRequired()) {
            int pay = job.getHours() * staff.getHourlyRate();
            account += pay;
            staff.setState(StaffState.ONLEAVE); // they go "on holiday"
            outcome += "\nJob completed by " + staff.getName() +
                       ". Earned " + pay +
                       "\nAccount = " + account;
            if (account < 0) outcome += "\nITProject is overdrawn";
            return outcome;
        } else {
            account -= job.getPenalty();
            outcome += "\nJob not completed due to staff inexperience (" + staff.getName() + ")" +
                       "\nPenalty of " + job.getPenalty() + " applied. Account = " + account;
            if (account < 0) outcome += "\nITProject is overdrawn";
            return outcome;
        }
    }

    // No suitable staff available
    account -= job.getPenalty();
    outcome += "\nJob not completed as no staff available. Penalty of " + job.getPenalty() + " applied." +
               "\nAccount = " + account;
    if (account < 0) outcome += "\nITProject is overdrawn";
    return outcome;
}

    /**Staff rejoin the team after holiday by setting state to "working" 
     * @param name the name of the staff rejoining the team after leave
     */
    public String staffRejoinTeam(String name)
    {   
          Staff s = team.get(name);
        if (s != null && s.getState() == StaffState.ONLEAVE) {
            s.setState(StaffState.WORKING);
            return name + " has rejoined the team from holiday.";
        }
        return name + " not in team so can't return from holiday";
    }
    //****************** private methods for Task 6.1 functionality*******************

    private void setupStaff()
{
   
    // Staff as per Appendix A with retainer for each member
    allStaff.put("Amir", new Planner("Amir", 2, 300, "Homebase"));    // hourly: 2*15=30
    allStaff.put("Bela", new Consultant("Bela", 2, 100, false));      // hourly: 30
    allStaff.put("Ceri", new Consultant("Ceri", 4, 250, true));       // hourly: 40
    allStaff.put("Dana", new Installer("Dana", 2, 200, false));       // hourly: 20
    allStaff.put("Eli", new Installer("Eli", 7, 200, true));          // hourly: 20
    allStaff.put("Firat", new Planner("Firat", 6, 300, "Hygena"));    // hourly: 6*15=90
    allStaff.put("Gani", new Installer("Gani", 2, 200, true));        // hourly: 20
    allStaff.put("Hui", new Consultant("Hui", 8, 450, true));         // hourly: 40
    allStaff.put("Jaga", new Planner("Jaga", 4, 300, "Homebase"));    // hourly: 4*15=60
}
   
    
private void setupJobs() {
    Job job1000 = new Job(1000, JobType.DESIGN, "Kitchen", 3, 10, 200);
    Job job1001 = new Job(1001, JobType.MAINTENANCE, "Lounge", 3, 20, 150);
    Job job1002 = new Job(1002, JobType.INSTALLATION, "Kitchen", 3, 30, 100);
    Job job1003 = new Job(1003, JobType.DESIGN, "Bathroom", 9, 25, 250);
    Job job1004 = new Job(1004, JobType.INSTALLATION, "Lounge", 7, 15, 350);
    Job job1005 = new Job(1005, JobType.MAINTENANCE, "Kitchen", 8, 35, 300);
    Job job1006 = new Job(1006, JobType.MAINTENANCE, "Bathroom", 5, 20, 400);
    Job job1007 = new Job(1007, JobType.INSTALLATION, "Bathroom", 1, 5, 120);
    Job job1008 = new Job(1008, JobType.DESIGN, "Kitchen", 1, 8, 175);

    jobs.put(1000, job1000);
    jobs.put(1001, job1001);
    jobs.put(1002, job1002);
    jobs.put(1003, job1003);
    jobs.put(1004, job1004);
    jobs.put(1005, job1005);
    jobs.put(1006, job1006);
    jobs.put(1007, job1007);
    jobs.put(1008, job1008);
}
    
    
    // may be helpful
    private Job getAJob(int num)
    {
        return jobs.get(num);
    }
   
    private Staff getStaffForJob(Job job)
     {
        for (Staff staff : team.values()) {
            if (staff.getState() == StaffState.WORKING && staff.canDoJob(job)) {
                return staff;
            }   
        }
    return null;
    }
 
    // ***************   file write/read  *********************
    /** Writes the Manager object to the specified file using serialization
     * @param filename name of file to which schedule is written
     */
    public void saveManager(String filename)
    {// uses object serialisation 
       try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    /** Reads Manager from specified file using serialization and restores it
     * @param filename name of file from which schedule is read
     * @return the whole Manager object
     */
    public Manager restoreManager(String filename)
    {   // uses object serialisation 
         Manager restoredManager = null;
         try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
               restoredManager = (Manager) in.readObject();
         } catch (IOException | ClassNotFoundException e) {
               e.printStackTrace();
         }
         return restoredManager;
    } 
    
    /** Reads jobs from a text file such as "jobs.txt" - provided
    *  @filename - name of the text file
    */
  public void readJobs(String fname) { 
    jobs.clear();
    int jobNo = 1000; // Or any starting value you wish
    try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
        String line;
        while ((line = br.readLine()) != null) {
            // Now expect: type,location,expReq,hours,penalty
            String[] parts = line.split(",");
            if (parts.length == 5) {;
                JobType type = JobType.valueOf(parts[0].trim().toUpperCase());
                String loc = parts[1].trim();
                int exp = Integer.parseInt(parts[2].trim());
                int hrs = Integer.parseInt(parts[3].trim());
                int pen = Integer.parseInt(parts[4].trim());
                jobs.put(jobNo, new Job(jobNo, type, loc, exp, hrs, pen));
                jobNo++;    // increment for next job
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}    
    }
