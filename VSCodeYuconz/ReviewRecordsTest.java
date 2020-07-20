
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReviewRecordsTest
{
    /**
     * Default constructor for test class ReviewRecordsTest
     */
    public ReviewRecordsTest()
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
    public void testCreateReview()
    {
        Database database1 = new Database();
        Authentication authenti1 = new Authentication();
        ReviewRecords reviewRe1 = new ReviewRecords();
        reviewRe1.createReview();
    }

    @Test
    public void testReviewDetails()
    {
        Database database1 = new Database();
        Authentication authenti1 = new Authentication();
        ReviewRecords reviewRe1 = new ReviewRecords();
        reviewRe1.createReview();
        authenti1.logOut();
        authenti1.userLogin();
        reviewRe1.reviewDetails();
    }

    @Test
    public void testSignReview()
    {
        Database database1 = new Database();
        Authentication authenti1 = new Authentication();
        ReviewRecords reviewRe1 = new ReviewRecords();
        reviewRe1.createReview();
        reviewRe1.signReview();
    }

    @Test
    public void testReadReview()
    {
        Database database1 = new Database();
        Authentication authenti1 = new Authentication();
        ReviewRecords reviewRe1 = new ReviewRecords();
        reviewRe1.createReview();
        reviewRe1.readReview();
    }

    @Test
    public void testFinalizeReview()
    {
        Database database1 = new Database();
        Authentication authenti1 = new Authentication();
        ReviewRecords reviewRe1 = new ReviewRecords();
        reviewRe1.createReview();
        authenti1.logOut();
        authenti1.userLogin();
        reviewRe1.signReview();
        authenti1.logOut();
        authenti1.userLogin();
        reviewRe1.signReview();
        reviewRe1.finaliseReview();
    }
}





