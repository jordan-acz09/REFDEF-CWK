/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package refdefcwk;

/**
 *
 * @author Jordan A
 *version - 20/06/25
 */
public class Planner extends Staff {
    private String make;

    public Planner(String name, int experience, int retainer, String make) {
        super(name, experience, retainer, experience * 15);
        this.make = make;
    }

    @Override
    public boolean canDoJob(Job job) {
        return job.getType() == JobType.DESIGN;
    }

    @Override
    public String toString() {
        return name + ",Planner," + experience + "," + retainer + "," + getHourlyRate() + ",Available," + make;
    }
}
