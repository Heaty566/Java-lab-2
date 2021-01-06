package controller;

import entity.Account;

import helper.ScannerCus;
import helper.Validator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author heaty566
 */
public class AccountController extends ArrayList<Account> {

    private static final ScannerCus SC = new ScannerCus();
    private int currentUser;

    public boolean login() {
        System.out.println("Login user information");
        System.out.print("Enter account id (xxxxxx):");
        String id = SC.getString(Validator.StringType.ALPHANUM, 6, 6,
                "Account id should be a string (lettter and number) with 6 characters. ");

        System.out.print("Enter account password:");
        String password = SC.getPassword(
                "Account password should be a string with ( 1 lettter, 1 number, 1 lowercase character , 1 uppercase character ) and length is 6-255 characters");

        int index = this.findOneById(id);
        if (index == -1) {
            System.out.println("ID or password are invalid");

            return false;
        }

        Account account = this.get(index);
        if (account.getPassword().equals(encrypt(password))) {
            this.currentUser = index;
            return true;
        }

        System.out.println("ID or password are invalid");
        return false;
    }

    public void depositMoney() {
        System.out.print("How much would you like to deposit? :");
        double amount = SC.getDouble(0, 1000000000, "Deposit amount should be a double from 0 - 1000000000. ");
        System.out.println("Are you sure to delete your account ?");
        boolean isConfirm = SC.getBoolean();
        if (isConfirm) {
            Account account = this.get(currentUser);
            account.setAmount(account.getAmount() + amount);
            this.set(currentUser, account);
            System.out.println("Deposited to your account");
            return;
        }
        System.out.println("Thank you");
    }

    public void withdrawalMoney() {

        Account account = this.get(currentUser);
        if (account.getAmount() == 0) {
            System.out.println("-> You have $0 in your account, please deposit before withdrawal");
            return;
        }

        System.out.println("How much would you like to withdrawal? :");
        System.out.print("Enter amount:");
        double amount = SC.getDouble(0, account.getAmount(), "Withdrawal amount should be a double from 0 - " + account.getAmount());
        System.out.println("Are you sure to withdraw amount of money ?");

        boolean isConfirm = SC.getBoolean();
        if (isConfirm) {
            account.setAmount(account.getAmount() - amount);
            this.set(currentUser, account);
            System.out.println("Here is your money: $" + amount);

        }
        System.out.println("Thank you");
    }

    public void transferMoney() {
        Account account = this.get(currentUser);
        if (account.getAmount() == 0) {
            System.out.println("-> You have $0 in your account, please deposit before transfer money");
            return;
        }
        System.out.println("Enter id (xxxxxx) account would you like to transfer?");
        System.out.print("Enter your Id:");
        String id = SC.getString(Validator.StringType.ALPHANUM, 6, 6, "Account id should be a string (lettter only) with 6 characters. ");
        int index = this.findOneById(id);
        if (index == -1) {
            System.out.println("User with the given Id was not found");
            return;
        }
        Account toAccount = this.get(index);
        if (account.getId().equals(toAccount.getId())) {
            System.out.println("Can not transform to yourself");
            return;
        }
        System.out.print("How much would you like to withdrawal? :");
        double amount = SC.getDouble(0, account.getAmount(), "Ammount should be a double from 0 - " + account.getAmount());

        System.out.println("Are you sure to transfer money to " + toAccount.getName() + " ? ");
        boolean isConfirm = SC.getBoolean();
        if (isConfirm) {
            account.setAmount(account.getAmount() - amount);
            this.set(currentUser, account);
            toAccount.setAmount(toAccount.getAmount() + amount);
            this.set(index, toAccount);

            System.out.println("Successfuly");
            System.out.println("Thank you");

        }
        System.out.println("Thank you");
    }

    /*
    / @params id: the id of banking account
    / @return return the index of animal in array
     */
    private int findOneById(String id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    /*
    / @Function:  delete a exist animal with id
     */
    public void deleteAccount() {
        System.out.println("Are you sure to delete your account ?");
        boolean isDelete = SC.getBoolean();
        if (isDelete) {
            this.remove(currentUser);
            System.out.println("User has been deleted");
            System.out.println("Thank you");
        } else {
            System.out.println("Thank you");
        }
        System.out.println("----------------------------------------------------");
    }

    /*
    / @Function: update an exist animal in araray list
     */
    private String encrypt(String value) {
        String result = "";
        for (int i = 0; i < value.length(); i++) {
            char c = (char) (((int) value.charAt(i) + 123) % 256);
            result += c;
        }

        return result;
    }

    /*
    / @Function: common input design for reactor code in common
     */
    public void addNewAccount() {
        String id;
        do {
            System.out.print("Enter account id (xxxxxx):");
            id = SC.getString(Validator.StringType.ALPHANUM, 6, 6,
                    "Account id should be a string (lettter only) with 6 characters. ");
            if (this.findOneById(id) != -1) {
                System.out.println("This id is taken");

            } else {
                break;
            }

        } while (true);

        System.out.print("Enter account name:");
        String name = SC.getString(Validator.StringType.ALPHABET, 1, 100,
                "Account name should be a string (lettter only) with 1-100 characters. ");
        String userPassword;
        do {
            System.out.print("Enter account password:");
            String password = SC.getPassword(
                    "Account password should be a string with ( 1 lettter, 1 number, 1 lowercase character , 1 uppercase character ) and length is 6-255 characters");
            System.out.print("Enter account confirm password:");
            String confirmPassword = SC.getPassword("Confirm password should be match with password");
            if (password.equals(confirmPassword)) {
                userPassword = password;
                break;
            } else {
                System.out.println("The password and confirm password do match");
            }
        } while (true);

        Account dto = new Account();
        dto.setId(id);
        dto.setName(name);
        dto.setPassword(encrypt(userPassword));
        dto.setAmount(0);
        this.add(dto);

        System.out.println("Registered an account");
        System.out.println("-------------------------------------------------");
    }

    /*
    / @Function: read data from animal file allow, and tranform it to entity object
     */
    public void decodeFile() {
        // decode data at the first time
        try {
            File file = new File("bank.dat");
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);

            String ep;
            while ((ep = bf.readLine()) != null) {

                StringTokenizer stk = new StringTokenizer(ep, ";");
                String id = stk.nextToken();
                String name = stk.nextToken();
                String password = stk.nextToken();
                double amount = Double.parseDouble(stk.nextToken());

                Account addAccount = new Account(id, name, password, amount);

                this.add(addAccount);
            }

            fr.close();

        } catch (IOException e) {
            System.out.println("Some thing goes wrong");
        } catch (NumberFormatException err) {
            System.out.println(err);
        }

    }

    /*
    / @Function: write data in arraylist to animals.txt file;
     */
    public void saveOnFile() {
        // save file
        try {
            File file = new File("bank.dat");

            PrintWriter pw = new PrintWriter(file);
            this.stream().forEach((item) -> {

                String encypt = item.getId() + ";" + item.getName() + ";" + item.getPassword() + ";" + item.getAmount();
                pw.println(encypt);
            });

            System.out.println("List has been saved");
            pw.close();

        } catch (IOException e) {
            System.out.println("Some thing goes wrong");
        }

    }
}
