package application.controllers;

import application.actors.Employee;
import application.menues.*;

public class MenuController {


    /*
     * This method is for chairman, and will only be created upon chairman login
     */
    public void chairmanMenu(DataController dataController, FileController fileController) {
        ChairmanMenu chairmanMenu = new ChairmanMenu("WELCOME CHAIRMAN", "Please choose ", new String[] {
                "1. Add Member",
                "2. Delete Member",
                "3. Edit Membership status",
                "4. Edit Person info",
                "0. Logout"
        });
        chairmanMenu.menuLoop(dataController, fileController);
    }


    /*
     * This method is the login procedure, returning an employee if login is successful
     */
    public Employee login() {
        LoginMenu loginMenu = new LoginMenu("DELFINEN", "Please Choose", new String[]{
                "1. Login",
                "0. Exit"
        });
        return loginMenu.menuLoop();
    }

    /*
     * This method is for coaches, and will only be accessible if employee login is a coach
     */
    public void coachMenu(DataController dataController, FileController fileController, int ID) {
        CoachMenu coachMenu = new CoachMenu("WELCOME COACH", "Please choose ", new String[] {
                "1. Add new swimming result",
                "2. Delete swimming result",
                "3. Enter \"Print Swimmer Menu\"",
                "4. Enter \"Print Result Menu\"",
                "0. Logout"
        });
        coachMenu.menuLoop(dataController, fileController,ID);
    }


    /*
     * This method is for admins
     */
    public void adminMenu(DataController dataController, FileController fileController) {
        AdminMenu adminMenu = new AdminMenu("## - > ADMIN < - ##", "CHOICES", new String[] {
                "1. Create Users",
                "2. Delete Users",
                "3. Enter \"Advanced Options\"",
                "0. Logout"
        });
        adminMenu.menuLoop(dataController, fileController);
    }


    public void treasuryMenu(DataController dataController, FileController fileController) {
        TreasuryMenu treasuryMenu = new TreasuryMenu("WELCOME TREASURER", "Please choose", new String[] {
                "1. Print all members arrears status",
                "2. Check specific member arrears status",
                "3. Print Club Economy",
                "0. Logout"
        });
        treasuryMenu.menuLooping(dataController, fileController);
    }
}
