package refdefcwk; 
import java.io.*;
import java.util.*;
/**
 * provides a command line interface
 * 
 * @author A.A.Marczyk
 * @version 20/05/25
 */
public class ManagerUI
{
    private static HISS man1;
    private static Scanner myIn = new Scanner(System.in);
    
  public static void main(String[] args)
    {
        int choice;

        try
        {
            System.out.println("Enter manager's name");
            String mg = myIn.nextLine();
            man1 = new Manager(mg, 1000, "jobs.txt"); // use concrete class as implementation
            choice = 100;
            while (choice != 0)
            {
                choice = getMenuItem();
                if (choice == 1)
                {
                    System.out.println(man1.toString());
                }
                else if (choice == 2)
                {
                    System.out.println(man1.getAllAvailableStaff());
                }
                else if (choice == 3)
                {
                    System.out.println("Enter Staff name");
                    String nme = (myIn.nextLine()).trim();
                    if (!man1.isOverdrawn())
                    {
                        System.out.println(man1.hireStaff(nme));
                    }
                    else
                    {
                        System.out.println("Project is overdrawn");
                    }
                } 
                else if (choice == 4)
                {
                    System.out.println("Staff currently in team:");
                    System.out.println(man1.getTeam());
                }
                else if (choice == 5)
                {
                    System.out.println(man1.getAllJobs());
                }
                else if (choice == 6)
                {
                    // Option 6: Do a job
                    System.out.println(man1.getAllJobs());
                    System.out.println("Enter job number (e.g., 1000):");
                    int jobNo = -1;
                    try {
                        jobNo = Integer.parseInt(myIn.nextLine().trim());
                        String result = man1.doJob(jobNo);
                        System.out.println(result);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid job number.");
                    }
                }
                else if (choice == 7)
                {
                    System.out.println("Enter Staff name");
                    String nme = (myIn.nextLine()).trim();
                    if (man1.isInTeam(nme))
                    {
                        System.out.println(man1.staffRejoinTeam(nme));
                    }
                }
                else if (choice == 8)
                {
                    System.out.println("Write to file");
                    System.out.println("Enter filename");
                    String filname = (myIn.nextLine()).trim();
                    man1.saveManager(filname);
                }
                else if (choice == 9)
                {
                    System.out.println("Restore from file");
                    System.out.println("Enter filename");
                    String fname = (myIn.nextLine()).trim();
                    man1 = man1.restoreManager(fname);
                    System.out.println(man1.getTeam());
                }
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("Thank-you");
    }

    private static int getMenuItem() throws IOException
    {
        int choice = 100;
        System.out.println("Main Menu");
        System.out.println("0. quit");
        System.out.println("1. list project information");
        System.out.println("2. list staff available for hire");
        System.out.println("3. hire staff for team");
        System.out.println("4. list staff in the team");
        System.out.println("5. list jobs ");
        System.out.println("6. do a job");
        System.out.println("7. staff rejoin team from leave");
        System.out.println("8. save game");
        System.out.println("9. restore game");

        while (choice < 0 || choice > 9)
        {
            System.out.println("Enter the number of your choice");
            choice = myIn.nextInt();
        }
        myIn.nextLine();
        return choice;
    }
}