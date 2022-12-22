package application.filehandler;

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

public class FileReader {
    BufferedReader reader;

    public void readMemberList(DataController dataController) {
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
                LocalDate personAge = LocalDate.parse(tokens[4]);
                String personPhoneNumber = tokens[5];
                char personGender = tokens[6].charAt(0);
                ArrayList<MembershipInfo.SwimmingDisciplineType> types = new ArrayList<>();
                for (String token : tokens) {
                    if (token.equalsIgnoreCase("crawl") || token.equalsIgnoreCase("butterfly") ||
                            token.equalsIgnoreCase("breaststroke") || token.equalsIgnoreCase("backcrawl") ||
                            token.equalsIgnoreCase("freestyle")) {
                        types.add(MembershipInfo.SwimmingDisciplineType.valueOf(token));
                    }
                }

                ListContainer.getInstance().getMemberList().put(new MembershipInfo(memberType, types),
                        new Person(personName, personAge, personPhoneNumber, personGender));
                dataController.getMembershipHandler().getLastLasMember().setMembershipStatus(isMembershipStatus);
                dataController.getMembershipHandler().getLastLasMember().setHasPaid(isHasPaid);

            }

            reader.close();
        } catch (FileNotFoundException e) {
            SystemPrint.getInstance().printOutFileNotExist();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readEmployeeList() {

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

                ListContainer.getInstance().getEmployeeList()
                        .put(new Employee(privilege, username, ""), new Person(personName, personAge, phoneNumber, gender));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            SystemPrint.getInstance().printOutFileNotExist();
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
}
