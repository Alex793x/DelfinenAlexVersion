package application.menues;

import actor_container.ListContainer;
import application.controllers.DataController;
import application.utility.SystemPrint;
import application.utility.UI;

public class ChairmanMenu extends Menu {


    public ChairmanMenu(String menuHeader, String leadText, String[] menuOptions) {
        super(menuHeader, leadText, menuOptions);
    }

    public void menuLoop(DataController dataController) {
        while (true) {
            printOptions();

            switch (UI.getInstance().readInt()) {
                case 1 -> addMember(dataController);
                case 2 -> deleteMemberByMembership(dataController);
                case 3 -> editMembershipInfo(dataController);
                case 4 -> editPersonInfo(dataController);
                case 0 -> {return;}
                default -> SystemPrint.getInstance().printOutInvalidInput();
            } // End of switch statement
        } // End of while loop
    } // End of method



    private void addMember(DataController dataController) {
        ListContainer.getInstance().getMemberList()
                .put(dataController.getMembershipHandler().createNewMembership(),
                        dataController.getPersonHandler().createNewPerson());
    } // End of method

    private void deleteMemberByMembership(DataController dataController) {
        if (!ListContainer.getInstance().getMemberList().isEmpty()) {
            dataController.getMembershipHandler().deleteMembership(dataController);
        } else {
            SystemPrint.getInstance().printOutMemberListEmpty();
        }
    } // End of method

    private void editMembershipInfo(DataController dataController) {
        if (!ListContainer.getInstance().getMemberList().isEmpty()) {
            dataController.getMembershipHandler().findMembership(dataController).setMembershipStatus();
            SystemPrint.getInstance().printOutMembershipStatusChange();
        } else {
            SystemPrint.getInstance().printOutMemberListEmpty();
        }
    } // End of method


    private void editPersonInfo(DataController dataController) {
        if (!ListContainer.getInstance().getMemberList().isEmpty()) {
            dataController.getPersonHandler().editInfo(dataController.getPersonHandler().findPerson());
        } else {
            SystemPrint.getInstance().printOutMemberListEmpty();
        }
    } // End of method

}
