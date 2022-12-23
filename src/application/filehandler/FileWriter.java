package application.filehandler;

import actor_container.CoachMemberAssociation;
import actor_container.ListContainer;
import application.actors.Employee;
import application.actors.MembershipInfo;
import application.actors.Person;
import application.utility.SystemPrint;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;


public class FileWriter {
    BufferedWriter writer;

    public void writeToMemberList() {

        try {
            writer = new BufferedWriter(new java.io.FileWriter("memberlist.txt"));

            for (Map.Entry<MembershipInfo, Person> set : ListContainer.getInstance().getMemberList().entrySet()) {
                writer.write(set.getKey().getMemberType() + ";");
                writer.write(set.getKey().isMembershipStatus() + ";");
                writer.write(set.getKey().isHasPaid() + ";");
                writer.write(set.getValue().getID() + ";");
                writer.write(set.getValue().getName() + ";");
                writer.write(set.getValue().getAge() + ";");
                writer.write(set.getValue().getPhoneNumber() + ";");
                writer.write(set.getValue().getGender() + ";");
                for (int i = 0; i < set.getKey().getResultList().size(); i++) {
                    writer.write(set.getKey().getActiveDisciplines().get(i) + ";");
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            SystemPrint.getInstance().printOutFileNotExist();
        }
    }

    public void writeToEmployeeList() {

        try {
            writer = new BufferedWriter(new java.io.FileWriter("employeelist.txt"));

            for (Map.Entry<Employee, Person> set : ListContainer.getInstance().getEmployeeList().entrySet()) {
                writer.write(set.getKey().getID()+";");
                writer.write(set.getKey().getPrivilege()+";");
                writer.write(set.getKey().getUsername()+";");
                writer.write(set.getValue().getName()+";");
                writer.write(set.getValue().getAge()+";");
                writer.write(set.getValue().getPhoneNumber()+";");
                writer.write(set.getValue().getGender()+";");
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            SystemPrint.getInstance().printOutFileNotExist();
        }
    }

    public void writeToAssociationList() {

        try {
            writer = new BufferedWriter(new java.io.FileWriter("associationlist.txt"));

            for (CoachMemberAssociation<Employee,MembershipInfo,Person> set : ListContainer.getInstance().getAssociationHashSet()) {
                writer.write(set.getEmployee().getID()+";");
                writer.write(set.getPerson().getID()+"");
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            SystemPrint.getInstance().printOutFileNotExist();
        }
    }

    public void writeToResultList() {

        try {
            writer = new BufferedWriter(new java.io.FileWriter("resultlist.txt"));

            for (CoachMemberAssociation<Employee, MembershipInfo, Person> set : ListContainer.getInstance().getAssociationHashSet()) {
                set.getMember().getResultList()
                        .forEach(result -> {
                            try {
                                writer.write(set.getPerson().getID()+";");
                                writer.write(result.location()+";");
                                writer.write(result.dateOfEvent()+";");
                                writer.write(result.swimmingDisciplineType()+";");
                                writer.write(result.distance()+";");
                                writer.write(result.swimTime()+";");
                                writer.write(result.rank()+";");
                                writer.write(result.isCompetitive()+";");
                                writer.newLine();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }
            writer.close();
        } catch (IOException e) {
            SystemPrint.getInstance().printOutFileNotExist();
        }
    }

    public void writeToLoginCredentials() {

        try {
            writer = new BufferedWriter(new java.io.FileWriter("login.txt"));

            for(Map.Entry<Employee, Person> set : ListContainer.getInstance().getEmployeeList().entrySet()) {
                writer.write(set.getKey().getUsername()+";");
                writer.write(set.getKey().getPassword()+";");
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            SystemPrint.getInstance().printOutFileNotExist();
        }
    }
}
