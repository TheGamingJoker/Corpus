import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonalDetailsTest
{
    /**
     * Default constructor for test class PersonalDetailsTest
     */
    public PersonalDetailsTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }


    @Test
    public void testCreatePersonalDetails()
    {
        Database database2 = new Database();
        Authentication authenti1 = new Authentication();
        PersonalDetails personal1 = new PersonalDetails();
        personal1.createPersonalDetails();
    }

    @Test
    public void testReadPersonalDetails()
    {
        Database database1 = new Database();
        Authentication authenti1 = new Authentication();
        PersonalDetails personal1 = new PersonalDetails();
        personal1.createPersonalDetails();
        authenti1.logOut();
        authenti1.userLogin();
        personal1.readPersonalDetails();
    }

    @Test
    public void testAmendPersonalDetails()
    {
        Database database1 = new Database();
        Authentication authenti1 = new Authentication();
        PersonalDetails personal2 = new PersonalDetails();
        personal2.createPersonalDetails();
        personal2.amendPersonalDetails();
    }
}




