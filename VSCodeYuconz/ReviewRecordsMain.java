
public class ReviewRecordsMain {
        // New
        public static void main(String[] args){
            
            Database data = new Database();
            //authenticate as an employee (geo124, ra12l)
            //access right 1
            Authentication auth = new Authentication();
            PersonalDetails pers = new PersonalDetails();
            ReviewRecords rev = new ReviewRecords();

            //create a review of himself
            //SIGN YES
            rev.createReview();
            auth.logOut();

            //login as second reviewer (leo124, le32n)
            //access right 3
            Authentication auth2 = new Authentication();
            //adds info to the review
            //2nd reviewer
            //geo124
            //SIGN YES
            rev.reviewDetails();
            auth2.logOut();

            //login as first reviewer (aum189, ta76m)
            //access right 3
            Authentication auth3 = new Authentication();
            //1st reviwer
            //geo124
            //Choose NO in sign review
            rev.reviewDetails();
            //1st reviewer
            //geo124
            rev.signReview();
            //1
            //geo124
            rev.finaliseReview();
            auth3.logOut();

            //2nd YEAR

            //authenticate as the same employee (geo124, ra12l)
            //access right 1
            Authentication auth4 = new Authentication();
            rev.createReview();
            auth.logOut();

            //login as second reviewer (leo124, le32n)
            ///access right 3
            Authentication auth5 = new Authentication();
            //adds info to the review
            rev.reviewDetails();
            auth2.logOut();

            //login as first reviewer (aum189, ta76m)
            //access right 3
            Authentication auth6 = new Authentication();
            //Choose YES in sign review
            //CHoose NO in read past performances
            rev.reviewDetails();
            rev.finaliseReview();
            auth3.logOut();

            //login as an HR (jam364, ja98s)
            //access right 2 
            Authentication auth7 = new Authentication();
            //should be able to create a review 
            rev.createReview();
            //review details of (geo124)
            //say YES in read past performances
            rev.readReview();
            auth7.logOut();

            
    
        }
    }
    