import java.util.*;

import javax.xml.crypto.Data;

public class ReviewRecords {

    String staffNo = "";
    String tempStaff = "";

    String name = "";

    String manOrDir = "";
    String secManOrDir = "";
    String section = "";
    String jobTitle = "";

    String performanceSummary = "";
    String futureGoals = "";

    String reviewerCommentsManager = "";
    String reviewerCommentsSecondManager = "";

    String recommendation = "";
    String revieweeSig = "";
    String manDirSig = "";
    String secondReviewerSig = "";

    String fullySigned = "false";

    Integer num = 0;

    String year = "";

    HashMap<String, String> reviewRec;
    HashMap<String, HashMap<String, String>> pastPerf;

    public ReviewRecords() 
    {
        reviewRec = new HashMap<String, String>();
        pastPerf = new HashMap<String, HashMap<String, String>>();
    }

    public void createReview()
    {
        if(!Database.authenticated) {
            System.out.println("Cannot Perform Action! Not Logged In!");
        } 
        else if (Database.accessRight.equals("2") || Database.accessRight.equals("3") || Database.accessRight.equals("4") || Database.accessRight.equals("5") || Database.accessRight.equals("6")) {
            System.out.println("You dont have access to create review details. Please login as Employee.");
        } else {
            reviewCreation();
        }
    }

    public void reviewDetails()
    {
        if(!Database.authenticated) {
            System.out.println("Cannot Perform Action! Not Logged In!");
        } else if (Database.accessRight.equals("3")) {
            reviewerCheck();
        } else {
            System.out.println("You dont have access to review details.");
        }
    }

    public void signReview()
    {
        if(!Database.authenticated) {
            System.out.println("Cannot Perform Action! Not Logged In!");
        } else if (Database.accessRight.equals("1") || Database.accessRight.equals("3")) {
            sign();
        } else {
            System.out.println("You dont have access to review details.");
        }
    }

    public void finaliseReview()
    {
        if(!Database.authenticated) {
            System.out.println("Cannot Perform Action! Not Logged In!");
        } else if(Database.accessRight.equals("3")) {
            done();
        } else {
            System.out.println("Not Authorised for Such Action!");
        }
    }

    public void readReview()
    {
        if(!Database.authenticated) {
            System.out.println("Cannot Perform Action! Not Logged In!");
        } else if (Database.accessRight.equals("1")) {
            if(Database.reviewRecords.containsKey(Database.username) && Database.reviewRecords.get(Database.username).get("Signed").equals("true")) {
                System.out.println("Review Can Only be Read by HR!");
            } else if (Database.reviewRecords.containsKey(Database.username)){
                readRev(Database.username);
            } else {
                System.out.println("Review Does not Exist!");
            }
        } else if (Database.accessRight.equals("3")) {
            System.out.println("1 - First Reviewer");
            System.out.println("2 - Second Reviewer");
            Scanner aDetail = new Scanner(System.in);
            System.out.print("Select (1/2):");
            if(aDetail.hasNextLine()) {
                int num = aDetail.nextInt();
                if(num == 1) {
                    readA();
                }
                if(num == 2) {
                    readB();
                }
            }
        } else if(Database.accessRight.equals("2") || Database.accessRight.equals("6")) {
            Scanner checkEmployee = new Scanner(System.in);
            System.out.print("Enter Employee ID to Read Form: ");
            if(checkEmployee.hasNextLine()) {
                tempStaff = checkEmployee.nextLine();
                if(Database.reviewRecords.containsKey(tempStaff)) {
                    readRev(tempStaff);
                } else {
                    System.out.println("Record or User Does not Exist!");
                }
            }
        }
    }

