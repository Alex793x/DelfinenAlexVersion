package application.menues;

import actor_container.ListContainer;
import application.actors.MembershipInfo;
import application.actors.Person;
import application.controllers.DataController;
import application.utility.SystemPrint;
import application.utility.UI;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;

public class TreasuryMenu extends Menu{
    public TreasuryMenu(String menuHeader, String leadText, String[] menuOptions) {
        super(menuHeader, leadText, menuOptions);
    }

    public void menuLooping(DataController dataController) {
        while(true) {
            printOptions();
            switch (UI.getInstance().readInt()) {
                case 1 -> printMembersArrears();
                case 2 -> checkOneMemberArrears(dataController);
                case 3 -> printClubEconomy();
                case 0 -> {return;}
                default -> SystemPrint.getInstance().printOutInvalidInput();
            }
        }
    }

    private void printMembersArrears() {
        if (!ListContainer.getInstance().getMemberList().isEmpty()) {
            SystemPrint.getInstance().printOutArrearsLabels();
            ListContainer.getInstance().getMemberList().forEach(
                    (membershipInfo, person) -> {
                        if (!membershipInfo.isHasPaid()) {
                            System.out.printf("%-5s%-10s%-20s%-20s%-5s%n",
                                    person.getID(),
                                    person.getName(),
                                    membershipInfo.getMemberType(),
                                    membershipInfo.isMembershipStatus(),
                                    membershipInfo.isHasPaid());
                            SystemPrint.getInstance().printOut("");
                        }
                    }
            );
        } else {
            SystemPrint.getInstance().printOutMemberListEmpty();
        }
    }

    private void checkOneMemberArrears(DataController dataController) {
        if (!ListContainer.getInstance().getMemberList().isEmpty()) {
            MembershipInfo foundMembership = dataController.getMembershipHandler().findMembership(dataController);

            if (foundMembership != null) {
                SystemPrint.getInstance().printOutArrearsLabels();
                ListContainer.getInstance().getMemberList().forEach(
                        (membershipInfo, person) -> {
                            if (membershipInfo.equals(foundMembership)) {
                                System.out.printf("%-5s%-10s%-20s%-20s%-5s%n",
                                        person.getID(),
                                        person.getName(),
                                        membershipInfo.getMemberType(),
                                        membershipInfo.isMembershipStatus(),
                                        membershipInfo.isHasPaid());
                                SystemPrint.getInstance().printOut("");

                                membershipInfo.setHasPaid(UI.getInstance().changeHasPaid());
                            }
                        }
                );
            }
        } else {
            SystemPrint.getInstance().printOutMemberListEmpty();
        }
    }

    private void printClubEconomy() {
        if (!ListContainer.getInstance().getMemberList().isEmpty()) {
            double totalArrears;
            double totalPaid;
            int underEighteenPaid = 0;
            int underEighteenNotPaid = 0;
            int underEighteenNotActivePaid = 0;
            int underEighteenNotActiveNotPaid = 0;

            int overEighteenPaid = 0;
            int overEighteenNotPaid = 0;
            int overEighteenNotActivePaid = 0;
            int overEighteenNotActiveNotPaid = 0;

            int overSixtyPaid = 0;
            int overSixtyNotPaid = 0;
            int overSixtyNotActivePaid = 0;
            int overSixtyNotActiveNotPaid = 0;

            for (Map.Entry<MembershipInfo, Person> set : ListContainer.getInstance().getMemberList().entrySet()) {
                int age = Period.between(set.getValue().getAge(), LocalDate.now()).getYears();
                boolean active = set.getKey().isMembershipStatus();
                boolean hasPaid = set.getKey().isHasPaid();

                if (age < 18 && active && hasPaid) underEighteenPaid++;
                else if (age < 18 && active) underEighteenNotPaid++;
                else if (age < 18 && hasPaid) underEighteenNotActivePaid++;
                 else if (age < 18) underEighteenNotActiveNotPaid++;

                if (age > 18 && age < 60 && active && hasPaid) overEighteenPaid++;
                else if (age > 18 && age < 60 && active) overEighteenNotPaid++;
                else if (age > 18 && age < 60 && hasPaid) overEighteenNotActivePaid++;
                else if (age > 18 && age < 60) overEighteenNotActiveNotPaid++;

                if (age > 60 && active && hasPaid) overSixtyPaid++;
                else if (age > 60 && active) overSixtyNotPaid++;
                else if (age > 60 && hasPaid) overSixtyNotActivePaid++;
                else if (age > 60) overSixtyNotActiveNotPaid++;
            }

            totalArrears = (underEighteenNotPaid * 1000) + (underEighteenNotActiveNotPaid * 500) + (overEighteenNotPaid * 1600)
                    + (overEighteenNotActiveNotPaid * 500) + ((overSixtyNotPaid * 1600) - ((overSixtyNotPaid * 1600) *0.25))
                    + ((overSixtyNotActiveNotPaid * 500) - ((overSixtyNotActiveNotPaid * 500) * 0.25));

            totalPaid = (underEighteenPaid * 1000) + (underEighteenNotActivePaid * 500) + (overEighteenPaid * 1600)
                    + (overEighteenNotActivePaid * 500) + ((overSixtyPaid * 1600) - (overSixtyPaid * 1600) * 0.25)
                    + ((overSixtyNotActivePaid * 1600) - (overSixtyNotActivePaid * 500) * 0.25);

            SystemPrint.getInstance().printOut("There is currently totally been paid: " + totalPaid + "\n" +
                    "There is currently totally in arrears: " + totalArrears);
        } else {
            SystemPrint.getInstance().printOutMemberListEmpty();
        }
    }
}
