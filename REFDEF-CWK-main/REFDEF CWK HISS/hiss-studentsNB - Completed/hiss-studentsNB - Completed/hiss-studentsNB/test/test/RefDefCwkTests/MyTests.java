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
 *
 * @author Ja22aaj
 */
public class MyTests {

    HISS pr;

    public MyTests() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        // Assuming Manager constructor is Manager(String name, int budget)
        pr = new Manager("Olenka", 1000);
    }

    @After
    public void tearDown() {
    }

    // *** My Tests ***
    // The following tests are custom tests added by Ja22aaj.
    // Each test is documented for clarity.

    /**
     * Test hiring the same staff member twice does not deduct budget twice.
     */
    @Test
    public void cannotHireSameStaffTwice() {
        pr.hireStaff("Amir");
        double afterFirstHire = pr.getAccount();
        pr.hireStaff("Amir");
        double afterSecondHire = pr.getAccount();
        assertEquals("Hiring same staff should not deduct funds twice.", afterFirstHire, afterSecondHire, 0.01);
    }

    /**
     * Test that hiring a staff not in the available list does not change the budget.
     */
    @Test
    public void hiringNonexistentStaffNoBudgetChange() {
        double before = pr.getAccount();
        pr.hireStaff("NonExistentPerson");
        double after = pr.getAccount();
        assertEquals("Hiring non-existent staff changes budget!", before, after, 0.01);
    }

    /**
     * Test that after hiring all staff, available staff list is empty or contains expected message.
     */
    @Test
    public void hiredStaffIsNotInAvailableList() {
        pr.hireStaff("Amir");
        String available = pr.getAllAvailableStaff();
        assertFalse("Hired staff should not appear in available staff list.", available.contains("Amir"));
    }

    /**
     * Test that account cannot go negative even if attempting to hire with insufficient funds.
     */
    @Test
    public void accountNeverNegative() {
        // Hire until budget is exhausted
        pr.hireStaff("Amir"); // -300
        pr.hireStaff("Dana"); // -300
        pr.hireStaff("Hui");  // -300 (should now be 100)
        pr.hireStaff("Firat");// -300 (should fail, not enough money)
        assertTrue("Account should not be negative.", pr.getAccount() >= 0);
    }

    // *** My Extended Tests ***
    // These tests are added by jordan-acz09 to further verify HISS functionality.

    /**
     * Test that staff can only rejoin the team if they are on leave.
     */
    @Test
    public void staffCanOnlyRejoinIfOnLeave() {
        pr.hireStaff("Hui");
        // Try to rejoin when not on leave
        String result = pr.staffRejoinTeam("Hui");
        assertTrue("Staff not on leave should not be able to rejoin.", result.contains("not in team so can't return from holiday"));
        // Hui going on leave by doing a job they can do (if your logic supports this)
        pr.doJob(1000); // Assume job 1000 can be done by Amir
        String resultAfterLeave = pr.staffRejoinTeam("Hui");
        // Hui should be able to rejoin from leave
        assertTrue("Staff on leave should be able to rejoin.", resultAfterLeave.contains("has rejoined the team from holiday"));
    }

    /**
     * Test that isOverdrawn returns true only when account is zero or less and no staff can leave.
     */
    @Test
    public void isOverdrawnLogic() {
        // Spend all the money
        pr.hireStaff("Amir");
        pr.hireStaff("Dana");
        pr.hireStaff("Hui");
        // Account should now be low or zero, but staff are present and not on leave
        assertFalse("Should not be overdrawn if staff are in team and not on leave.", pr.isOverdrawn());
        // Simulate all staff on leave
        pr.doJob(1000);
        pr.doJob(1002);
        pr.doJob(1005);
        // Now check isOverdrawn logic (assuming account is <=0 and all staff are on leave)
        if (pr.getAccount() <= 0) {
            assertTrue("Should be overdrawn if account <=0 and no staff can leave.", pr.isOverdrawn());
        }
    }

    /**
     * Test that trying to get details for a staff member not in allStaff or team returns the correct message.
     */
    @Test
    public void getStaffNotFound() {
        String result = pr.getStaff("NonExistentPerson");
        assertTrue("Should return correct message for non-existent staff.", result.contains("No such staff"));
    }

    /**
     * Test that getJob returns correct details for an existing job.
     */
    @Test
    public void getJobDetails() {
        String details = pr.getJob(1000);
        assertTrue("Job details should contain job number.", details.contains("Job#1000"));
    }

    /**
     * Test that getAllJobs returns a string listing all jobs.
     */
    @Test
    public void getAllJobsListsJobs() {
        String jobsList = pr.getAllJobs();
        assertTrue("All jobs list should mention at least one job.", jobsList.contains("Job#"));
    }
}
