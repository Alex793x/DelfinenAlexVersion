package application.utility;

import actor_container.CoachMemberAssociation;
import application.actors.Employee;
import application.actors.MembershipInfo;
import application.actors.Person;

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

    public void printOutSwimmerPrintMenu() {
        System.out.println("""
                
                1. Print out all swimmers associated this account by Name
                2. Print out all swimmers associated this account by age
                3. Print out all swimmers associated this account by ID
                4. Print out all swimmers associated this account by Gender
                0. Cancel""");
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

    public void promptEmployeeName() {System.out.print("Please enter Employee name: ");}

    public void printOutEmployeeNotExist() {
        System.out.println("Employee does not exist\n");
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
                """);
    }

    public void printOutResultMenu() {
        System.out.println(
                """
                
                1. Print out all Results associated this account by time
                2. Print all results from swimmer, by swim time
                3. Print results from swimmer, by Location
                4. Print result from swimmer, by Rank
                5. Print result from swimmer, by competitiveness
                6. Print result from swimmer, by date if event
                0. Cancel""");
    }

    public void promptMinutes() {
        System.out.print("Please enter minutes: ");
    }

    public void promptSeconds() {
        System.out.print("Please enter seconds ");
    }

    public void printOutResultLabels() {
        System.out.printf("%-15s%-15s%-15s%-15s%s%n",
            "LOCATION:",
            "DATE:",
            "TIME:",
            "RANK:",
            "COMPETITIVE:");
    }

    public void printOutResult(MembershipInfo.SwimmingDisciplineResult result) {
        System.out.printf("%-15s%-15s%d:%-15d%-15d%b%n",
                result.location(),
                result.dateOfEvent(),
                result.swimTime()/60,
                result.swimTime()%60,
                result.rank(),
                result.isCompetitive());
    }



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
    public void printOutMemberListEmpty() {System.out.println("Member-list is empty, your request is not possible\n");}
    public void printOutNoSwimmingResults() {
        System.out.println("This swimmer has no swimming results\n");
    }

    // Menu messages ---------------------------------------------------------------------
    public void printOutReturnToMenu() {
        System.out.println("Returning to menu\n");
    }


    // Association prompts and prints
    public void printOutPersonLabels(CoachMemberAssociation<Employee, MembershipInfo, Person> association) {
        System.out.println("--------------------");
        System.out.printf("ID: %-5d%s%n",
                association.getPerson().getID(),
                association.getPerson().getName());
        System.out.println("--------------------");
    }

    public void printOutPersonDetails(Person person) {
        System.out.printf("%s%-5d%s%-10s%s%-15s%s%-15s%s%-10s%n",
                "ID: ", person.getID(),
                "NAME: ", person.getName(),
                "BIRTH: ", person.getAge(),
                "TEL: ", person.getPhoneNumber(),
                "GENDER: ", person.getGender());
    }































































}