    private void readA()
    {   
        Scanner aDetail = new Scanner(System.in);
        System.out.print("Enter Employee ID to Read Form: ");
        if(aDetail.hasNextLine()) {
            tempStaff = aDetail.nextLine();
            if(Database.assignedManagers.containsKey(tempStaff) && Database.assignedManagers.get(tempStaff).equals(staffNo)) {
                if(Database.reviewRecords.containsKey(tempStaff) && Database.reviewRecords.get(tempStaff).get("Signed").equals("true")) {
                    System.out.println("Review Can Only be Read by HR!");
                } else if(Database.reviewRecords.containsKey(tempStaff)) {
                    readRev(tempStaff);
                }
            } else {
                System.out.println("Record or User Does not Exist!");
            }
        }
    }
    
    private void readB()
    {   
        Scanner aDetail = new Scanner(System.in);
        System.out.print("Enter Employee ID to Read Form: ");
        if(aDetail.hasNextLine()) {
            tempStaff = aDetail.nextLine();
            if(Database.assignedReviewers.containsKey(tempStaff) && Database.assignedReviewers.get(tempStaff).equals(staffNo)) {
                if(Database.reviewRecords.containsKey(tempStaff) && Database.reviewRecords.get(tempStaff).get("Signed").equals("true")) {
                    System.out.println("Review Can Only be Read by HR!");
                } else if(Database.reviewRecords.containsKey(tempStaff)) {
                    readRev(tempStaff);
                }
            } else {
                System.out.println("Record or User Does not Exist!");
            }
        }
    }

    private void done()
    {
        staffNo = Database.username;
        System.out.println("1 - First Reviewer");
        System.out.println("2 - Second Reviewer");

        Scanner adetail = new Scanner(System.in);
        System.out.print("Select (1/2): ");
        if(adetail.hasNextLine()) {
            int num = adetail.nextInt();
            if(num == 1) {
               doneOne();
            }
            if(num == 2) {
                doneTwo();
            }
            if(num > 2) {
                System.out.println("Try Again, No Such Selection!");
            }
        } 
    }

    private void doneOne()
    {
        Scanner adetail = new Scanner(System.in);
        System.out.print("Enter Employee ID to Finalise Review Form: ");
        if(adetail.hasNextLine()) {
            tempStaff = adetail.nextLine();
                if(Database.assignedManagers.containsKey(tempStaff) && Database.assignedManagers.get(tempStaff).equals(staffNo)) {
                    if(Database.reviewRecords.containsKey(tempStaff) && Database.reviewRecords.get(tempStaff).containsKey("Employee Signature") && Database.reviewRecords.get(tempStaff).containsKey("MDSignature") && Database.reviewRecords.get(tempStaff).containsKey("Second Manager/Director Signature")) {
                        fullySigned = "true";
                        reviewRec.put("Signed", fullySigned);
                        Database.reviewRecords.put(tempStaff, reviewRec);
                        System.out.println("Document Finalised!");
                        pastPerf.put(year, reviewRec);
                        if(!Database.pastPerformances.containsKey(tempStaff)) {
                            Database.pastPerformances.put(tempStaff, pastPerf);
                        } else if (Database.pastPerformances.get(tempStaff).equals(year)){
                            System.out.println("Annual Review Exists!");
                        } else {
                            Database.pastPerformances.get(tempStaff).put(year, reviewRec);
                        }
                    } else {
                        System.out.println("Document not fully signed!");
                    }
                } else {
                    System.out.println("Employee Does not Exist or Assigned!");
                }
        }
    }

    private void doneTwo()
    {
        Scanner adetail = new Scanner(System.in);
        System.out.print("Enter Employee ID to Finalise Review Form: ");
        if(adetail.hasNextLine()) {
            tempStaff = adetail.nextLine();
                if(Database.assignedReviewers.containsKey(tempStaff) && Database.assignedReviewers.get(tempStaff).equals(staffNo)) {
                    if(Database.reviewRecords.containsKey(tempStaff) && Database.reviewRecords.get(tempStaff).containsKey("Employee Signature") && Database.reviewRecords.get(tempStaff).containsKey("Second Manager/Director Signature") && Database.reviewRecords.get(tempStaff).containsKey("MDSignature")) {
                        fullySigned = "true";
                        reviewRec.put("Signed", fullySigned);
                        Database.reviewRecords.put(tempStaff, reviewRec);
                        pastPerf.put(year, reviewRec);
                        if(!Database.pastPerformances.containsKey(tempStaff)) {
                            Database.pastPerformances.put(tempStaff, pastPerf);
                        } else if (Database.pastPerformances.get(tempStaff).equals(year)){
                            System.out.println("Annual Review Exists!");
                        } else {
                            Database.pastPerformances.get(tempStaff).put(year, reviewRec);
                        }
                    } else {
                        System.out.println("Document not fully signed!");
                    }
                } else {
                    System.out.println("Employee Does not Exist or Assigned!");
                }
        }    
    }

