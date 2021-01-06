/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controller.AccountController;
import helper.MenuHelper;

/**
 *
 * @author heaty566
 */
public class App {

    // Controller instance
    private static final AccountController CONTROLLER = new AccountController();

    //submenu for user options
    private static void subMenuLogin() {
        MenuHelper subMenuShow = new MenuHelper(5);
        subMenuShow.add("Withdrawal");
        subMenuShow.add("Deposit");
        subMenuShow.add("Transfer");
        subMenuShow.add("Remove account");
        subMenuShow.add("Exit");
        int choice;
        do {
            System.out.println("----> User Menu");
            choice = subMenuShow.getChoice();

            switch (choice) {
                case 1:
                    CONTROLLER.withdrawalMoney();
                    break;
                case 2:
                    CONTROLLER.depositMoney();
                    break;
                case 3:
                    CONTROLLER.transferMoney();
                    break;
                case 4:
                    CONTROLLER.deleteAccount();
                    return;
            }

        } while (choice >= 1 && choice < 5);
        //submenu for print out options
    }

    public static void main(String[] args) {
        //initialized for everything
        CONTROLLER.decodeFile();
        MenuHelper mainMenu = new MenuHelper(6);
        mainMenu.add("Create new account");
        mainMenu.add("login  account");
        mainMenu.add("to exit");

        //main mainMenu
        int choice;
        do {
            System.out.println("---- Welcome to Secret Bank -------");
            choice = mainMenu.getChoice();

            switch (choice) {
                case 1:
                    CONTROLLER.addNewAccount();
                    break;
                case 2:
                    if (CONTROLLER.login()) {
                        subMenuLogin();
                    }

                    break;

            }

        } while (choice >= 1 && choice < 3);
        CONTROLLER.saveOnFile();
    }

}
