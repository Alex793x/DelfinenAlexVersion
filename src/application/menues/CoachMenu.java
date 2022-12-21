package application.menues;

import application.controllers.DataController;
import application.utility.SystemPrint;
import application.utility.UI;

public class CoachMenu extends Menu {


    public CoachMenu(String menuHeader, String leadText, String[] menuOptions) {
        super(menuHeader, leadText, menuOptions);
    }

    public void menuLoop(DataController dataController) {
        while(true) {
        printOptions();

            switch (UI.getInstance().readInt()) {
                case 1 -> dataController.getMembershipHandler().findMembership(dataController).getResultList()
                            .add(dataController.getMembershipHandler().createNewResult());
                case 2 -> dataController.getMembershipHandler().deleteResult(dataController);
                case 3 -> {

                }
                case 0 -> {return;}
                default -> SystemPrint.getInstance().printOutInvalidInput();
            }
        }
    }
}
