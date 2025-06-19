package refdefcwk; 
import java.io.*;
/**
 * Enumeration class StaffState - write a description of the enum class here
 * The StaffState enum defines the possible states that a staff member can be in within the HISS system, it represents and manages the status of staffs.
 * @author (Jordan A)
 * @version (18/06/2025)
 */
public enum StaffState implements Serializable
{
    AVAILABLE(" Available"), WORKING (" Working"), ONLEAVE(" On leave");
    private String state;
    
    private StaffState(String st)
    {
        state = st;
    }
    
    public String toString()
    {
        return state;
    }
}
