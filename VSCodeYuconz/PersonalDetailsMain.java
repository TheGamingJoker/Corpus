public class PersonalDetailsMain {
    // New
    public static void main(String[] args){
        
        Database data = new Database();
        //authenticate as an HR (jam364, ja98s)
        //access right 2 for HR
        Authentication auth = new Authentication();
        PersonalDetails pers = new PersonalDetails();
        ReviewRecords rev = new ReviewRecords();

        //create details as an HR for another employee (geo124)
        pers.createPersonalDetails();
        //read the personal details
        pers.readPersonalDetails();
        //amend some details of this person 
        pers.amendPersonalDetails();
        //read the personal details
        pers.readPersonalDetails();
        //logs out
        auth.logOut();

        //login as (geo124, ra12l)
        //access right 1
        auth.userLogin();
        //it will show his own personal details
        pers.readPersonalDetails();
        //he should be able to amend his own personal details
        pers.amendPersonalDetails();
        //read the personal details
        pers.readPersonalDetails();
        //logs out 
        auth.logOut();

        //login as another employee that doesnt have personal details (aum189, ta76m)
        //access right 1
        auth.userLogin();
        //this should give an error as an employee would be able to create personal details
        pers.createPersonalDetails();
        //logs out
        auth.logOut();

        //returns a log of all logins
        data.returnLog();
        

    }
}

