import java.util.*;

public class PersonalDetails {

    //User Other Than Logged In | Use for HR Employees and HR Director
    String tempUser = "";

    String surname = "";
    String name = "";
    String dob = "";
    String address = "";
    String town = "";
    String county = "";
    String postCode = "";
    String telNo = "";
    String mobNo = "";
    String emergencyContact = "";
    String emergencyMob = "";

    HashMap<String, String> personalD;

    public PersonalDetails()
    {
        personalD = new HashMap<String, String>();
    }

    //New Test
    public void createPersonalDetails()
    {
        if(!Database.authenticated) {
            System.out.println("Cannot Perform Action! Not Logged In!");
        }
        else if (Database.accessRight.equals("1") || Database.accessRight.equals("3")|| Database.accessRight.equals("4")|| Database.accessRight.equals("5") ) {
            System.out.println("You dont have access to create personal details.");
        } else {
            detailsToAnswer();
        }
    }

    public void readPersonalDetails()
    {
        if(!Database.authenticated) {
            System.out.println("Cannot Perform Action! Not Logged In!");
        } else {
            readDetails();
        }
    }

    public void amendPersonalDetails()
    {
        if(!Database.authenticated) {
            System.out.println("Cannot Perform Action! Not Logged In!");
        } else {
            amendDetails();
        }
    }

    private void amendDetails()
    {   
        //if hr employee, or hr director tries to amend personal details
        if(Database.accessRight.equals("2") || Database.accessRight.equals("6")) {
            Scanner aDetails = new Scanner(System.in);
            System.out.print("Enter Employee Login to Amend Details: ");
            if(aDetails.hasNextLine()) {
                tempUser = aDetails.nextLine();
                if(Database.personalDetails.containsKey(tempUser)) {
                    amend(tempUser);
                } else {
                    System.out.println("Employee Details Do Not Exist!");
                }
            }
        } else {
            //amend personal details of own user
            if(Database.personalDetails.containsKey(Database.username)) {
                amend(Database.username);
            } else {
                System.out.println("Employee Details Do Not Exist!");
            }
        }
    }

    private void amend(String amend)
    {
        read(amend);

        System.out.println("Select Number For Detail You Wish to Ammend:");
        System.out.println("1 - Surname");
        System.out.println("2 - Name");
        System.out.println("3 - Date of Birth");
        System.out.println("4 - Address");
        System.out.println("5 - Town/City");
        System.out.println("6 - County");
        System.out.println("7 - Postcode");
        System.out.println("8 - Telephone Number");
        System.out.println("9 - Mobile Number");
        System.out.println("10 - Emergency Contact");
        System.out.println("11 - Emergency Contact Number");

        Scanner adetail = new Scanner(System.in);
        System.out.print("Select: ");
        if(adetail.hasNextLine()) {
            int num = adetail.nextInt();
            adetail.nextLine();
            if(num == 1) {
                System.out.println("Please enter the new Surname: ");
                personalD.put("Surname", adetail.nextLine());
                Database.personalDetails.put(amend, personalD);
                System.out.println("Done"); 
            }
            else if(num == 2) {
                System.out.println("Please enter the new Name: ");
                personalD.put("Name", adetail.nextLine());
                Database.personalDetails.put(amend, personalD);
                System.out.println("Done"); 
            }
           else if(num == 3) {
            System.out.println("Please enter the new Date of Birth: ");
                personalD.put("Date of Birth", adetail.nextLine());
                Database.personalDetails.put(amend, personalD);
                System.out.println("Done"); 
            }
           else if(num == 4) {
            System.out.println("Please enter the new Address: ");
                personalD.put("Address", adetail.nextLine());
                Database.personalDetails.put(amend, personalD);
                System.out.println("Done"); 
            }
           else if(num == 5) {
            System.out.println("Please enter the new Town/City: ");
                personalD.put("Town/City", adetail.nextLine());
                Database.personalDetails.put(amend, personalD);
                System.out.println("Done"); 
            }
           else if(num == 6) {
            System.out.println("Please enter the new County: ");
                personalD.put("County", adetail.nextLine());
                Database.personalDetails.put(amend, personalD);
                System.out.println("Done"); 
            }
            else if(num == 7) {
                System.out.println("Please enter the new Postcode: ");
                personalD.put("Postcode", adetail.nextLine());
                Database.personalDetails.put(amend, personalD);
                System.out.println("Done"); 
            }
            else if(num == 8) {
                System.out.println("Please enter the new Telephone Number: ");
                personalD.put("Telephone Number", adetail.nextLine());
                Database.personalDetails.put(amend, personalD);
                System.out.println("Done"); 
            }
            else if(num == 9) {
                System.out.println("Please enter the new Mobile Number: ");
                personalD.put("Mobile Number", adetail.nextLine());
                Database.personalDetails.put(amend, personalD);
                System.out.println("Done"); 
            }
            else if(num == 10) {
                System.out.println("Please enter the new Emergency Contact: ");
                personalD.put("Emergency Contact", adetail.nextLine());
                Database.personalDetails.put(amend, personalD);
                System.out.println("Done"); 
            }
            else if(num == 11) {
                System.out.println("Please enter the new Emergency Contact Number: ");
                personalD.put("Emergency Contact Number", adetail.nextLine());
                Database.personalDetails.put(amend, personalD);
                System.out.println("Done"); 
            }
        }
        System.out.println("The ammended personal details: ");
        read(amend);
    }

