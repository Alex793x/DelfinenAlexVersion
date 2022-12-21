package application.utility;

import application.actors.Employee;
import application.actors.MembershipInfo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);
    private static final UI singletonUI = new UI();

    // Constructor ------------------------------
    private UI() {
    }

    // Getter -----------------------------------
    public static UI getInstance() {
        return singletonUI;
    }

    // --------------------------------------------------------
    public String readLine() {
        return scanner.nextLine();
    }
    public int readInt() {
        while(true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                SystemPrint.getInstance().printOutInvalidInput();
            }
        }
    }
    public boolean readBoolean() {
        while(true) {
            try {
                return Boolean.parseBoolean(scanner.nextLine());
            } catch (Exception e) {
                SystemPrint.getInstance().printOut(e.getMessage());
            }
        }
    }

    // DATE OF BIRTH / DATE OF EVENT ------------ etc
    public LocalDate readDateOfEvent() {

        while(true) {
            SystemPrint.getInstance().printOut("Please enter year");
            int year = readInt();
            SystemPrint.getInstance().printOut("Please enter month");
            int month = readInt();
            SystemPrint.getInstance().printOut("Please enter day");
            int day = readInt();

            try {
                return LocalDate.of(year,month,day);
            } catch (DateTimeException e) {
                SystemPrint.getInstance().printOut(e.getMessage());
            }
        }
    }



    // Membership prompts ----------------------------------------------

    public MembershipInfo.SwimmingDisciplineType readDisciplineType() {
        SystemPrint.getInstance().promptSwimmingDisciplineType();
        while (true) {
            try {
                return MembershipInfo.SwimmingDisciplineType.valueOf(readLine().toUpperCase());
            } catch (EnumConstantNotPresentException | IllegalArgumentException e)  {
                SystemPrint.getInstance().printOut(e.getMessage());
            }
        }
    }

    public MembershipInfo.MemberType readMemberType() {
        while (true) {
            SystemPrint.getInstance().printOut(
                    """
                    Choose Either:
                    1. Competitive Swimmer
                    2. Leisure Swimmer
                    """);

            switch (readInt()) {
                case 1 -> {
                    return MembershipInfo.MemberType.COMPETITIVE_SWIMMER;
                }
                case 2 -> {
                    return MembershipInfo.MemberType.LEISURE_SWIMMER;
                }
            }
        }
    }

    public char readGender() {
        return scanner.nextLine().charAt(0);
    }

    public Employee.Privilege privilege() {
        while(true) {
            try {
                return Employee.Privilege.valueOf(scanner.nextLine());

            } catch (EnumConstantNotPresentException e) {
                SystemPrint.getInstance().printOut(e.getMessage());
            }
        }
    }


    // LOGIN PROMPTS ----------------------------------------------------
    public String readUsername() {
        SystemPrint.getInstance().printOut("Please enter username: ");
        return scanner.nextLine();
    }

    public String readPassword() {
        SystemPrint.getInstance().printOut("Please enter password: ");
        return scanner.nextLine();
    }


}
