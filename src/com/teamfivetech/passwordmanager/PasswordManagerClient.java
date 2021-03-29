package com.teamfivetech.passwordmanager;
import com.apps.util.Prompter;
import java.util.Scanner;

class PasswordManagerClient {
    public static void main(String[] args) {
        PasswordManager pm = new PasswordManager(new Prompter(new Scanner(System.in)));
        pm.start();
    }
}