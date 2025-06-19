/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refdefcwk;

/**
 *
 * @author Ja22aaj
 */
public class Installer extends Staff {
    private boolean corgiTrained;

    public Installer(String name, int experience, int retainer, boolean corgiTrained) {
        super(name, experience, retainer, 20);
        this.corgiTrained = corgiTrained;
    }

    @Override
    public boolean canDoJob(Job job) {
        if (job.getType() == JobType.INSTALLATION) return true;
        if (job.getType() == JobType.MAINTENANCE && corgiTrained) return true;
        return false;
    }

    @Override
    public String toString() {
        return name + ",Installer," + experience + "," + retainer + "," + getHourlyRate() + ",Available";
    }
}