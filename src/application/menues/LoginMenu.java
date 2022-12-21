package application.menues;

import application.actors.Employee;
import application.LoginVerification;
import application.utility.SystemPrint;
import application.utility.UI;

public class LoginMenu extends Menu{
    LoginVerification loginVerification = new LoginVerification();

    public LoginMenu(String menuHeader, String leadText, String[] menuOptions) {
        super(menuHeader, leadText, menuOptions);
    }

    public Employee menuLoop() {
        printOptions();
        boolean exit = false;
        while(!exit) {

            switch (UI.getInstance().readInt()) {
                case 1 -> {
                   return loginVerification.verifyLogin();
                }
                case 0 -> exit = true;
                default -> SystemPrint.getInstance().printOut("Invalid input");
            }
        }
        return null;
    }
}
