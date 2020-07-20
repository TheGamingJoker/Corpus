import java.util.*;

public class Database{

    //New 
    //<Clearance Level, Login, Password>
    public static HashMap<String, HashMap<String, String>> staffLogin;
    
    //<Login, Password>
    public static HashMap<String, String> employee; // All Employees | No Matter of Role
    HashMap<String, String> hrEmployee; // Only HR Employees
    HashMap<String, String> director; // Only Directors || HR Director Included As Well
    HashMap<String, String> hrDirector; // HR Director Only
    HashMap<String, String> reviewers; // Only Reviewers
    HashMap<String, String> managers; // Only Managers

    public static Boolean authenticated = false;
    public static String accessRight = "";
    public static String username = "";
    
    public static StringBuilder authorisationLog;

    //Details Repository of Employees
    public static HashMap<String, HashMap<String, String>> personalDetails; 
    public static HashMap<String, HashMap<String, String>> reviewRecords;
    public static HashMap<String, String> assignedManagers;
    public static HashMap<String, String> assignedReviewers;

    //pastPerformances

    public static HashMap<String, HashMap<String, HashMap<String, String>>> pastPerformances;

    public static StringBuilder authorizationlog;
    public Database()
    {
        staffLogin = new HashMap<String, HashMap<String, String>>();

        employee = new HashMap<String, String>();
        hrEmployee = new HashMap<String, String>();
        reviewers = new HashMap<String, String>();
        managers = new HashMap<String, String>();
        director = new HashMap<String, String>();
        hrDirector = new HashMap<String, String>();
        
        assignedManagers = new HashMap<String, String>();
        assignedReviewers = new HashMap<String, String>();

        personalDetails = new HashMap<String, HashMap<String, String>>();
        reviewRecords = new HashMap<String, HashMap<String, String>>();

        pastPerformances = new HashMap<String, HashMap<String, HashMap<String, String>>>();
        // Initiate Login Repository
        loginRepository();
        assignManagers();
        assignReviewers();

        authorisationLog = new StringBuilder();
        authorisationLog.append("Authorisation Attempts Log \n");
        authorisationLog.append("-------------------------- \n");

    }

    private void loginRepository()
    {   
        
        //Personal ID | Password
        employee.put("geo124", "ra12l");
        employee.put("sam389", "ma34f");
        employee.put("jam364", "ja98s");
        employee.put("aum189", "ta76m");
        employee.put("dav791", "ms76s");
        employee.put("jac329", "ph23a");
        employee.put("nik457", "ni23j");
        employee.put("leo124", "le32n");
        employee.put("co365", "lsao2");
        employee.put("za385", "l2ao4");

        hrEmployee.put("jam364", "ja98s");

        reviewers.put("geo124", "ra12l"); 
        reviewers.put("leo124", "le32n");
        reviewers.put("aum189", "ta76m");
        reviewers.put("dav791", "ms76s");

        managers.put("geo124", "ra12l");
        managers.put("leo124", "le32n");

        director.put("aum189", "ta76m");
        director.put("dav791", "ms76s"); 

        hrDirector.put("aum189", "ta76m");
        
        //Access Right | Login Details
        staffLogin.put("1", employee); 
        staffLogin.put("2", hrEmployee); 
        staffLogin.put("3", reviewers); 
        staffLogin.put("4", managers); 
        staffLogin.put("5", director);
        staffLogin.put("6", hrDirector);  
    }

    
    private void assignManagers()
    {   
        //Reviewee and Reviewer
        assignedManagers.put("sam389", "geo124"); 
        assignedManagers.put("jac329", "leo124"); 
        assignedManagers.put("jam364", "geo124"); 
        assignedManagers.put("aum189", "leo124"); 
        assignedManagers.put("dav791", "aum189"); 
        assignedManagers.put("nik457", "dav791"); 
        assignedManagers.put("geo124", "aum189"); 
        assignedManagers.put("leo124", "dav791");
        assignedManagers.put("co365", "geo124");
        assignedManagers.put("za385", "leo124");

    }
    
    private void assignReviewers()
    {   
        //Reviewee and Second Reviewer
        assignedReviewers.put("sam389", "aum189"); 
        assignedReviewers.put("jac329", "geo124"); 
        assignedReviewers.put("jam364", "leo124"); 
        assignedReviewers.put("aum189", "geo124"); 
        assignedReviewers.put("dav791", "leo124"); 
        assignedReviewers.put("nik457", "dav791"); 
        assignedReviewers.put("geo124", "leo124"); 
        assignedReviewers.put("leo124", "dav791");
        assignedReviewers.put("co365", "geo124");
        assignedReviewers.put("za385", "aum189");
    }

    public void returnLog()
    {
        System.out.println(authorisationLog.toString());
    }
}