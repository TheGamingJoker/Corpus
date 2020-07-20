

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AuthenticationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AuthenticationTest
{
    /**
     * Default constructor for test class AuthenticationTest
     */
    public AuthenticationTest()
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
    public void Login()
    {
        Database database1 = new Database();
        Authentication authenti1 = new Authentication();
    }

    @Test
    public void Logout()
    {
        Database database2 = new Database();
        Authentication authenti2 = new Authentication();
        authenti2.logOut();
    }
}


