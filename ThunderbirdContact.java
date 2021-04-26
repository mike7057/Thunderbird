/******************************************************************************
 * Copyright (C) 2019 Eric Pogue.
 * 
 * This file is licensed under the BSD-3-Clause
 * 
 * You may use any part of the file as long as you give credit in your 
 * source code.
 * 
 *****************************************************************************/

class ThunderbirdContact extends HttpRequest implements Runnable {
    private String firstName;
    public String getFirstName() { return firstName; }

    private String lastName;
    public String getLastName() { return lastName; }

    private String preferredName; //Initializing String of preferredName so it can be imported from the JSON file
    public String getPreferredName() { return preferredName; }

    private String email; //Initializing String of email so it can be imported from the JSON file
    public String getEmail() { return email; }
    
    private int seatLocation; 
    public int getSeat() { return seatLocation; }



    ThunderbirdContact(String urlIn) {
        super(urlIn);

        firstName = "";
        lastName = "";
        preferredName = "";
        email = "";
        seatLocation = 0;

        // Todo: Add additional fields. COMPLETE MO
        // Added fields for email and preferredName
    }

    public Boolean Load() {
        Boolean returnValue = false;
        System.out.println("Loading: " + requestURL);
        if (super.readURL()) {
            Parse(); 
            returnValue = true;
        }

        return returnValue;
    }

    public void Parse() {
        for (String s : urlContent) {
            String[] subString = s.split("\"");

            // Todo: Parse for additional fields. COMPLETE MO
            // Added parsing for email and preferredName fields
            if (subString.length > 3) {
                if (subString[1].equals("firstName")) {
                    firstName = subString[3];
                }
                if (subString[1].equals("lastName")) {
                    lastName = subString[3];
                }
                if (subString[1].equals("preferredName")) { // Parsing for preferredName
                    preferredName = subString[3]; 
                }
                if (subString[1].equals("email")) { // Parsing for email
                    email = subString[3];
                }
                if (subString[1].equals("seatLocation")) {
                    try {
                        seatLocation = 0; 
                        if (!subString[3].equals("")) {
                            seatLocation = Integer.parseInt(subString[3]);
                        }
                    } 
                    catch (Exception e) {
                        System.out.println("Exception: " + e);
                    }
                }
            }
        }    
    }

    public void Validate() {
        if (urlContent.size() < 1) {
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: No content loaded\n");
             // Returning from the middle of a method is controversial.
        }

        // Todo: Add author's name and email address to failed messages. COMPLETED MO
        // Added fail messages for preferredName and email
        if (firstName.length() == 0) {
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: First Name (\"firstName\") required but not found\n\n");
            System.out.println(this);
        } else if (lastName.length() == 0) {   
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: Last Name (\"lastName\") required but not found\n\n");
            System.out.println(this);          
        } else if (preferredName.length() == 0) {   // Added failed message for preferredName
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: preferredName (\"preferredName\") required but not found\n\n");
            System.out.println(this);
        } else if (email.length() == 0) {   // Added failed message for email
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: email (\"email\") required but not found\n\n");
            System.out.println(this);
        } else {
            System.out.println("Validating: " + requestURL + "... Passed!");
        }
        return;
    }

    public String toString() {
        // Todo: Add additional fields to returnString. COMPLETED MO
        // Added preferredName and email return strings
        String returnString = "firstName: " + firstName + "\n";
        returnString = returnString + "lastName: " + lastName + "\n";
        returnString = returnString + "preferredName: " + preferredName + "\n"; // Added preferredName to return string
        returnString = returnString + "email: " + email + "\n"; // Added email to return string
        returnString = returnString + "seatNumber: " + seatLocation + "\n";
        returnString = returnString + super.toString();

        return returnString;
    }

    public void run() {
        Load();
    }
}