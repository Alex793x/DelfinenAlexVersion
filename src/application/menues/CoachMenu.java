package application.menues;

import application.controllers.DataController;
import application.utility.SystemPrint;
import application.utility.UI;

public class CoachMenu extends Menu {


    public CoachMenu(String menuHeader, String leadText, String[] menuOptions) {
        super(menuHeader, leadText, menuOptions);
    }

    public void menuLoop(DataController dataController, int ID) {
        while(true) {
        printOptions();

            switch (UI.getInstance().readInt()) {
                case 1 -> addNewSwimmingResult(dataController);
                case 2 -> deleteSwimmingResult(dataController);
                case 3 -> printAllSwimmers(dataController, ID);
                case 4 -> enterResultMenu(dataController, ID);
                case 0 -> {return;}
                default -> SystemPrint.getInstance().printOutInvalidInput();
            }
        }
    }

    private void addNewSwimmingResult(DataController dataController) {
        dataController.getMembershipHandler().findMembership(dataController).getResultList()
                .add(dataController.getMembershipHandler().createNewResult());

    }

    private void deleteSwimmingResult(DataController dataController) {
        dataController.getMembershipHandler().deleteResult(dataController);
    }

    private void printAllSwimmers(DataController dataController, int ID) {
        while (true) {
            SystemPrint.getInstance().printOutSwimmerPrintMenu();
            switch (UI.getInstance().readInt()) {
                case 1 -> dataController.getPersonComparatorHandler().printSwimmersByName(ID);
                case 2 -> dataController.getPersonComparatorHandler().printSwimmersByAge(ID);
                case 3 -> dataController.getPersonComparatorHandler().printSwimmersByID(ID);
                case 4 -> dataController.getPersonComparatorHandler().printSwimmersByGender(ID);
                case 0 -> {return;}
                default -> SystemPrint.getInstance().printOutInvalidInput();
            }
        }
    }

    private void enterResultMenu(DataController dataController, int ID) {
        while(true) {
            SystemPrint.getInstance().printOutResultMenu();
            switch (UI.getInstance().readInt()) {
                case 1 -> dataController.getResultComparatorHandler().printAllResults( ID);
                case 2 -> dataController.getResultComparatorHandler().printSortedByTime(dataController, ID);
                case 3 -> dataController.getResultComparatorHandler().printSortedByLocation(dataController, ID);
                case 4 -> dataController.getResultComparatorHandler().printSortedByRank(dataController, ID);
                case 5 -> dataController.getResultComparatorHandler().printSortedByCompetitiveness(dataController, ID);
                case 6 -> dataController.getResultComparatorHandler().printSortedByDate(dataController, ID);
                case 0 -> {return;}
                default -> SystemPrint.getInstance().printOutInvalidInput();
            }
        }
    }
}
