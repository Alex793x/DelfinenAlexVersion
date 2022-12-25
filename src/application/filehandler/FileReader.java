package application.filehandler;

import actor_container.CoachMemberAssociation;
import actor_container.ListContainer;
import application.actors.Employee;
import application.actors.MembershipInfo;
import application.actors.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class FileReader {
    BufferedReader reader;

    public LinkedHashMap<MembershipInfo, Person> readMemberList() {
        LinkedHashMap<MembershipInfo, Person> map = new LinkedHashMap<>();
        try {
            reader = new BufferedReader(new java.io.FileReader("memberlist.txt"));
            String line;
            while ((line = reader.readLine()) != null) {

                String[] tokens = line.split(";");
                MembershipInfo.MemberType memberType = MembershipInfo.MemberType.valueOf(tokens[0]);
                boolean isMembershipStatus = Boolean.parseBoolean(tokens[1]);
                boolean isHasPaid = Boolean.parseBoolean(tokens[2]);
                int personID = Integer.parseInt(tokens[3]);
                String personName = tokens[4];
                LocalDate personAge = LocalDate.parse(tokens[5]);
                String personPhoneNumber = tokens[6];
                char personGender = tokens[7].charAt(0);
                ArrayList<MembershipInfo.SwimmingDisciplineType> types = new ArrayList<>();
                for (String token : tokens) {
                    if (token.equalsIgnoreCase("crawl") || token.equalsIgnoreCase("butterfly") ||
                            token.equalsIgnoreCase("breaststroke") || token.equalsIgnoreCase("backcrawl") ||
                            token.equalsIgnoreCase("freestyle")) {
                        types.add(MembershipInfo.SwimmingDisciplineType.valueOf(token));
                    }
                }
                MembershipInfo membershipInfo = new MembershipInfo(memberType, types);
                Person person = new Person(personName,personAge,personPhoneNumber,personGender);
                membershipInfo.setMembershipStatus(isMembershipStatus);
                membershipInfo.setHasPaid(isHasPaid);
                map.put(membershipInfo,person);
            }
            reader.close();
            return map;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkedHashMap<Employee, Person> readEmployeeList() {
        LinkedHashMap<Employee, Person> map = new LinkedHashMap<>();
        try {
            reader = new BufferedReader(new java.io.FileReader("employeelist.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(";");
                Employee.Privilege privilege = Employee.Privilege.valueOf(tokens[1]);
                String username = tokens[2];
                String personName = tokens[3];
                LocalDate personAge = LocalDate.parse(tokens[4]);
                String phoneNumber = tokens[5];
                char gender = tokens[6].charAt(0);

                map.put(new Employee(privilege, username, ""), new Person(personName, personAge, phoneNumber, gender));
            }
            reader.close();
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readLoginCredentials() {

        try {
          reader = new BufferedReader(new java.io.FileReader("login.txt"));
          String line;
          while((line = reader.readLine()) != null) {
              String[] tokens = line.split(";");
              String username = tokens[0];
              String password = tokens[1];
              ListContainer.getInstance().getEmployeeList().forEach((employee, person) -> {
                  if (employee.getUsername().equals(username)) {
                      employee.setPassword(password);
                  }
              });
          }
          reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readAssociationList() {
        HashSet<CoachMemberAssociation<Employee, MembershipInfo, Person>> associations = new HashSet<>();
        try {
            reader = new BufferedReader(new java.io.FileReader("associationlist.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(";");
                int employeeID = Integer.parseInt(tokens[0]);
                int swimmerID = Integer.parseInt(tokens[1]);

                ListContainer.getInstance().getMemberList()
                        .forEach((membershipInfo, swimmer) -> {
                            if (swimmer.getID() == swimmerID) {

                                ListContainer.getInstance().getEmployeeList()
                                        .forEach((employee, person) -> {
                                            if (employee.getID() == employeeID) {
                                                ListContainer.getInstance().getAssociationHashSet()
                                                        .add(new CoachMemberAssociation<>(employee, membershipInfo, swimmer));
                                            }
                                        });

                            }
                        } );
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readResults() {
        try {
            reader = new BufferedReader(new java.io.FileReader("resultlist.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(";");
                int personID = Integer.parseInt(tokens[0]);
                String location = tokens[1];
                LocalDate dateOfEvent = LocalDate.parse(tokens[2]);
                MembershipInfo.SwimmingDisciplineType disciplineType = MembershipInfo.SwimmingDisciplineType.valueOf(tokens[3]);
                int distance = Integer.parseInt(tokens[4]);
                int swimTime = Integer.parseInt(tokens[5]);
                int rank = Integer.parseInt(tokens[6]);
                boolean competitiveness = Boolean.parseBoolean(tokens[7]);

                ListContainer.getInstance().getAssociationHashSet()
                        .forEach( (association) -> {
                            if (association.getPerson().getID() == personID) {
                                association.getMember().getResultList()
                                        .add(new MembershipInfo.SwimmingDisciplineResult(
                                                location,
                                                dateOfEvent,
                                                disciplineType,
                                                distance,
                                                swimTime,
                                                rank,
                                                competitiveness
                                                ));
                            }
                        });
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
