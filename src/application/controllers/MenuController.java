package application.controllers;

import application.actors.Employee;
import application.menues.*;

public class MenuController {


    /*
     * This method is for chairman, and will only be created upon chairman login
     */
    public void chairmanMenu(DataController dataController) {
        ChairmanMenu chairmanMenu = new ChairmanMenu("WELCOME CHAIRMAN", "Please choose ", new String[] {
                "1. Add Member",
                "2. Delete Member",
                "3. Edit Membership status",
                "4. Edit Person info",
                "0. Logout"
        });
        chairmanMenu.menuLoop(dataController);
    }


    /*
     * This method is the login procedure, returning an employee if login is successful
     */
    public Employee login() {
        LoginMenu loginMenu = new LoginMenu("DELFINEN", "Please Choose", new String[]{
                "1. Login",
                "2. Exit"
        });
        return loginMenu.menuLoop();
    }

    /*
     * This method is for coaches, and will only be accessible if employee login is a coach
     */
    public void coachMenu(DataController dataController) {
        CoachMenu coachMenu = new CoachMenu("WELCOME COACH", "Please choose ", new String[] {
                "1. Add new swimming result",
                "2. Delete swimming result",
                "3. Print out all Swimmers associated with this account",
                "4. Print out all results associated with this account",
                "5. Enter \"Print Result Menu\"",
                "0. Logout"
        });
        coachMenu.menuLoop(dataController);
    }


    /*
     * This method is for admins
     */
    public void adminMenu(DataController dataController) {
        AdminMenu adminMenu = new AdminMenu("## - > ADMIN < - ##", "CHOICES", new String[] {
                "1. Create Users",
                "2. Delete Users",
                "3. Enter \"Advanced Options\"",
                "0. Logout"
        });
        adminMenu.menuLoop(dataController);
    }


    public void treasuryMenu(DataController dataController) {
        TreasuryMenu treasuryMenu = new TreasuryMenu("WELCOME TREASURER", "Please choose", new String[] {
                "1. Print members in arrears",
                "2. Change member arrears status",
                "3. Print Club Economy",
                "0. Logout"
        });
        treasuryMenu.menuLooping(dataController);
    }
}
