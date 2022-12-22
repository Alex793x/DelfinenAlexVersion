package application.controllers;

import application.data_handler.*;

public class DataController {
    EmployeeHandler employeeHandler = new EmployeeHandler();
    MembershipHandler membershipHandler = new MembershipHandler();
    PersonHandler personHandler = new PersonHandler();
    AssociationHandler associationHandler = new AssociationHandler();
    PersonComparatorHandler comparatorHandler = new PersonComparatorHandler();
    MembershipInfoComparatorHandler membershipInfoComparatorHandler = new MembershipInfoComparatorHandler();
    ResultComparatorHandler resultComparatorHandler = new ResultComparatorHandler();

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

    public PersonComparatorHandler getPersonComparatorHandler() {
        return comparatorHandler;
    }

    public MembershipInfoComparatorHandler getMembershipInfoComparatorHandler() {
        return membershipInfoComparatorHandler;
    }

    public ResultComparatorHandler getResultComparatorHandler() {
        return resultComparatorHandler;
    }
}
