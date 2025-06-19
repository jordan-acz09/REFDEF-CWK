package test.RefDefCwkTests;

import refdefcwk.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Provides tests for the basic HISS setup
 * @author comqaam
 */
public class ManagementJobsDoing {
    
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
    
     private boolean containsText3(String text, String s1) {
        boolean result = false;
        result = text.contains(s1) ;
        return result;
    }
    
    public ManagementJobsDoing() {
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
    
// Check if staff can do Jobs
    @Test
    public void plannerNoSuchJob()
    {
        //should not be eligible
        pr.hireStaff("Amir");
        String done = (pr.doJob(1020)).toLowerCase();
        System.out.println(done);
        boolean result = containsText3(done, "no such" );
        assertTrue(result);      
    }
    
    
    @Test
    public void plannerDoingDesign()
    {
        //should be eligible
        pr.hireStaff("Firat");
        String done = (pr.doJob(1000)).toLowerCase();
        boolean result = containsText3(done, "completed by" );
        assertTrue(result);      
    }
    
    @Test
    public void plannerDoingDesignMoney()
    {
       //should be eligible 
        pr.hireStaff("Firat");
        pr.doJob(1000);
        double money =pr.getAccount();
        double expected = 1000 - 300 + 90 * 10;
        assertEquals(expected,money,.5);      
    }
    
    @Test
    public void plannerNotDoingInstallation()
    {
        //should be eligible
        pr.hireStaff("Amir");
        String done = (pr.doJob(1002)).toLowerCase();
        boolean result = containsText3(done, "no staff" );
        assertTrue(result);      
    }
    
    @Test
    public void plannerNotDoingInstallationMoney()
    {
       //should be eligible 
        pr.hireStaff("Amir");
        pr.doJob(1000);
        double money =pr.getAccount();
        double expected = 1000 - 300 - 200;
        assertEquals(expected,money,.5);      
    }

    @Test
    public void plannerNotDoingMaintenance()
    {
        //should be eligible
        pr.hireStaff("Amir");
        String done = (pr.doJob(1001)).toLowerCase();
        boolean result = containsText3(done, "no staff" );
        assertTrue(result);      
    }
    
    @Test
    public void plannerNotDoingMaintenanceMoney()
    {
       //should be eligible 
        pr.hireStaff("Amir");
        pr.doJob(1001);
        double money =pr.getAccount();
        double expected = 1000 - 300 - 150;
        assertEquals(expected,money,.5);      
    }

    @Test
    public void plannerDoingDesignInexperience()
    {
        //should be eligible
        pr.hireStaff("Amir");
        String done = (pr.doJob(1000)).toLowerCase();
        boolean result = containsText3(done, "staff inexperience" );
        assertTrue(result);      
    }
    
    @Test
    public void plannerDoingDesignInexpMoney()
    {
        //should be eligible
        pr.hireStaff("Amir");
        pr.doJob(1000);
        double money =pr.getAccount();
        double expected = 1000 - 300 - 200;
        assertEquals(expected,money,.5);         
    }
    
    @Test
    public void consultantDoingDesign()
    {
        //should be eligible
        pr.hireStaff("Hui");
        String done = (pr.doJob(1000)).toLowerCase();
        boolean result = containsText3(done, "completed by" );
        assertTrue(result);      
    }
    
    @Test
    public void consultantDoingDesignMoney()
    {
        //should be eligible
        pr.hireStaff("Hui");
        pr.doJob(1000);
        double money =pr.getAccount();
        double expected = 1000 - 450 + 40*10;
        assertEquals(expected,money,.5);         
    }
    
    @Test
    public void consultantDoingDesignInexp()
    {
        //should be eligible
        pr.hireStaff("Bela");
        String done = (pr.doJob(1000)).toLowerCase();
        boolean result = containsText3(done, "inexperience" );
        assertTrue(result);      
    }
    
    @Test
    public void consultantDoingDesignMoneyInexp()
    {
        //should be eligible
        pr.hireStaff("Bela");
        pr.doJob(1000);
        double money =pr.getAccount();
        double expected = 1000 - 100 -200;
        assertEquals(expected,money,.5);         
    }
 
       @Test
    public void installerDoingDesign()
    {
        //should be eligible
        pr.hireStaff("Eli");
        String done = (pr.doJob(1002)).toLowerCase();
        boolean result = containsText3(done, "completed by" );
        assertTrue(result);      
    }
    
    @Test
    public void installerDoingInstalMoney()
    {
        //should be eligible
        pr.hireStaff("Eli");
        pr.doJob(1002);
        double money =pr.getAccount();
        double expected = 1000 - 200 + 20*30;
        assertEquals(expected,money,.5);         
    }
    
    @Test
    public void installerDoingInstalInexp()
    {
        //should be eligible
        pr.hireStaff("Dana");
        String done = (pr.doJob(1002)).toLowerCase();
        boolean result = containsText3(done, "inexperience" );
        assertTrue(result);      
    }
    
    @Test
    public void installerDoingInstalMoneyInexp()
    {
        //should be eligible
        pr.hireStaff("Dana");
        pr.doJob(1002);
        double money =pr.getAccount();
        double expected = 1000 - 200 -100;
        assertEquals(expected,money,.5);         
    }
    
    @Test
    public void installerNotDoingDesign()
    {
        //should be eligible
        pr.hireStaff("Dana");
        String done = (pr.doJob(1000)).toLowerCase();
        boolean result = containsText3(done, "no staff" );
        assertTrue(result);      
    }
    
    @Test
    public void installerNotDoingDesignMoney()
    {
        //should be eligible
        pr.hireStaff("Dana");
        pr.doJob(1000);
        double money =pr.getAccount();
        double expected = 1000 - 200 - 200;
        assertEquals(expected,money,.5);         
    }
   
    @Test
    public void installerDoingMaint()
    {
        //should be eligible
        pr.hireStaff("Eli");
        String done = (pr.doJob(1001)).toLowerCase();
        boolean result = containsText3(done, "completed" );
        assertTrue(result);      
    }
    
   @Test
    public void installerNotDoingMaint()
    {
        //should be eligible
        pr.hireStaff("Dana");
        String done = (pr.doJob(1001)).toLowerCase();
        boolean result = containsText3(done, "no staff" );
        assertTrue(result);      
    }
    
    @Test
    public void installerDoingMaintInexp()
    {
        //should be eligible
        pr.hireStaff("Gani");
        String done = (pr.doJob(1001)).toLowerCase();
        boolean result = containsText3(done, "inexperience" );
        assertTrue(result);      
    }
}

