package application.data_handler;

import actor_container.ListContainer;
import application.actors.MembershipInfo;
import application.actors.Person;
import application.controllers.DataController;
import application.utility.SystemPrint;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultComparatorHandler {

    public void printAllResults(DataController dataController, int ID) {
        ListContainer.getInstance().getAssociationHashSet().stream()
                .filter(association -> association.getEmployee().getID()==ID)
                .forEach(association -> {
                    SystemPrint.getInstance().printOutPersonLabels(association);
                    association.getMember().getResultList().stream()
                            .sorted(Comparator.comparing(MembershipInfo.SwimmingDisciplineResult::swimTime))
                            .forEach(result ->  SystemPrint.getInstance().printOutResult(result));
                });
    }

    private List<MembershipInfo.SwimmingDisciplineResult> sortedSwimmerResults(Person swimmer, int ID) {
        SystemPrint.getInstance().printOutResultLabels();
        return ListContainer.getInstance().getAssociationHashSet().stream()
                .filter(association -> association.getPerson().equals(swimmer))
                .filter(association -> association.getEmployee().getID() == ID)
                .flatMap(association -> association.getMember().getResultList().stream())
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
