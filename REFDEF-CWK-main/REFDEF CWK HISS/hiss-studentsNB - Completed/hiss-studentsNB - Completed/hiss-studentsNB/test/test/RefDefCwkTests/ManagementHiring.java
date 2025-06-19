package test.RefDefCwkTests; 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import refdefcwk.*;

public class ManagementHiring
{
    HISS pr;
    public ManagementHiring() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pr = new Manager("Olenka",1000);
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void hireStaff(){
        int expected = 700;
        pr.hireStaff("Amir");
        assertTrue(expected == pr.getAccount());
    }
    
    @Test
    public void hiredStaffInTeam() {
        int expected = 600;
        pr.hireStaff("Amir");
        pr.hireStaff("Bela");
        boolean budgetOK = (expected == pr.getAccount());
        assertTrue(pr.isInTeam("Amir") && pr.isInTeam("Bela")&& budgetOK);
    }
    
    @Test
    public void hiredStaffNotAvailable() {
        boolean result = true;
        pr.hireStaff("Amir");
        pr.hireStaff("Bela");
        String actual = pr.getAllAvailableStaff();
        result = !(actual.contains("Amir") || actual.contains("Bela"));
        assertTrue(result);
    }
    
    @Test 
    public void notEnoughMoney() {
        pr.hireStaff("Amir");
        pr.hireStaff("Dana");
        pr.hireStaff("Hui");
        pr.hireStaff("Firat");
        boolean result = (pr.getAccount()==50);
        result = result && !pr.isInTeam("Firat");
        assertTrue(result);
    }
    
    @Test 
    public void notSuchStaff() {
        pr.hireStaff("John");
        boolean result = (pr.getAccount()==1000);
        result = result && !pr.isInTeam("John");
        assertTrue(result);
    }
}
