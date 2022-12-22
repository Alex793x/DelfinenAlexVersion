package application;

import actor_container.CoachMemberAssociation;
import actor_container.ListContainer;
import application.actors.Employee;
import application.actors.MembershipInfo;
import application.actors.Person;
import application.controllers.DataController;
import application.controllers.MenuController;
import application.utility.SystemPrint;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class SystemBoot {
    Employee currentUser;
    DataController dataController = new DataController();
    MenuController menuController = new MenuController();

    SystemBoot() {
        ListContainer.getInstance().getEmployeeList().put(new Employee(Employee.Privilege.ADMIN, "admin", "0000"),
                new Person("John", LocalDate.of(1993,3,2), "422378267", 'M' ));

        ListContainer.getInstance().getEmployeeList().put(new Employee(Employee.Privilege.CHAIRMAN, "chairman", "1234"),
                new Person("Madwill",LocalDate.of(1973, 1, 23),"23114355",'M'));

        ListContainer.getInstance().getEmployeeList().put(new Employee(Employee.Privilege.COACH, "Alexthh", "Frida2020"),
                new Person("Alex",LocalDate.of(1993, 3, 2),"42237826",'M'));

        ListContainer.getInstance().getEmployeeList().put(new Employee(Employee.Privilege.ACCOUNTANT, "account", "0000"),
                new Person("Freddie",LocalDate.of(1993, 3, 2),"42237826",'M'));
        menuUserLoader();
    }

    private void menuUserLoader() {

        while (true) {
            currentUser = menuController.login();
            createRandomMembers();
            createThousandResults();

            if (currentUser != null) {

                switch (currentUser.getPrivilege()) {
                    case ADMIN -> menuController.adminMenu(dataController);
                    case CHAIRMAN -> menuController.chairmanMenu(dataController);
                    case ACCOUNTANT -> menuController.treasuryMenu(dataController);
                    case COACH -> menuController.coachMenu(dataController, currentUser.getID());
                    default -> SystemPrint.getInstance().printOutSomethingWentWrong();
                } // End of Switch case
            } else {
                return;
            } // End of else statement
        } // End of while loop
    } // End of method

    public void createRandomMembers() {
        Random random = new Random();
        int counter = 300;


        while (counter > 1) {
            String[] names = {"Aiden","Aria","Asher","Ava","Camden","Caroline","Chloe","Clara","Daisy","Elizabeth","Ethan",
                    "Evie","Harper","Isabella","Jackson","James","Liam", "Lucas","Lucy","Maeve","Madison","Mabel","Nathaniel",
                    "Olivia","Penelope","Riley","Rowan","Scarlett","Sophia","Thea","Victoria","Violet","William","Wyatt","Xander",
                    "Zachary","Abigail","Amelia","Emma","Isla"};

            ArrayList<MembershipInfo.SwimmingDisciplineType> disciplineTypes = new ArrayList<>();

            MembershipInfo.SwimmingDisciplineType[] types = {
                    MembershipInfo.SwimmingDisciplineType.CRAWL,
                    MembershipInfo.SwimmingDisciplineType.FREESTYLE,
                    MembershipInfo.SwimmingDisciplineType.BUTTERFLY,
                    MembershipInfo.SwimmingDisciplineType.BREASTSTROKE,
                    MembershipInfo.SwimmingDisciplineType.BACKCRAWL
            };

            disciplineTypes.add(types[random.nextInt(5)]);


            MembershipInfo.MemberType type = MembershipInfo.MemberType.COMPETITIVE_SWIMMER;

            LocalDate date = LocalDate.of(LocalDate.now().minusYears(
                            random.nextInt(50)+1).getYear(),
                    random.nextInt(12)+1,
                    random.nextInt(25)+1);

            String phoneNumber = String.valueOf(random.nextInt(88888888) + 11111111);
            char[] gender = {'M','F'};
            boolean[] hasPaid = {true,false};
            boolean[] active = {true,false};

            Person person = new Person(names[random.nextInt(names.length)],
                    date, phoneNumber, gender[random.nextInt(2)]);
            Employee employee = currentUser;
            MembershipInfo membershipInfo = new MembershipInfo(type, disciplineTypes);
            membershipInfo.setHasPaid(hasPaid[random.nextInt(2)]);
            membershipInfo.setMembershipStatus(active[random.nextInt(2)]);

            ListContainer.getInstance().getMemberList().put(membershipInfo, person);
            ListContainer.getInstance().getAssociationHashSet().add(new CoachMemberAssociation<>(employee, membershipInfo, person));
            counter--;
        }
    }

    public void createThousandResults() {
        Random random = new Random();
        ArrayList<MembershipInfo> arrayList = new ArrayList<>(ListContainer.getInstance().getMemberList().keySet());

        for (MembershipInfo membershipInfo : arrayList) {

            for (int j = 0; j < random.nextInt(10) + 1; j++) {
                String[] locations = {"DGI Byen", "Borholm", "Delfinen", "OL", "VM", "EM"};
                LocalDate date = LocalDate.of(LocalDate.now().minusYears(
                                random.nextInt(50) + 1).getYear(),
                        random.nextInt(12) + 1,
                        random.nextInt(25) + 1);
                int swimTime = (random.nextInt(4) * 60) + random.nextInt(59) + 1;
                int[] distance = {100,200,300};
                int rank = random.nextInt(10) + 1;
                boolean[] competitive = {true, false};

                membershipInfo.getResultList().add(new MembershipInfo.SwimmingDisciplineResult(
                        locations[random.nextInt(locations.length)], date,
                        membershipInfo.getActiveDisciplines().get(0),
                        distance[random.nextInt(3)],
                        swimTime, rank, competitive[random.nextInt(2)]
                ));
            }

        }

    }
}