    private void readDetails()
    {
        if(Database.accessRight.equals("2") || Database.accessRight.equals("6")) {
            Scanner loginDetails = new Scanner(System.in);
            System.out.print("Enter Employee ID to View Details: ");
            if(loginDetails.hasNextLine()) {
                tempUser = loginDetails.nextLine();
            }
            if(Database.personalDetails.containsKey(tempUser)) {
                read(tempUser);
            } else {
                System.out.println("Employee Details Do Not Exist!");
            }
        } else {
            if(Database.personalDetails.containsKey(Database.username)) {
                read(Database.username);
            } else {
                System.out.println("Personal Details Do Not Exist!");
            }
        }
    }

    private void read(String detail)
    {
        System.out.println("Personal Details For: " +detail);
        System.out.println("Surname: " +Database.personalDetails.get(detail).get("Surname"));
        System.out.println("Name: " +Database.personalDetails.get(detail).get("Name"));
        System.out.println("Date of Birth: " +Database.personalDetails.get(detail).get("Date of Birth"));
        System.out.println("Address: " +Database.personalDetails.get(detail).get("Address"));
        System.out.println("Town/City: " +Database.personalDetails.get(detail).get("Town/City"));
        System.out.println("County: " +Database.personalDetails.get(detail).get("County"));
        System.out.println("Postcode: " +Database.personalDetails.get(detail).get("Postcode"));
        System.out.println("Telephone Number: " +Database.personalDetails.get(detail).get("Telephone Number"));
        System.out.println("Mobile Number: " +Database.personalDetails.get(detail).get("Mobile Number"));
        System.out.println("Emergency Contact: " +Database.personalDetails.get(detail).get("Emergency Contact"));
        System.out.println("Emergency Contact Number: " +Database.personalDetails.get(detail).get("Emergency Contact Number"));
    }


    private void detailsToAnswer()
    {
        Scanner loginDetails = new Scanner(System.in);
        System.out.print("Enter Employee ID to Create Details: ");
        if(loginDetails.hasNextLine()) {
            tempUser = loginDetails.nextLine();
        }
        if(Database.personalDetails.containsKey(tempUser)) {
            System.out.println("Personal Details For: " + tempUser + " Already Exists!");
        } else if (Database.employee.containsKey(tempUser) ){
            System.out.println("----------- PERSONAL DETAILS CREATION -----------");
            System.out.println("Enter Details: ");
            Scanner details = new Scanner(System.in);
            System.out.print("Surname: ");
            if(details.hasNextLine()) {
                surname = details.nextLine();
                personalD.put("Surname", surname);
                System.out.print("Name: ");
            }
            if(details.hasNextLine()) {
                name = details.nextLine();
                personalD.put("Name", name);
                System.out.print("Date of Birth (dd/mm/yyyy): ");
            }
            if(details.hasNextLine()) {
                dob = details.nextLine();
                personalD.put("Date of Birth", dob);
                System.out.print("Address: ");
            }
            if(details.hasNextLine()) {
                address = details.nextLine();
                personalD.put("Address", address);
                System.out.print("Town/City: ");
            }
            if(details.hasNextLine()) {
                town = details.nextLine();
                personalD.put("Town/City", town);
                System.out.print("County: ");
            }
            if(details.hasNextLine()) {
                county = details.nextLine();
                personalD.put("County", county);
                System.out.print("Postcode: ");
            }
            if(details.hasNextLine()) {
                postCode = details.nextLine();
                personalD.put("Postcode", postCode);
                System.out.print("Telephone Number: ");
            }
            if(details.hasNextLine()) {
                telNo = details.nextLine();
                personalD.put("Telephone Number", telNo);
                System.out.print("Mobile Number: ");
            }
            if(details.hasNextLine()) {
                mobNo = details.nextLine();
                personalD.put("Mobile Number", mobNo);
                System.out.print("Emergency Contact: ");
            }
            if(details.hasNextLine()) {
                emergencyContact = details.nextLine();
                personalD.put("Emergency Contact", emergencyContact);
                System.out.print("Emergency Contact Number: ");
            }
            if(details.hasNextLine()) {
                emergencyMob = details.nextLine();
                personalD.put("Emergency Contact Number", emergencyMob);
            }
            Database.personalDetails.put(tempUser, personalD);
            System.out.println("----------- PERSONAL DETAILS CREATED -----------");
        }
        else {
            System.out.println("This person does not exist.");
        }
    }
}