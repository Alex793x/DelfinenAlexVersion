package application.data_handler;

import actor_container.ListContainer;
import application.actors.Person;
import application.utility.SystemPrint;
import application.utility.UI;

import java.time.LocalDate;
import java.util.ArrayList;

public class PersonHandler {

    public Person createNewPerson() {
        SystemPrint.getInstance().promptPersonName();
        String name = UI.getInstance().readLine();

        SystemPrint.getInstance().promptPersonDateOfBirth();
        LocalDate dateOfBirth = UI.getInstance().readDateOfEvent();

        SystemPrint.getInstance().promptPersonPhoneNumber();
        String phoneNumber = UI.getInstance().readLine();

        SystemPrint.getInstance().promptPersonGender();
        char gender = UI.getInstance().readGender();

        return new Person(name, dateOfBirth, phoneNumber, gender);
    } // End of method

    public Person findPerson() {
        SystemPrint.getInstance().promptPersonName();
        String name = UI.getInstance().readLine();
        ArrayList<Person> possiblePersons = new ArrayList<>(ListContainer.getInstance().getMemberList().values().stream()
                .filter(person -> person.getName().equals(name)).toList());

        possiblePersons.forEach(
                person -> SystemPrint.getInstance().printOut("ID: " + person.getID() + ", Name: " + person.getName()));

        SystemPrint.getInstance().promptPersonID();
        int ID = UI.getInstance().readInt();
        for (Person person : ListContainer.getInstance().getMemberList().values()) {

            if (person.getID() != ID) continue; {
                return person;
            } // End of if statement
        } // End of for loop
        return null;
    } // End of method

    public void editInfo(Person person) {
        SystemPrint.getInstance().promptPersonChange();
        while(true) {
            switch (UI.getInstance().readInt()) {
                case 1 -> {
                    SystemPrint.getInstance().promptPersonName();
                    person.setName(UI.getInstance().readLine());
                    return;
                }
                case 2 -> {
                    SystemPrint.getInstance().promptPersonPhoneNumber();
                    person.setPhoneNumber(UI.getInstance().readLine());
                    return;
                }
                case 3 -> {
                    SystemPrint.getInstance().promptPersonGender();
                    person.setGender(UI.getInstance().readGender());
                    return;
                }
                case 0 -> {
                    SystemPrint.getInstance().printOutReturnToMenu();
                    return;
                }
                default -> SystemPrint.getInstance().printOutInvalidInput();
            } // End of switch case
        } // End of while loop
    } // End of method
}
