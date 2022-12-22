package application.data_handler;

import actor_container.ListContainer;
import application.actors.MembershipInfo;
import application.actors.Person;
import application.controllers.DataController;
import application.utility.SystemPrint;
import application.utility.UI;

import java.util.Comparator;
import java.util.List;

public class ResultComparatorHandler {

    public void printAllResults(int ID) {
        ListContainer.getInstance().getAssociationHashSet().stream()
                .filter(association -> association.getEmployee().getID()==ID)
                .forEach(association -> {
                    SystemPrint.getInstance().printOutPersonLabels(association);
                    SystemPrint.getInstance().printOutResultLabels();
                    association.getMember().getResultList().stream()
                            .sorted(Comparator.comparing(MembershipInfo.SwimmingDisciplineResult::swimTime))
                            .forEach(result ->  SystemPrint.getInstance().printOutResult(result));
                });
    }

    public void printAllResultsForSwimmer(DataController dataController ,int ID) {
        Person swimmer = dataController.getPersonHandler().findPerson();
        SystemPrint.getInstance().printOutPersonLabel(swimmer);
        ListContainer.getInstance().getAssociationHashSet().stream()
                .filter(association -> association.getEmployee().getID()==ID)
                .filter(association -> association.getPerson().equals(swimmer))
                .forEach(association -> {
                    SystemPrint.getInstance().printOutPersonLabels(association);
                    SystemPrint.getInstance().printOut(association.getMember().getActiveDisciplines().toString());
                    SystemPrint.getInstance().printOutResultLabels();
                    association.getMember().getResultList().stream()
                            .sorted(Comparator.comparing(MembershipInfo.SwimmingDisciplineResult::swimTime))
                            .forEach(result ->  SystemPrint.getInstance().printOutResult(result));
                });
    }

    private List<MembershipInfo.SwimmingDisciplineResult> sortedSwimmerResults(Person swimmer, int ID) {
        SystemPrint.getInstance().printOutPersonLabel(swimmer);
        ListContainer.getInstance().getAssociationHashSet().stream().filter(
                association -> association.getPerson().equals(swimmer))
                .forEach(association ->SystemPrint.getInstance().printOut(association.getMember().getActiveDisciplines().toString()));

        MembershipInfo.SwimmingDisciplineType disciplineType = UI.getInstance().readDisciplineType();
        int distance = UI.getInstance().readDistance();
        SystemPrint.getInstance().printOutResultLabels();
        return ListContainer.getInstance().getAssociationHashSet().stream()
                .filter(association -> association.getPerson().equals(swimmer))
                .filter(association -> association.getEmployee().getID() == ID)
                .flatMap(association -> association.getMember().getResultList().stream())
                .filter(result -> result.swimmingDisciplineType().equals(disciplineType))
                .filter(result -> result.distance() == distance)
                .sorted(Comparator.comparing(MembershipInfo.SwimmingDisciplineResult::swimTime))
                .toList();
    }

    public void printSortedByTime(DataController dataController, int ID) {
        sortedSwimmerResults(dataController.getPersonHandler().findPerson(),ID)
                .forEach(result -> SystemPrint.getInstance().printOutResult(result));
    }

    public void printSortedByLocation(DataController dataController, int ID) {
        sortedSwimmerResults(dataController.getPersonHandler().findPerson(), ID).stream()
                .sorted(Comparator.comparing(MembershipInfo.SwimmingDisciplineResult::location))
                .forEach(result -> SystemPrint.getInstance().printOutResult(result));
    }

    public void printSortedByRank(DataController dataController, int ID) {
        sortedSwimmerResults(dataController.getPersonHandler().findPerson(), ID).stream()
                .sorted(Comparator.comparing(MembershipInfo.SwimmingDisciplineResult::rank))
                .forEach(result -> SystemPrint.getInstance().printOutResult(result));
    }

    public void printSortedByCompetitiveness(DataController dataController, int ID) {
        sortedSwimmerResults(dataController.getPersonHandler().findPerson(), ID).stream()
                .sorted(Comparator.comparing(MembershipInfo.SwimmingDisciplineResult::isCompetitive))
                .forEach(result -> SystemPrint.getInstance().printOutResult(result));
    }

    public void printSortedByDate(DataController dataController, int ID) {
        sortedSwimmerResults(dataController.getPersonHandler().findPerson(), ID).stream()
                .sorted(Comparator.comparing(MembershipInfo.SwimmingDisciplineResult::dateOfEvent))
                .forEach(result -> SystemPrint.getInstance().printOutResult(result));
    }
}