    private void sign()
    {
        staffNo = Database.username;
        if(Database.accessRight.equals("1")) {
            if(Database.reviewRecords.containsKey(staffNo) && Database.reviewRecords.get(staffNo).containsKey("Employee Signature")) {
                System.out.println("Review Already Signed by Employee!");
            } else if(!Database.reviewRecords.containsKey(staffNo)) {
                System.out.println("No Such Review!");
            } else {
                Scanner adetail = new Scanner(System.in);
                System.out.print("Sign (Yes/No): ");
                if(adetail.hasNextLine()) {
                    if(adetail.nextLine().equals("Yes")) {
                        revieweeSig = staffNo;
                        reviewRec.put("Employee Signature", revieweeSig);
                        Database.reviewRecords.put(staffNo, reviewRec);
                        System.out.println("Signature Recorded.");
                    } else {
                        Database.reviewRecords.put(staffNo, reviewRec);
                        System.out.println("Pending Signature.");
                    }
                }
            }
        }
        if(Database.accessRight.equals("3")) {
            System.out.println("1 - First Reviewer");
            System.out.println("2 - Second Reviewer");
            Scanner adetailO = new Scanner(System.in);
            System.out.print("Select (1/2): ");
            if(adetailO.hasNextLine()) {
                int num = adetailO.nextInt();
                if(num == 1) {
                    signOne();
                }
                if(num == 2) {
                    signTwo();
                }
                if(num > 2) {
                    System.out.println("Try Again, No Such Selection!");
                }
            }
        }
    }

    private void signOne()
    {   
        Scanner adetailO = new Scanner(System.in);
        System.out.print("Enter Employee ID to Sign Review Form: ");
        if(adetailO.hasNextLine()) {
            tempStaff = adetailO.nextLine();
            if(Database.assignedManagers.containsKey(tempStaff) && Database.assignedManagers.get(tempStaff).equals(staffNo)) {
                if(Database.reviewRecords.containsKey(tempStaff) && Database.reviewRecords.get(tempStaff).containsKey("MDSignature")) {
                    System.out.println("Review Already Signed!");
                } else if(!Database.reviewRecords.containsKey(tempStaff)) {
                     System.out.println("No Such Review!");
                }
                else {
                    System.out.print("Sign (Yes/No): ");
                    if(adetailO.hasNextLine()) {
                        if(adetailO.nextLine().equals("Yes")) {
                            manDirSig = staffNo;
                            reviewRec.put("MDSignature", manDirSig);
                            Database.reviewRecords.put(tempStaff, reviewRec);
                            System.out.println("Signature Recorded.");
                        } else {
                            Database.reviewRecords.put(tempStaff, reviewRec);
                            System.out.println("Pending Signature.");
                        }
                    }
                }
            }
        }
    }

