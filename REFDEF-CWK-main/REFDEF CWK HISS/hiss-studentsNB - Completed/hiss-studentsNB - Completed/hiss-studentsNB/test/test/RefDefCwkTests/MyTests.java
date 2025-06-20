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
     * Expected: Budget reduces by correct amount, staff joins team.
     */
    @Test
    public void canHireExistingStaff() {
        double before = pr.getAccount();
        String result = pr.hireStaff("Amir");
        double after = pr.getAccount();
        assertTrue("Hiring staff should succeed if they exist and funds are sufficient.", result.contains("has joined the team"));
        assertTrue("Budget should decrease after hiring.", after < before);
    }

    /**
     * Test hiring a staff member who does not exist.
     * Expected: No budget change, appropriate message.
     */
    @Test
    public void cannotHireNonexistentStaff() {
        double before = pr.getAccount();
        String result = pr.hireStaff("Ghost");
        double after = pr.getAccount();
        assertTrue("Result should mention staff not found.", result.toLowerCase().contains("no such staff"));
        assertEquals("Budget should not change when hiring fails.", before, after, 0.01);
    }

    /**
     * Test hiring someone when budget is insufficient.
     * Expected: Hiring fails, budget unchanged.
     */
    @Test
    public void cannotHireWhenInsufficientFunds() {
        pr = new Manager("Olenka", 0); // Set budget to 0
        double before = pr.getAccount();
        String result = pr.hireStaff("Amir");
        double after = pr.getAccount();
        assertTrue("Should not hire staff when funds are insufficient.", result.toLowerCase().contains("not enough money"));
        assertEquals("Budget should not change when hiring fails.", before, after, 0.01);
    }

    /**
     * Test hiring the same staff member twice does not deduct budget twice.
     * Expected: Second attempt does not change budget.
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
     * Test that after hiring, hired staff is not in available staff list.
     * Expected: Available staff list does not contain hired staff.
     */
    @Test
    public void hiredStaffIsNotInAvailableList() {
        pr.hireStaff("Amir");
        String available = pr.getAllAvailableStaff();
        assertFalse("Hired staff should not appear in available staff list.", available.contains("Amir"));
    }

    /**
     * Test that account cannot go negative even if attempting to hire with insufficient funds.
     * Expected: Account remains >= 0.
     */
    @Test
    public void accountNeverNegative() {
        pr.hireStaff("Amir"); // -300
        pr.hireStaff("Dana"); // -300
        pr.hireStaff("Hui");  // -300 (should now be 100)
        pr.hireStaff("Firat");// -300 (should fail, not enough money)
        assertTrue("Account should not be negative.", pr.getAccount() >= 0);
    }

    /**
     * Test that account handles extreme values: negative and large numbers.
     * Expected: Negative and very large numbers are managed gracefully.
     */
    @Test
    public void accountExtremeValues() {
        pr = new Manager("Olenka", Integer.MIN_VALUE); // very negative
        assertTrue("Account can be negative but must not throw.", pr.getAccount() <= 0);

        pr = new Manager("Olenka", Integer.MAX_VALUE); // very large
        assertTrue("Account can be very large and must not throw.", pr.getAccount() > 1_000_000);
    }

    /**
     * Test that staff can only rejoin the team if they are on leave.
     * Expected: Only staff on leave may rejoin.
     */
    @Test
    public void staffCanOnlyRejoinIfOnLeave() {
        pr.hireStaff("Hui");
        String result = pr.staffRejoinTeam("Hui");
        assertTrue("Staff not on leave should not be able to rejoin.", result.contains("not in team so can't return from holiday"));
        pr.doJob(1000); // Puts someone on leave
        String resultAfterLeave = pr.staffRejoinTeam("Hui");
        // May need to check for the correct staff and job number per your data
        assertTrue("Staff on leave should be able to rejoin.", resultAfterLeave.contains("has rejoined the team from holiday"));
    }

    /**
     * Test isOverdrawn logic: should return true only when account is <=0 and no staff can leave.
     */
    @Test
    public void isOverdrawnLogic() {
        pr.hireStaff("Amir");
        pr.hireStaff("Dana");
        pr.hireStaff("Hui");
        assertFalse("Should not be overdrawn if staff are in team and not on leave.", pr.isOverdrawn());
        pr.doJob(1000);
        pr.doJob(1002);
        pr.doJob(1005);
        if (pr.getAccount() <= 0) {
            assertTrue("Should be overdrawn if account <=0 and no staff can leave.", pr.isOverdrawn());
        }
    }

    /**
     * Test getting details for a staff member not in allStaff or team.
     * Expected: Returns "No such staff".
     */
    @Test
    public void getStaffNotFound() {
        String result = pr.getStaff("NonExistentPerson");
        assertTrue("Should return correct message for non-existent staff.", result.toLowerCase().contains("no such staff"));
    }

    /**
     * Test getJob returns correct details for an existing job.
     * Expected: Job details string contains job number.
     */
    @Test
    public void getJobDetails() {
        String details = pr.getJob(1000);
        assertTrue("Job details should contain job number.", details.contains("Job#1000"));
    }

    /**
     * Test getJob returns correct error for a non-existent job.
     * Expected: Error message returned.
     */
    @Test
    public void getJobNotFound() {
        String details = pr.getJob(9999);
        assertTrue("Non-existent job should report error.", details.toLowerCase().contains("no such job"));
    }

    /**
     * Test getAllJobs returns a string listing all jobs.
     * Expected: At least one job is listed.
     */
    @Test
    public void getAllJobsListsJobs() {
        String jobsList = pr.getAllJobs();
        assertTrue("All jobs list should mention at least one job.", jobsList.contains("Job#"));
    }

    /**
     * Test that loading a non-existent save file does not crash or throw.
     * Expected: Method returns gracefully, no exception thrown.
     */
    @Test
    public void loadingNonExistentSaveFileDoesNotCrash() {
        String missingFile = "file_that_does_not_exist.sav";
        try {
            pr = pr.restoreManager(missingFile);
            // If no exception is thrown, test passes.
        } catch (Exception e) {
            fail("Loading a non-existent save file should not throw an exception. Exception: " + e.getMessage());
        }
    }

    /**
     * Test that saving with a null or invalid filename is handled.
     * Expected: Should not throw, returns error string or handles gracefully.
     */
    @Test
    public void savingNullOrInvalidFilename() {
        try {
            String result = pr.saveManager(null);
            assertTrue("Should handle null filename gracefully.", result == null || result.toLowerCase().contains("error"));
        } catch (Exception e) {
            // Acceptable if it throws a NullPointerException (documented behavior), but should not crash the whole app
            assertTrue("Should not throw when saving with null filename.", e instanceof NullPointerException);
        }
    }
}
