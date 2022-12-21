package application.menues;

import actor_container.ListContainer;
import application.controllers.DataController;
import application.utility.SystemPrint;
import application.utility.UI;

public class AdminMenu extends Menu {


    public AdminMenu(String menuHeader, String leadText, String[] menuOptions) {
        super(menuHeader, leadText, menuOptions);
    }

    public void menuLoop(DataController dataController) {
        while(true) {
            switch (UI.getInstance().readInt()) {
                case 1 -> ListContainer.getInstance().getEmployeeList()
                            .put(dataController.getEmployeeHandler().createEmployee(),
                                    dataController.getPersonHandler().createNewPerson());
                case 2 -> dataController.getEmployeeHandler().deleteUser();
                case 3 -> advancedMenu(dataController);
                case 0 -> {return;}
                default -> SystemPrint.getInstance().printOutInvalidInput();
            }
        }
    }

    public void advancedMenu(DataController dataController) {
        while(true) {
            SystemPrint.getInstance().printOut("""
                    1. Change a users username
                    2. Change a users password
                    3. Change admin username
                    4. Change admin password
                    0. Exit Advanced Options""");

            switch (UI.getInstance().readInt()) {
                case 1 -> {

                }
                case 2 -> {

                }
                case 3 -> {

                }
            }
        }
    }
}
