package application.filehandler;

import actor_container.CoachMemberAssociation;
import actor_container.ListContainer;
import application.actors.Employee;
import application.actors.MembershipInfo;
import application.actors.Person;
import application.controllers.DataController;
import application.utility.SystemPrint;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicReference;

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
        } catch (FileNotFoundException e) {
            SystemPrint.getInstance().printOutFileNotExist();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
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
        } catch (FileNotFoundException e) {
            SystemPrint.getInstance().printOutFileNotExist();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
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

    public HashSet<CoachMemberAssociation<Employee, MembershipInfo, Person>> readAssociationList() {
        HashSet<CoachMemberAssociation<Employee, MembershipInfo, Person>> associations = new HashSet<>();
        try {
            reader = new BufferedReader(new java.io.FileReader("associationlist.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(";");
                int employeeID = Integer.parseInt(tokens[0]);
                int swimmerID = Integer.parseInt(tokens[1]);

                AtomicReference<Employee> employeePerson = new AtomicReference<>();
                AtomicReference<MembershipInfo> membershipInfo = new AtomicReference<>();
                AtomicReference<Person> swimmer = new AtomicReference<>();
                ListContainer.getInstance().getMemberList()
                        .forEach((membership, person) -> {
                            if (person.getID() == swimmerID) {
                                swimmer.set(person);
                                membershipInfo.set(membership);
                            }
                        } );
                ListContainer.getInstance().getEmployeeList()
                        .forEach((employee, person) -> {
                            if (employee.getID() == employeeID) {
                                employeePerson.set(employee);
                            }
                        });

                associations.add(new CoachMemberAssociation<>(employeePerson.get(),membershipInfo.get(),swimmer.get()));
            }
            reader.close();
            return associations;
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

                ListContainer.getInstance().getMemberList()
                        .forEach( (membershipInfo, person) -> {
                            if (person.getID() == personID) {
                                membershipInfo.getResultList()
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
