/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refdefcwk;

/**
 *
 * @author ja22aaj
 */
public class Apprentice extends Staff {
    private String mentorName;

    
    public Apprentice(String name, int experience, int retainer, int hourlyRate, String mentorName) {
        super(name, experience, retainer, hourlyRate);
        this.mentorName = mentorName; 
    }

    @Override
    public boolean canDoJob(Job job) {
        return true;
    }
    
    @Override
    public String toString(){
        return name + " ,Apprentice, " + experience + "," 
                + retainer + "," + getHourlyRate() + ",Available, " + " This apprentice's mentor is: " + mentorName;
    }
    
}
