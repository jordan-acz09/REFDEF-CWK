/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refdefcwk;
import java.io.Serializable;
/**
 *
 * @author Ja22aaj
 */
public abstract class Staff implements Serializable {
    protected String name;
    protected int experience;
    protected int retainer;
    protected int hourlyRate;
    protected StaffState state;

    public Staff(String name, int experience, int retainer, int hourlyRate) {
        this.name = name;
        this.experience = experience;
        this.retainer = retainer;
        this.hourlyRate = hourlyRate;
        this.state = StaffState.AVAILABLE;
    }

    public String getName() { return name; }
    public int getExperience() { return experience; }
    public int getRetainer() { return retainer; }
    public int getHourlyRate() { return hourlyRate; }
    public StaffState getState() { return state; }
    public void setState(StaffState s) { this.state = s; }

    public abstract boolean canDoJob(Job job);

    public String toString() {
        return name + " [" + getClass().getSimpleName() + "], Exp: " + experience + ", State: " + state;
    }
}
