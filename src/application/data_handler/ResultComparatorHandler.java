package application.data_handler;

import actor_container.ListContainer;
import application.actors.MembershipInfo;
import application.actors.Person;
import application.controllers.DataController;
import application.utility.SystemPrint;
import application.utility.UI;

import java.time.LocalDate;
import java.util.*;

public class ResultComparatorHandler {

    public void printAllResults(int ID) {
        ListContainer.getInstance().getAssociationHashSet().stream()
                .filter(association -> association.getEmployee().getID()==ID)
                .forEach(association -> {
                    SystemPrint.getInstance().printOutPersonLabelsThroughAssociation(association);
                    SystemPrint.getInstance().printOutResultLabels();
                    association.getMember().getResultList().stream()
                            .sorted(Comparator.comparing(MembershipInfo.SwimmingDisciplineResult::swimTime))
                            .forEach(result ->  SystemPrint.getInstance().printOutResult(result));
                });
    }

    public void printAllResultsForSwimmer(DataController dataController ,int ID) {
        Person swimmer = dataController.getPersonHandler().findPerson();
        SystemPrint.getInstance().printOutPersonLabel(swimmer);
        if (ListContainer.getInstance().getAssociationHashSet().isEmpty()) {
            SystemPrint.getInstance().printOut("Empty");
        }
        ListContainer.getInstance().getAssociationHashSet().stream()
                .filter(association -> association.getEmployee().getID()==ID)
                .filter(association -> association.getPerson().equals(swimmer))
                .forEach(association -> {
                    SystemPrint.getInstance().printOutPersonLabelsThroughAssociation(association);
                    SystemPrint.getInstance().printOut(association.getMember().getActiveDisciplines().toString());
                    SystemPrint.getInstance().printOutResultLabels();
                    association.getMember().getResultList().stream()
                            .sorted(Comparator.comparing(MembershipInfo.SwimmingDisciplineResult::swimTime))
                            .forEach(result ->  SystemPrint.getInstance().printOutResult(result));
                });
    }

    private List<MembershipInfo.SwimmingDisciplineResult> sortedSwimmerResults(Person swimmer, int ID) {
        SystemPrint.getInstance().printOutPersonLabel(swimmer);
        ListContainer.getInstance().getAssociationHashSet().stream()
                .filter(association -> association.getPerson().equals(swimmer))
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


    public void topFiveCompetitor() {
        int distance = UI.getInstance().readDistance();
        MembershipInfo.SwimmingDisciplineType disciplineType = UI.getInstance().readDisciplineType();
        boolean competitive = UI.getInstance().readCompetitiveness();
        LocalDate timeFrame = UI.getInstance().chooseTimeFrame();

        // Find the top five swimmers and their best result
        List<AbstractMap.SimpleEntry<Person, MembershipInfo.SwimmingDisciplineResult>> topFiveSwimmers = ListContainer.getInstance().getAssociationHashSet().stream()
                .flatMap(association -> association.getMember().getResultList().stream()
                        .filter(result -> result.distance() == distance)
                        .filter(result -> result.swimmingDisciplineType().equals(disciplineType))
                        .filter(result -> result.isCompetitive() == competitive)
                        .filter(result -> result.dateOfEvent().isAfter(timeFrame) || result.dateOfEvent().equals(timeFrame))
                        .sorted(Comparator.comparing(MembershipInfo.SwimmingDisciplineResult::swimTime))
                        .limit(1)
                        .map(result -> new AbstractMap.SimpleEntry<>(association.getPerson(), result)))
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(MembershipInfo.SwimmingDisciplineResult::swimTime)))
                .limit(5).toList();

        // Print the top five swimmers and their best result
        topFiveSwimmers.forEach(entry -> {
            Person person = entry.getKey();
            MembershipInfo.SwimmingDisciplineResult result = entry.getValue();
            SystemPrint.getInstance().printOutPersonLabel(person);
            SystemPrint.getInstance().printOutResultLabels();
            SystemPrint.getInstance().printOutResult(result);
        });
    }

}