    private void signTwo()
    {
        Scanner adetailO = new Scanner(System.in);
        System.out.print("Enter Employee ID to Sign Review Form: ");
        if(adetailO.hasNextLine()) {
        tempStaff = adetailO.nextLine();
            if(Database.assignedReviewers.containsKey(tempStaff) && Database.assignedReviewers.get(tempStaff).equals(staffNo)) {
                if(Database.reviewRecords.containsKey(tempStaff) && Database.reviewRecords.get(tempStaff).containsKey("Second Manager/Director Signature")) {
                    System.out.println("Review Already Signed!");
                } else if(!Database.reviewRecords.containsKey(tempStaff)) {
                    System.out.println("No Such Review!");
                }
                else {
                    System.out.print("Sign (Yes/No): ");
                    if(adetailO.hasNextLine()) {
                        if(adetailO.nextLine().equals("Yes")) {
                            secondReviewerSig = staffNo;
                            reviewRec.put("Second Manager/Director Signature", secondReviewerSig);
                            Database.reviewRecords.put(tempStaff, reviewRec);
                            System.out.println("Signature Recorded.");
                        } else {
                            Database.reviewRecords.put(tempStaff, reviewRec);
                            System.out.println("Pending Signature.");
                        }
                    }
                }
            }
        }
    }

    private void reviewerCheck()
    {
        staffNo = Database.username;
        System.out.println("1 - First Reviewer");
        System.out.println("2 - Second Reviewer");

        Scanner adetail = new Scanner(System.in);
        System.out.print("Select (1/2): ");
        if(adetail.hasNextLine()) {
            int num = adetail.nextInt();
            if(num == 1) {
                managerDirMod();
            }
            if(num == 2) {
                reviewerMod();
            }
            if(num > 2) {
                System.out.println("Try Again, No Such Selection!");
            }
        }
    }

    private void reviewerMod()
    {
        Scanner manD = new Scanner(System.in);
        System.out.print("Enter Employee ID to Review Detail: ");
        if(manD.hasNextLine()) {
            tempStaff = manD.nextLine();
            if(Database.assignedReviewers.containsKey(tempStaff) && Database.assignedReviewers.get(tempStaff).equals(staffNo)) {
                if(Database.reviewRecords.containsKey(tempStaff)) {
                    readRev(tempStaff);
                    Scanner writeReview = new Scanner(System.in);
                    System.out.print("Write Review: ");
                    if(writeReview.hasNextLine()) {
                        reviewerCommentsSecondManager = writeReview.nextLine();
                        reviewRec.put("Second Review", reviewerCommentsSecondManager);
                        System.out.print("Sign (Yes/No): ");
                    }
                    if(writeReview.hasNextLine()) {
                        if(writeReview.nextLine().equals("Yes")) {
                            secondReviewerSig = staffNo;
                            reviewRec.put("Second Manager/Director Signature", secondReviewerSig);
                            Database.reviewRecords.put(tempStaff, reviewRec);
                            System.out.println("Signature Recorded.");
                        }
                        else {
                            Database.reviewRecords.put(tempStaff, reviewRec);
                            System.out.println("Review Completed, Pending Signature.");
                        }
                    }
                } else {
                    System.out.println("Employee has not created Review Record Yet!");
                }
            } else {
                System.out.println("Cannot Review Employee you are not Assigned too!");
            }
        }
    }

    private void managerDirMod()
    {
        Scanner manD = new Scanner(System.in);
        System.out.print("Enter Employee ID to Review Detail: ");
        if(manD.hasNextLine()) {
            tempStaff = manD.nextLine();
            if(Database.assignedManagers.containsKey(tempStaff) && Database.assignedManagers.get(tempStaff).equals(staffNo)) {
                if(Database.reviewRecords.containsKey(tempStaff)) {
                    readRev(tempStaff);
                    Scanner writeReview = new Scanner(System.in);
                    System.out.print("Write Review: ");
                    if(writeReview.hasNextLine()) {
                        reviewerCommentsManager = writeReview.nextLine();
                        reviewRec.put("Manager/Director Review", reviewerCommentsManager);
                        
                        System.out.print("Recommendation (Stay in Post, Salary Increase, Promotion, Probation, Termination): ");
                    }
                    if(writeReview.hasNextLine()) {
                        recommendation = writeReview.nextLine();
                        reviewRec.put("Recommendation", recommendation);
                        System.out.print("Sign (Yes/No): ");
                    }
                    if(writeReview.hasNextLine()) {
                        if(writeReview.nextLine().equals("Yes")) {
                            manDirSig = staffNo;
                            reviewRec.put("MDSignature", manDirSig);
                            Database.reviewRecords.put(tempStaff, reviewRec);
                            System.out.println("Signature Recorded.");
                        }
                        else {
                            Database.reviewRecords.put(tempStaff, reviewRec);
                            System.out.println("Review Completed, Pending Signature.");
                        }
                    }
                } else {
                    System.out.println("Employee has not created Review Record Yet!");
                }
            } else {
                System.out.println("Cannot Review Employee you are not Assigned too!");
            }
        }
    }

