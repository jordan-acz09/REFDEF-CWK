/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refdefcwk;

/**
 *
 * @author Ja22aaj
 */
public class Consultant extends Staff {
    private boolean corgiTrained;

    public Consultant(String name, int experience, int retainer, boolean corgiTrained) {
        super(name, experience, retainer, experience >= 4 ? 40 : 30);
        this.corgiTrained = corgiTrained;
    }

    @Override
    public boolean canDoJob(Job job) {
        return true; // Consultants can do all jobs
    }

    @Override
    public String toString() {
        // To match the test exactly:
        return name + ",Consultant," + experience + "," + retainer + "," + getHourlyRate() + ",Available," + corgiTrained;
    }
}