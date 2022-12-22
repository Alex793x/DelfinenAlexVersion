package application.menues;

import application.controllers.DataController;
import application.utility.SystemPrint;
import application.utility.UI;

public class TreasuryMenu extends Menu{
    public TreasuryMenu(String menuHeader, String leadText, String[] menuOptions) {
        super(menuHeader, leadText, menuOptions);
    }

    public void menuLooping(DataController dataController) {
        while(true) {
            printOptions();
            switch (UI.getInstance().readInt()) {
                case 1 -> dataController.getMembershipHandler().printMembersArrears();
                case 2 -> dataController.getMembershipHandler().checkOneMemberArrears(dataController);
                case 3 -> dataController.getMembershipHandler().printClubEconomy();
                case 0 -> {return;}
                default -> SystemPrint.getInstance().printOutInvalidInput();
            }
        }
    }
}
