package application.utility;

public class SystemPrint {
     private static final SystemPrint SingletonSystemPrint = new SystemPrint();

     // Constructor --------------------------------------------
    private SystemPrint() {

    }

    // Getter --------------------------------------------------
    public static SystemPrint getInstance() {
        return SingletonSystemPrint;
    }


    // -------------------------------------------------------------------------------------------//

    // Print MSG/VARIABLE/STRING/INT --- etc.
    public void printOut(String msg) {
        System.out.println(msg);
    }

    // Person Prompts ---------------------------------------
    public void promptPersonName() {System.out.print("Please enter name: ");}
    public void promptPersonDateOfBirth() {System.out.println("Please enter date of birth: ");}
    public void promptPersonPhoneNumber() {System.out.print("Please enter phone number: ");}
    public void promptPersonGender() {System.out.print("Please enter gender: ");}
    public void promptPersonID() {
        System.out.print("Please enter ID: ");
    }
    public void promptPersonChange() {
        System.out.println("""
                Please choose what to change:
                1. Name
                2. Phone number
                3. Gender
                0. Cancel""");
    }
    public void printOutPersonNotExist() {
        System.out.println("That Person does not exist\n");
    }


    // MembershipInfo prints -------------------------------
    public void printOutMembershipStatusChange() {
        System.out.println("Membership has been changed\n");
    }

    public void promptMembershipHasPaid() {
        System.out.println("""
                Would you like to change payment status?:
                1. Yes
                2. No""");
    }

    public void printOutArrearsLabels() {
        SystemPrint.getInstance().printOut("-------------------------------------------------------------------");
        System.out.printf("%-5s%-10s%-20s%-20s%s%n",
                "ID",
                "NAME",
                "MEMBERSHIP-TYPE",
                "MEMBERSHIP-STATUS",
                "PAID STATUS");
        SystemPrint.getInstance().printOut("-------------------------------------------------------------------");
    }


    // Employee Prompts and prints -------------------------------------
    public void promptPrivilege() {System.out.print("Please enter privilege: ");}
    public void promptUsername() {System.out.print("Please enter a username for this user: ");}
    public void promptPassword() {System.out.print("Please enter a password for this user: ");}
    public void printOutEmployeeRemoved() {
        System.out.println("Employee has been removed, from system");
    }
    public void printPrivilegeOptions() {
        System.out.println("""
                
                1. Chairman
                2. Accountant
                3. Coach""");
    }


    // Result Prompts and prints ----------------------------------------
    public void promptLocation() {System.out.print("Please enter location of result: ");}
    public void promptDateOfEvent() {System.out.println("Please enter date of event: ");}
    public void promptRank() {System.out.print("Please enter given rank: ");}
    public void promptIsCompetitive() {
        System.out.println(
                """
                Please enter:
                1. Competition
                2. Training
                """);}

    // Swimming Discipline Prompt and prints --------------------------------
    public void promptSwimmingDisciplineType() {System.out.print("Please enter Swimming Discipline Type: ");}
    public void promptActiveDisciplineAmount() {System.out.print("How many disciplines are active: ");}
    public void printOutSettingMaxDisciplines() {System.out.println("Only 5 disciplines are possible, setting input to 5\n");}
    public void printOutSwimmerAlreadyHaveDiscipline() {
        System.out.println("Swimmer already have this discipline\n");
    }



    // Invalid inputs / SOMETHING WENT WRONG --------------------------------------------

    public void printOutInvalidInput() {
        System.out.println("Invalid input\n");
    }
    public void printOutSomethingWentWrong() {
        System.out.println("Something Went Wrong\n");
    }
    public void printOutMemberListEmpty() {
        System.out.println("Member-list is empty, your request is not possible\n");
    }
    public void printOutNoSwimmingResults() {
        System.out.println("This swimmer has no swimming results\n");
    }

    public void printOutEmployeeNotExist() {
        System.out.println("Employee does not exist\n");
    }

    // Menu messages ---------------------------------------------------------------------
    public void printOutReturnToMenu() {
        System.out.println("Returning to menu\n");
    }
}
