package com.teamfivetech.passwordmanager.controller.client;
import com.apps.util.Prompter;
import com.teamfivetech.passwordmanager.controller.PasswordManager;

import java.util.Scanner;

class PasswordManagerClient {
    public static void main(String[] args) {
        PasswordManager pm = new PasswordManager(new Prompter(new Scanner(System.in)));
        pm.start();
    }
}