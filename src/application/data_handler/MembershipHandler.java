package application.data_handler;

import actor_container.ListContainer;
import application.actors.MembershipInfo;
import application.actors.Person;
import application.controllers.DataController;
import application.utility.SystemPrint;
import application.utility.UI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

    public MembershipInfo getLastLasMember() {
        Set<MembershipInfo> keys = ListContainer.getInstance().getMemberList().keySet();
        Iterator<MembershipInfo> iterator = keys.iterator();
        MembershipInfo lastKey = null;
        while (iterator.hasNext()) {
            lastKey = iterator.next();
        }
        return lastKey;
    }
}

