package application;

import actor_container.ListContainer;
import application.actors.Employee;
import application.actors.Person;
import application.controllers.DataController;
import application.controllers.MenuController;
import application.utility.SystemPrint;

import java.time.LocalDate;

public class SystemBoot {
    Employee currentUser;
    DataController dataController = new DataController();
    MenuController menuController = new MenuController();

    SystemBoot() {
        ListContainer.getInstance().getEmployeeList().put(new Employee(Employee.Privilege.ADMIN, "admin", "0000"),
                new Person("John", LocalDate.of(1993,3,2), "422378267", 'M' ));

        ListContainer.getInstance().getEmployeeList().put(new Employee(Employee.Privilege.CHAIRMAN, "chairman", "1234"),
                new Person("Madwill",LocalDate.of(1973, 1, 23),"23114355",'M'));

        ListContainer.getInstance().getEmployeeList().put(new Employee(Employee.Privilege.COACH, "Alexthh", "Frida2020"),
                new Person("Alex Holmberg",LocalDate.of(1993, 3, 2),"42237826",'M'));
        menuUserLoader();
    }

    private void menuUserLoader() {

        while (true) {
            currentUser = menuController.login();

            if (currentUser != null) {

                switch (currentUser.getPrivilege()) {
                    case ADMIN -> menuController.adminMenu(dataController);
                    case CHAIRMAN -> menuController.chairmanMenu(dataController);
                    case ACCOUNTANT -> {

                    }
                    case COACH -> menuController.coachMenu(dataController);
                    default -> SystemPrint.getInstance().printOutSomethingWentWrong();
                } // End of Switch case
            } else {
                return;
            } // End of else statement
        } // End of while loop
    } // End of method

}
