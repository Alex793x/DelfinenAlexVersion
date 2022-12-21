package actor_container;

import application.actors.Employee;
import application.actors.MembershipInfo;
import application.actors.Person;

import java.util.HashMap;
import java.util.HashSet;


// This class contains all lists holding association between Employee and the person, Membership and Person of membership and association
public class ListContainer {
    private static final ListContainer SingletonListContainer = new ListContainer();

    private HashMap<Employee, Person> employeeList;
    private HashMap<MembershipInfo, Person> memberList;
    private HashSet<CoachMemberAssociation<Employee, MembershipInfo, Person>> associationHashSet;

    // Constructor -----------------------------------------------
    private ListContainer() {
        employeeList = new HashMap<>();
        memberList = new HashMap<>();
        associationHashSet = new HashSet<>();
    }

    public static ListContainer getInstance() {
        return SingletonListContainer;
    }

    // Getter ---------------------------------------------------
    public HashMap<Employee, Person> getEmployeeList() {
        return employeeList;
    }

    public HashMap<MembershipInfo, Person> getMemberList() {
        return memberList;
    }

    public HashSet<CoachMemberAssociation<Employee, MembershipInfo, Person>> getAssociationHashSet() {
        return associationHashSet;
    }

    // Setter ----------------------------------------------------

    public void setEmployeeList(HashMap<Employee, Person> employeeList) {
        this.employeeList = employeeList;
    }

    public void setMemberList(HashMap<MembershipInfo, Person> memberList) {
        this.memberList = memberList;
    }

    public void setAssociationHashSet(HashSet<CoachMemberAssociation<Employee, MembershipInfo, Person>> associationHashSet) {
        this.associationHashSet = associationHashSet;
    }
}
