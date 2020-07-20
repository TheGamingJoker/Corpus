import java.util.*;

public class Authentication{

    protected String userID = "";
    protected String password = "";
    
    protected String aR = "";
    
    protected Integer logOnAttempts = 0;
    
    public static Integer attempts = 0;

    public Authentication()
    {   
        System.out.println("----------- System Initiated -----------");
        System.out.println("----------- LOG IN -----------");
       
        userLogin();
    }

    //New
    public void userLogin() 
    {   
        attempts++;
        Database.authorisationLog.append("Attempt Number: " + attempts + "\n");

        if(Database.authenticated){
            System.out.println("You are already logged in.");
            Database.authorisationLog.append("User was Already Logged In");
        }
        else {
            Scanner login = new Scanner(System.in);
            System.out.print("User ID: ");
            if(login.hasNextLine()) {
                userID = login.nextLine();
                System.out.print("Password: ");
                Database.authorisationLog.append("User ID: " + userID + "\n");
            }
            if(login.hasNextLine()) {
                password = login.nextLine();
                Database.authorisationLog.append("Password: " + password + "\n" );
                System.out.println("Access Right:");
                System.out.println("1 --> Employee");
                System.out.println("2 --> Human Resources Employee ");
                System.out.println("3 --> Reviewer");
                System.out.println("4 --> Manager");
                System.out.println("5 --> Director");
                System.out.println("6 --> Human Resources Director");
            }
            if(login.hasNextLine()) {
                aR = login.nextLine();
                Database.authorisationLog.append("Access Right: " + aR +  "\n");
            } 
            if(Database.staffLogin.containsKey(aR) && Database.staffLogin.get(aR).containsKey(userID) && Database.staffLogin.get(aR).get(userID).equals(password)) {
                Database.username = userID;
                Database.accessRight = aR;
                Database.authenticated = true;
                System.out.println("----------- LOGGED IN -----------");
                System.out.println("User Logged In: " + userID);
                System.out.println("Access Right: " + aR);
                System.out.println("-----------  -----------");
                Database.authorisationLog.append("Status: successfull \n \n");
            } else {
                logOnAttempts++;
                if(logOnAttempts >= 3) {
                System.out.println("Too many attempts! Access Denied!");
                Database.authorisationLog.append("Status: denied \n \n");
                } else {
                    System.out.println("LogOn Attempt Failed, Invalid Details! Please Try Again! Log On Attempts: " +logOnAttempts);
                    Database.authorisationLog.append("Status: denied \n \n");
                    userLogin();
                }
            }
        }
    }
   
    
    public void logOut()
    {
        if(Database.authenticated) {
            Database.username = "";
            Database.accessRight = "";
            Database.authenticated = false;
            System.out.println("----------- LOGGED OUT -----------");
        } else {
            System.out.println("Cannot Log Out! Not Logged In!");
        }
    }
}