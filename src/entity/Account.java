/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.*;

/**
 *
 * @author heaty566
 */
public class Account {

    private String id;
    private String name;
    private String password;
    private double amount;

    public Account() {
    }

    public Account(String id, String name, String password, double amount) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-20s %-20s", id, name, amount);
    }

}
