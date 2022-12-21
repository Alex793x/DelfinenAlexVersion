package application.actors;

public class Employee {

    // Privilege level to use sections of the program
    public enum Privilege {
        ADMIN,
        CHAIRMAN,
        ACCOUNTANT,
        COACH
    }

    private final Privilege privilege;
    private String username;
    private String password;
    private static int uniqueID = 0;
    private final int ID = uniqueID++;

    // Constructor -------------------------------------------------
    public Employee(Privilege privilege, String username, String password) {
        this.privilege = privilege;
        this.username = username;
        this.password = password;
    }

    // Getter -----------------------------------------------------
    public Privilege getPrivilege() {
        return privilege;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getID() {
        return ID;
    }

    // Setter -----------------------------------------------------
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
