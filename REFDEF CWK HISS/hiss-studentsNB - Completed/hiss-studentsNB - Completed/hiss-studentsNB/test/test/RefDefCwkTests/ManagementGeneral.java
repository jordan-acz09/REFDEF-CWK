/*
 * To change this license header, choose License Headers in Manager Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.RefDefCwkTests;

import refdefcwk.Manager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import refdefcwk.HISS;

/**
 * Provides tests for the basic HISS setup
 * @author comqaam
 */
public class ManagementGeneral {
    
    HISS pr;
    
    // Just some local methods
    private boolean containsText(String text, List<String> str) {
        boolean result = true;
        for (String s : str) {
            result = result && text.toLowerCase().contains(s.toLowerCase());
        }
        return result;
    }
    
    private boolean containsText2(String text, String s1, String s2, String s3) {
        boolean result = false;
        result = text.contains(s1) && text.contains(s2) && text.contains(s3) ;
        return result;
    }
    
    public ManagementGeneral() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //create the Manager object used before each test method
        pr = new Manager("Olenka",1000);
    }
    
    @After
    public void tearDown() {
    }

//***** Tests for Manager just after creation
    
    @Test
    public void checkAccountAtStart() {
        //Checks that account is set to the parameter in setUp() above
        assertEquals(1000.0, pr.getAccount(), 0.5);
    }
    
    @Test
    public void checkTeamAtStart() {
        //Checks that team is empty at the start
        assertTrue((pr.getTeam().contains("No staff hired")));
    } 
    
    @Test
    public void isNotOverdrawnAtStart() {
        assertTrue(!pr.isOverdrawn());
    }
    
// ************ Jobs **********
    
    @Test
    public void isInJobRange() {
       // Checks that jobs have been loaded and can retrieve a job
       boolean actual = true;
       for (int x=1000; x<=106; x++){
           actual = actual && pr.isJob(x);
       }
       assertTrue(actual);
    }
       
    @Test
    public void isNotInJobRange() {
       // Checks correct response to out of range job numbers
       int x = 99;
       boolean actual = !pr.isJob(x);
       assertTrue(actual);
    }
    
    
// Just checking getAllJobs for a few of the Jobs
    @Test
    public void Job1000InAllJobs() {
        String str = pr.getJob(1000);
        boolean result = containsText2(str,"Design","3","10");
        assertTrue(result);
    }
    
    @Test
    public void Job1003InAllJobs() {
        String str = pr.getJob(1003);
        
        boolean result2 = containsText2(str,"Design","9", "25");
        assertTrue(result2);
    }
    
    @Test
    public void Job1007InAllJobs() {
        String str = pr.getAllJobs();
        boolean result3 = containsText2(str,"Maintenance","8","35");
        assertTrue(result3);
    }
    
//************** Staff *********************
// Checks just a few staff details 
    @Test
    public void checkDetailsOfAmir() {
        String mem = pr.getStaff("Amir");
        boolean result = containsText(mem,new ArrayList<>(Arrays.asList(
                        "Amir","Planner", "2","300","30", "Homebase" )));
        assertTrue(result); 
    }

    @Test
    public void checkDetailsOfJaga() {
        String str = pr.getStaff("Jaga");
        boolean result = containsText(str,new ArrayList<>(Arrays.asList(
                        "Jaga","Planner","4", "300","60", "Homebase" )));
        assertTrue(result); 
    }

    @Test
    public void checkDetailsOfBela() {
        String str = pr.getStaff("Bela");
        boolean result = containsText(str,new ArrayList<>(Arrays.asList(
                        "Bela","Consultant","2", "100","30","Available", "false" )));
        assertTrue(result); 
    }

    @Test
    public void checkDetailsOfCeri() {
        String str = pr.getStaff("Ceri");
        boolean result = containsText(str,new ArrayList<>(Arrays.asList(
                        "Ceri","Consultant","4", "250","40","Available", "true" )));
        assertTrue(result); 
    }

    @Test
    public void checkDetailsOfEli() {
        String str = pr.getStaff("Eli");
        boolean result = containsText(str,new ArrayList<>(Arrays.asList(
                        "Eli","Installer","7", "200","20","Available" )));
        assertTrue(result); 
    }

    @Test
    public void checkDetailsOfNonExistantJohn() {
        // Checks for non-existent John
        String str = pr.getStaff("John");
        boolean result = containsText(str,new ArrayList<>(Arrays.asList(
                        "No such staff" )));
        assertTrue(result); 
    } 
    
    @Test
    public void checkAvailableStaffAtStart() {
        // Checks getAllAvailableStaff()
        String str = pr.getAllAvailableStaff();
        boolean result = containsText(str,new ArrayList<>(Arrays.asList(
                        "Amir","Bela",
                    "Ceri","Dana","Eli","Firat","Gani","Hui","Jaga" )));
        assertTrue(result); 
    }          
}