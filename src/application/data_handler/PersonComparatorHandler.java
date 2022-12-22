package application.data_handler;

import actor_container.CoachMemberAssociation;
import actor_container.ListContainer;
import application.actors.Person;
import application.utility.SystemPrint;

import java.util.ArrayList;
import java.util.Comparator;

public class PersonComparatorHandler {
    private void swimmerList(int ID, Comparator<Person> comparing) {
        ArrayList<Person> swimmerList = new ArrayList<>(
                ListContainer.getInstance().getAssociationHashSet().stream()
                        .filter(association -> association.getEmployee().getID() == ID)
                        .map(CoachMemberAssociation::getPerson)
                        .sorted(comparing).toList()
        );

        swimmerList.forEach(person -> SystemPrint.getInstance().printOutPersonDetails(person));
    }

    public void printSwimmersByName(int ID) {
        swimmerList(ID, Comparator.comparing(Person::getName));
    }

    public void printSwimmersByAge(int ID) {
        swimmerList(ID, Comparator.comparing(Person::getAge));
    }

    public void printSwimmersByGender(int ID) {
        swimmerList(ID, Comparator.comparing(Person::getGender));
    }

    public void printSwimmersByID(int ID) {
        swimmerList(ID, Comparator.comparing(Person::getID));
    }

}
