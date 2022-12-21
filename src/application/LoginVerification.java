package application;

import actor_container.ListContainer;
import application.actors.Employee;
import application.utility.SystemPrint;
import application.utility.UI;

public class LoginVerification {

    public Employee verifyLogin() {
        int tries = 3;

        while (tries != 0) {
            String username = UI.getInstance().readUsername();
            String password = UI.getInstance().readPassword();
            for (Employee employee : ListContainer.getInstance().getEmployeeList().keySet()) {
                if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                     return employee;
                }
            }
            SystemPrint.getInstance().printOut("Username or Password invalid");
            tries--;
        }
        return null;
    } // End of method
}
