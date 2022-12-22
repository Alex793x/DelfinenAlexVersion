package application.data_handler;

import actor_container.ListContainer;
import application.actors.MembershipInfo;
import application.actors.Person;
import application.controllers.DataController;
import application.utility.SystemPrint;
import application.utility.UI;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Map;

public class MembershipHandler {

    public MembershipInfo createNewMembership() {
        return new MembershipInfo(UI.getInstance().readMemberType(),addActiveDisciplineList());
    }


    public void deleteMembership(DataController dataController) {
        MembershipInfo membershipInfo = findMembership(dataController);
            ListContainer.getInstance().getMemberList().entrySet().removeIf(
                    membershipInfoPersonEntry -> membershipInfoPersonEntry.getKey().equals(membershipInfo)
            );

            if (membershipInfo.getMemberType().equals(MembershipInfo.MemberType.COMPETITIVE_SWIMMER)) {
                ListContainer.getInstance().getAssociationHashSet().removeIf(
                        employeeMemberPerson ->  employeeMemberPerson.getMember().equals(membershipInfo)
                );
            }
        }



    public MembershipInfo.SwimmingDisciplineResult createNewResult() {
        SystemPrint.getInstance().promptLocation();
        String location = UI.getInstance().readLine();

        SystemPrint.getInstance().promptDateOfEvent();
        LocalDate dateOfEvent = UI.getInstance().readDateOfEvent();

        SystemPrint.getInstance().promptRank();
        int rank = UI.getInstance().readInt();

        SystemPrint.getInstance().promptIsCompetitive();
        boolean isCompetitive = UI.getInstance().readBoolean();

        return new MembershipInfo.SwimmingDisciplineResult(location,dateOfEvent,rank,isCompetitive);
    }



    protected ArrayList<MembershipInfo.SwimmingDisciplineType> addActiveDisciplineList() {
        ArrayList<MembershipInfo.SwimmingDisciplineType> disciplineTypes = new ArrayList<>();
        SystemPrint.getInstance().promptActiveDisciplineAmount();
        int amount = UI.getInstance().readInt();
        if (amount > 5) {
            SystemPrint.getInstance().printOutSettingMaxDisciplines();
            amount = 5;
        }
        while (amount != 0) {
            disciplineTypes.add(UI.getInstance().readDisciplineType());
            amount--;
            SystemPrint.getInstance().printOut("You still need to enter " + amount + " disciplines");
        }
        return disciplineTypes;
    }


    protected void addDiscipline(MembershipInfo memberShipInfo) {
        SystemPrint.getInstance().promptSwimmingDisciplineType();
        MembershipInfo.SwimmingDisciplineType disciplineType = UI.getInstance().readDisciplineType();

        if (memberShipInfo.getActiveDisciplines().size() < 5 &&
                memberShipInfo.getActiveDisciplines().stream()
                        .noneMatch(swimmingDisciplineType -> swimmingDisciplineType.equals(disciplineType))) {
            memberShipInfo.getActiveDisciplines().add(disciplineType);
        } else {
            SystemPrint.getInstance().printOutSwimmerAlreadyHaveDiscipline();
        }
    }



    public MembershipInfo findMembership(DataController dataController) {
        Person foundPerson = dataController.getPersonHandler().findPerson();
        for (Map.Entry<MembershipInfo, Person> membershipInfoPersonEntry : ListContainer.getInstance().getMemberList().entrySet()) {
            if (foundPerson != membershipInfoPersonEntry.getValue()) continue; {
                return membershipInfoPersonEntry.getKey();
            }
        }

        return null;
    }



    public void deleteResult(DataController dataController) {
        MembershipInfo foundMembershipInfo = findMembership(dataController);
        if (!foundMembershipInfo.getResultList().isEmpty()) {
            SystemPrint.getInstance().promptDateOfEvent();
            LocalDate dateOfEvent = UI.getInstance().readDateOfEvent();

            ListContainer.getInstance().getMemberList().keySet()
                    .forEach(membershipInfo -> {

                        if (membershipInfo.equals(foundMembershipInfo)) {
                            membershipInfo.getResultList().stream()
                                    .filter(swimmingDisciplineResult ->
                                            swimmingDisciplineResult.dateOfEvent().isEqual(dateOfEvent))
                                    .findFirst()
                                    .ifPresent(swimmingDisciplineResult ->
                                            membershipInfo.getResultList().remove(swimmingDisciplineResult));
                        }
                    });
        } else {
            SystemPrint.getInstance().printOutNoSwimmingResults();
        }
    }



    public void printMembersArrears() {
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



    public void checkOneMemberArrears(DataController dataController) {
        if (!ListContainer.getInstance().getMemberList().isEmpty()) {
            MembershipInfo foundMembership = findMembership(dataController);

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



    public void printClubEconomy() {
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
                int age = Period.between(set.getValue().getAge(),LocalDate.now()).getYears();
                boolean active = set.getKey().isMembershipStatus();
                boolean hasPaid = set.getKey().isHasPaid();

                if (age < 18 && active && hasPaid)
                    underEighteenPaid++;
                else if (age < 18 && active)
                    underEighteenNotPaid++;
                else if (age < 18 && hasPaid) {
                    underEighteenNotActivePaid++;
                } else if (age < 18) {
                    underEighteenNotActiveNotPaid++;
                }

                if (age > 18 && age < 60 && active && hasPaid)
                    overEighteenPaid++;
                else if (age > 18 && age < 60 && active)
                    overEighteenNotPaid++;
                else if (age > 18 && age < 60 && hasPaid) {
                    overEighteenNotActivePaid++;
                } else if (age > 18 && age < 60) {
                    overEighteenNotActiveNotPaid++;
                }

                if (age > 60 && active && hasPaid)
                    overSixtyPaid++;
                else if (age > 60 && active)
                    overSixtyNotPaid++;
                else if (age > 60 && hasPaid) {
                    overSixtyNotActivePaid++;
                } else if (age > 60) {
                    overSixtyNotActiveNotPaid++;
                }
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

