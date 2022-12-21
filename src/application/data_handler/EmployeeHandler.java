package application.data_handler;

import actor_container.ListContainer;
import application.actors.Employee;
import application.actors.Person;
import application.utility.SystemPrint;
import application.utility.UI;

import java.util.Map;

public class EmployeeHandler {


    public Employee createEmployee() {
        SystemPrint.getInstance().promptPrivilege();
        Employee.Privilege privilege = UI.getInstance().privilege();

        SystemPrint.getInstance().promptUsername();
        String username = UI.getInstance().readLine();

        SystemPrint.getInstance().promptPassword();
        String password = UI.getInstance().readLine();

        return new Employee(privilege,username,password);

    } 
    
    public void deleteUser() {
        Employee foundEmployee = findEmployee();
        if (foundEmployee != null) {
            ListContainer.getInstance().getEmployeeList().remove(foundEmployee);
            SystemPrint.getInstance().printOutEmployeeRemoved();
        }
    }
    
    public Employee findEmployee() {
        String employeeName = UI.getInstance().readLine();
        for (Map.Entry<Employee, Person> set : ListContainer.getInstance().getEmployeeList().entrySet()) {
            if (!set.getValue().getName().equals(employeeName)) continue; {
                return set.getKey();
            }
        }
        SystemPrint.getInstance().printOutEmployeeNotExist();
        return null;
    }

    public Employee adminAccount() {
        for (Map.Entry<Employee, Person> set : ListContainer.getInstance().getEmployeeList().entrySet()) {
            if (!set.getKey().getUsername().equals("admin")) continue; {
                return set.getKey();
            }
        }
        SystemPrint.getInstance().printOutSomethingWentWrong();
        return null;
    }
}
