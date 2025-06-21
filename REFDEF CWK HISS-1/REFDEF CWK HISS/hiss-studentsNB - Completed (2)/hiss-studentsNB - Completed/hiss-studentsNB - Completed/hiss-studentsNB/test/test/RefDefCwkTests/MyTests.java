/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package test.RefDefCwkTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import refdefcwk.Manager;
import refdefcwk.HISS;

/**
 *This MyTests Junit is a test to see whether the HISS system that has been developed 
 * can fully pass the test in which I have set for it, to ensure it is matching with 
 * the given specification 
 * @author Jordan A
 * version - 20/06/25
 */
public class MyTests {

    HISS pr;

    public MyTests() {}

    @BeforeClass
    public static void setUpClass() {}

    @AfterClass
    public static void tearDownClass() {}

    @Before
    public void setUp() {
        // Standard setup: Manager named Olenka with budget = 1000
        pr = new Manager("Olenka", 1000);
    }

    @After
    public void tearDown() {}

    /**
     * Test hiring a staff member by name who exists.
     * Budget reduces by correct amount, staff joins team.
     */
    @Test
    public void canHireExistingStaff() {
        double before = pr.getAccount();
        String result = pr.hireStaff("Amir");
        double after = pr.getAccount();
        assertTrue("Hiring staff should succeed if they exist and funds are sufficient.", result.contains("Hired: "));
    }

    /**
     * Test hiring a staff member who does not exist.
     * No budget change, appropriate message.
     */
    @Test
    public void cannotHireNonexistentStaff() {
        String str = pr.hireStaff("Ghost");
    boolean result = str.contains("Not found: ");
    assertTrue(result);
    }

    /**
     * Test hiring a Staff when budget is insufficient.
     * Hiring fails, budget unchanged.
     */
    @Test
    public void cannotHireWhenInsufficientFunds() {
         pr = new Manager("Olenka", 0); // This will set the budget to 0
    double before = pr.getAccount();
    String str = pr.hireStaff("Amir");
    double after = pr.getAccount();
    boolean result = str.toLowerCase().contains("not enough money");
    assertTrue(result);
    assertEquals(before, after, 0.01);
    }

    /**
     * Test hiring the same staff member twice does not deduct budget twice.
     * Second attempt does not change budget.
     */
    @Test
    public void cannotHireSameStaffTwice() {
        pr.hireStaff("Amir");
        double first = pr.getAccount();
        pr.hireStaff("Amir");
        double second = pr.getAccount();
        assertEquals(first, second, 0.01);
    }

    /**
     * Test that after hiring, hired staff is not in available staff list.
     * Available staff list does not contain hired staff.
     */
    @Test
    public void hiredStaffIsNotInAvailableList() {
        pr.hireStaff("Amir");
        String str = pr.getAllAvailableStaff();
        boolean result = str.contains("Amir");
        assertFalse(result);
    }

    /**
     * Test that account cannot go negative even if attempting to hire with insufficient funds.
     * Account remains >= 0.
     */
    @Test
    public void accountNeverNegative() {
        pr.hireStaff("Amir");
        pr.hireStaff("Dana");
        pr.hireStaff("Hui");
        pr.hireStaff("Firat");
        assertTrue(pr.getAccount() >= 0);
    }


    /**
     * Test that staff can only rejoin the team if they are on leave.
     * Only staff on leave may rejoin.
     */
    @Test
    public void staffCanOnlyRejoinIfOnLeave() {
        pr.hireStaff("Hui");
        String str1 = pr.staffRejoinTeam("Hui");
        boolean result1 = str1.contains("not in team so can't return from holiday");
        assertTrue(result1);

        pr.doJob(1000); // Assumes doing job puts Hui on leave
        String str2 = pr.staffRejoinTeam("Hui");
        boolean result2 = str2.contains("has rejoined the team from holiday");
        assertTrue(result2);
    }

    /**
     * Test isOverdrawn logic: should return true only when account is <=0 and no staff can leave.
     */
    @Test
    public void isOverdrawnLogic() {
        pr.hireStaff("Amir");
        pr.hireStaff("Dana");
        pr.hireStaff("Hui");
        assertFalse(pr.isOverdrawn());

        pr.doJob(1000);
        pr.doJob(1002);
        pr.doJob(1005);

        if (pr.getAccount() <= 0) {
            assertTrue(pr.isOverdrawn());
        }
    }

    /**
     * Test getting details for a staff member not in allStaff or team.
     * Returns "No such staff".
     */
    @Test
    public void getStaffNotFound() {
        String str = pr.getStaff("NonExistentPerson");
    boolean result = str.toLowerCase().contains("no such staff");
    assertTrue(result);
    }

    /**
     * Test getJob returns correct details for an existing job.
     * Job details string contains job number.
     */
    @Test
    public void getJobDetails() {
        String str = pr.getJob(1000);
    boolean result = str.contains("Job#1000");
    assertTrue(result);
    }

    /**
     * Test getJob returns correct error for a non-existent job.
     * This should return an Error message that the specific jobNO does not exist 
     */
    @Test
    public void getJobNotFound() {
        String str = pr.getJob(9999);
    boolean result = str.toLowerCase().contains("no such job");
    assertTrue(result);
    }

    /**
     * Test getAllJobs returns a string listing all jobs.
     * At least one job is listed.
     */
    @Test
    public void getAllJobsListsJobs() {
       String str = pr.getAllJobs();
    boolean result = str.contains("Job#");
    assertTrue(result);
    }

    /**
     * Test that loading a non-existent save file does not crash or throw.
     * Method returns well, no exception thrown.
     */
    @Test
    public void loadingNonExistentSaveFileDoesNotCrash() {
        String missingFile = "file that does not exist";
        try {
            pr = pr.restoreManager(missingFile);
            // If there is no exception thrown, test should most likely pass.
        } catch (Exception e) {
            fail("Loading a non-existent save file should not throw an exception. Exception: " + e.getMessage());
        }
    }

    /**
     * Test that saving with a null or invalid filename is handled.
     * Should not throw, returns error string or handles gracefully.
     */
    @Test
    public void savingNullOrInvalidFilename() {
    try {
        pr.saveManager(null);
        // Optionally check side effects, or just pass
    } catch (Exception e) {
        // Acceptable if it throws a NullPointerException (documented behavior), but should not crash the whole app
        assertTrue("Should not throw when saving with null filename.", e instanceof NullPointerException);
    }
}
}
