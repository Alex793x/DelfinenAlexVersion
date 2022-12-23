package actor_container;

public class CoachMemberAssociation<Employee, MembershipInfo, Person>{

    private Employee employee;
    private final MembershipInfo member;
    private final Person person;


    // Constructor -----------------------------------------------
    public CoachMemberAssociation(Employee employee, MembershipInfo member, Person person) {
        this.employee = employee;
        this.member = member;
        this.person = person;
    }

    // Getter -------------------------------------
    public Employee getEmployee() {
        return employee;
    }

    public MembershipInfo getMember() {
        return member;
    }

    public Person getPerson() {
        return person;
    }

    // Setter --------------------------------------
}
