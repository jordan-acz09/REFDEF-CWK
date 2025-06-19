package refdefcwk; 
import java.io.*;
/**
 * Enumeration class JobType - write a description of the enum class here
 * The JobType enum defines the different types of jobs that can be assigned within the system.
 * @author (Jordan A)
 * @version (19/06/25)
 */
public enum JobType implements Serializable
{
    DESIGN(" Design"), INSTALLATION(" Installation"), MAINTENANCE (" Maintenance");
    private String type;
    
    private JobType(String ty)
    {
        type = ty;
    }
    
    public String toString()
    {
        return type;
    }
}
