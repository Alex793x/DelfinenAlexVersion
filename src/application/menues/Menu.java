package application.menues;

import application.utility.SystemPrint;

public abstract class Menu {
    String[] menuOptions;
    String leadText;
    String menuHeader;

    Menu(String menuHeader, String leadText, String[] menuOptions) {
        this.menuHeader = menuHeader;
        this.leadText = leadText;
        this.menuOptions = menuOptions;
    }

    protected void printOptions() {
        SystemPrint.getInstance().printOut(menuHeader);
        for (String options : menuOptions) {
            SystemPrint.getInstance().printOut(options);
        }
    }
}