    private void readRev(String user)
    {
        System.out.println("Review Record For: " +Database.reviewRecords.get(user).get("Name"));
        System.out.println("Staff Number: " +Database.reviewRecords.get(user).get("Staff Number"));
        System.out.println("Manager/Director: " +Database.reviewRecords.get(user).get("Manager/Director"));
        System.out.println("Second Manager/Director: " +Database.reviewRecords.get(user).get("Second Manager/Director"));
        System.out.println("Section: " +Database.reviewRecords.get(user).get("Section"));
        System.out.println("Job Title: " +Database.reviewRecords.get(user).get("Job Title"));
        System.out.println("Performance Summary: " +Database.reviewRecords.get(user).get("Performance Summary"));
        System.out.println("Future Goals: " +Database.reviewRecords.get(user).get("Future Goals"));
        if(Database.reviewRecords.get(user).containsKey("Manager/Director Review")) {
            System.out.println("Manager/Director Review: " +Database.reviewRecords.get(user).get("Manager/Director Review"));
        }
        if(Database.reviewRecords.get(user).containsKey("Second Review")) {
            System.out.println(Database.reviewRecords.get(user).get("Manager/Director Review"));
        }
        if(Database.reviewRecords.get(user).containsKey("Recommendation")) {
            System.out.println("Recommendation: " +Database.reviewRecords.get(user).get("Recommendation"));
        }
        if(Database.reviewRecords.get(user).containsKey("Employee Signature")) {
            System.out.println("Employee Signature: " +Database.reviewRecords.get(user).get("Employee Signature"));
        }
        if(Database.reviewRecords.get(user).containsKey("MDSignature")) {
            System.out.println("Manager/Director Signature: " +Database.reviewRecords.get(user).get("MDSignature"));
        }
        if(Database.reviewRecords.get(user).containsKey("Second Manager/Director Signature")) {
            System.out.println("Second Manager/Director Signature: " +Database.reviewRecords.get(user).get("Second Manager/Director Signature"));
        }
        if(Database.pastPerformances.containsKey(user)) {
            Scanner aDetail = new Scanner(System.in);
            System.out.print("Do You Wish To Look at Past Reviews? (Yes/No)");
            if(aDetail.hasNextLine()) {
                if(aDetail.nextLine().equals("Yes")) {
                    readPast(user);
                } else {
                    System.out.println("End of Review Record.");
                }
            }
        }
    }

