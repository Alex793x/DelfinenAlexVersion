package application.menues;

import actor_container.ListContainer;
import application.controllers.DataController;
import application.controllers.FileController;
import application.utility.SystemPrint;
import application.utility.UI;

public class AdminMenu extends Menu {


    public AdminMenu(String menuHeader, String leadText, String[] menuOptions) {
        super(menuHeader, leadText, menuOptions);
    }

    public void menuLoop(DataController dataController, FileController fileController) {
        while(true) {
            printOptions();
            switch (UI.getInstance().readInt()) {
                case 1 -> createNewUser(dataController, fileController);
                case 2 -> deleteUser(dataController, fileController);
                case 3 -> advancedMenu(dataController, fileController);
                case 0 -> {return;}
                default -> SystemPrint.getInstance().printOutInvalidInput();
            } // End of switch
        } // End of while loop
    } // End of method

    private void advancedMenu(DataController dataController, FileController fileController) {
        while(true) {
            SystemPrint.getInstance().printOut("""
                    1. Change a users username
                    2. Change a users password
                    3. Change admin username
                    4. Change admin password
                    0. Exit Advanced Options""");

            switch (UI.getInstance().readInt()) {
                case 1 -> {
                    dataController.getEmployeeHandler().findEmployee().setUsername(UI.getInstance().readUsername());
                    fileController.adminWriteToFiles();
                }
                case 2 -> {
                    dataController.getEmployeeHandler().findEmployee().setPassword(UI.getInstance().readPassword());
                    fileController.adminWriteToFiles();
                }
                case 3 -> {
                    dataController.getEmployeeHandler().adminAccount().setUsername(UI.getInstance().readUsername());
                    fileController.adminWriteToFiles();
                }
                case 4 -> {
                    dataController.getEmployeeHandler().adminAccount().setPassword(UI.getInstance().readPassword());
                    fileController.adminWriteToFiles();
                }
                case 0 -> {return;}
                default -> SystemPrint.getInstance().printOutInvalidInput();
            } // End of switch
        } // End of while loop
    } // End of method


    private void createNewUser(DataController dataController, FileController fileController) {
        ListContainer.getInstance().getEmployeeList()
                .put(dataController.getEmployeeHandler().createEmployee(),
                        dataController.getPersonHandler().createNewPerson());
        fileController.adminWriteToFiles();

    }

    private void deleteUser(DataController dataController, FileController fileController) {
        dataController.getEmployeeHandler().deleteUser();
        fileController.adminWriteToFiles();
    }
}
