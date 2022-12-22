package application.controllers;

import application.data_handler.AssociationHandler;
import application.data_handler.EmployeeHandler;
import application.data_handler.MembershipHandler;
import application.data_handler.PersonHandler;

public class DataController {
    EmployeeHandler employeeHandler = new EmployeeHandler();
    MembershipHandler membershipHandler = new MembershipHandler();
    PersonHandler personHandler = new PersonHandler();
    AssociationHandler associationHandler = new AssociationHandler();

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

    public AssociationHandler getAssociationHandler() {
        return associationHandler;
    }
}