    private void readPast(String user) 
    {
        for(String userName : Database.pastPerformances.keySet()) {
            if(userName.equals(user)) {
                HashMap<String, HashMap<String, String>> yearCheck;
                yearCheck = Database.pastPerformances.get(userName);
                for(String year : yearCheck.keySet()) {
                    HashMap<String, String> pastDetails;
                    pastDetails = yearCheck.get(year);
                    System.out.println(year);
                    for(String keyName : pastDetails.keySet()) {
                        if(keyName.equals("Name")) {
                            System.out.println("Review Record For: " +pastDetails.get(keyName));
                        }
                        if(keyName.equals("Staff Number")) {
                            System.out.println("Staff Number: " +pastDetails.get(keyName));
                        }
                        if(keyName.equals("Manager/Director")) {
                            System.out.println("Manager/Director: " +pastDetails.get(keyName));
                        }
                        if(keyName.equals("Second Manager/Director")) {
                            System.out.println("Second Manager/Director: " +pastDetails.get(keyName));
                        }
                        if(keyName.equals("Section")) {
                            System.out.println("Section: " +pastDetails.get(keyName));
                        }
                        if(keyName.equals("Job Title")) {
                            System.out.println("Job Title: " +pastDetails.get(keyName));
                        }
                        if(keyName.equals("Performance Summary")) {
                            System.out.println("Performance Summary: " +pastDetails.get(keyName));
                        }
                        if(keyName.equals("Future Goals")) {
                            System.out.println("Future Goals: " +pastDetails.get(keyName));
                        }
                        if(keyName.equals("Manager/Director Review")) {
                            System.out.println("Manager/Director Review: " +pastDetails.get("Manager/Director Review"));
                        }
                        if(keyName.equals("Second Review")) {
                            System.out.println("Second Manager/Director Review: " +pastDetails.get("Manager/Director Review"));
                        }
                        if(keyName.equals("Recommendation")) {
                            System.out.println("Recommendation: " +pastDetails.get("Recommendation"));
                        }
                        if(keyName.equals("Employee Signature")) {
                            System.out.println("Employee Signature: " +pastDetails.get("Employee Signature"));
                        }
                        if(keyName.equals("MDSignature")) {
                            System.out.println("Manager/Director Signature: " +pastDetails.get("MDSignature"));
                        }
                        if(keyName.equals("Second Manager/Director Signature")) {
                            System.out.println("Second Manager/Director Signature: " +pastDetails.get("Second Manager/Director Signature"));
                        }
                        
                    }
                }
            }
        }
    }

    private void reviewCreation()
    {   
        staffNo = Database.username;
        System.out.println("Creating Details for Staff: " +staffNo);
        Scanner reviewDetails = new Scanner(System.in);
        System.out.print("Year of Review (yyyy): ");
        if(reviewDetails.hasNextLine()) {
            year = reviewDetails.nextLine();
            reviewRec.put("Staff Number", staffNo);
            reviewRec.put("Year", year);
            System.out.print("Name of Employee: ");
        }
        if(reviewDetails.hasNextLine()) {
            name = reviewDetails.nextLine();
            reviewRec.put("Name", name);
            System.out.print("Manager/Director: ");
        }
        if(reviewDetails.hasNextLine()) {
            manOrDir = reviewDetails.nextLine();
            reviewRec.put("Manager/Director", manOrDir);
            System.out.print("Second Manager/Director: ");
        }
        if(reviewDetails.hasNextLine()) {
            secManOrDir = reviewDetails.nextLine();
            reviewRec.put("Second Manager/Director", secManOrDir);
            System.out.print("Section: ");
        }
        if(reviewDetails.hasNextLine()) {
            section = reviewDetails.nextLine();
            reviewRec.put("Section", section);
            System.out.print("Job Title: ");
        }
        if(reviewDetails.hasNextLine()) {
            jobTitle = reviewDetails.nextLine();
            reviewRec.put("Job Title", jobTitle);
            System.out.print("Performance Summary: ");
        }
        if(reviewDetails.hasNextLine()) {
            performanceSummary = reviewDetails.nextLine();
            reviewRec.put("Performance Summary", performanceSummary);
            System.out.print("Future Goals: ");
        }
        if(reviewDetails.hasNextLine()) {
            futureGoals = reviewDetails.nextLine();
            reviewRec.put("Future Goals", futureGoals);
            System.out.print("Sign (Yes/No): ");
        }
        if(reviewDetails.hasNextLine()) {
            if(reviewDetails.nextLine().equals("Yes")) {
                revieweeSig = staffNo;
                reviewRec.put("Employee Signature", revieweeSig);
                System.out.println("Signature Recorded.");
            } else {
                System.out.println("Review Created, Pending Signature.");
            }
        }
        reviewRec.put("Signed", fullySigned);
        Database.reviewRecords.put(staffNo, reviewRec);
    }
}   