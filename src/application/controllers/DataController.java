package application.controllers;

import application.data_handler.EmployeeHandler;
import application.data_handler.MembershipHandler;
import application.data_handler.PersonHandler;

public class DataController {
    EmployeeHandler employeeHandler = new EmployeeHandler();
    MembershipHandler membershipHandler = new MembershipHandler();
    PersonHandler personHandler = new PersonHandler();

    // Getter ----------------------------------------------------

    public EmployeeHandler getEmployeeHandler() {
        return employeeHandler;
    }

    public MembershipHandler getMembershipHandler() {
        return membershipHandler;
    }

    public PersonHandler getPersonHandler() {
        return personHandler;
    }
}
