package com.teamfivetech.passwordmanager.client;
import com.apps.util.Prompter;
import com.apps.util.SplashApp;
import com.teamfivetech.passwordmanager.controller.PasswordManager;

import java.util.Scanner;

class PasswordManagerClient implements SplashApp {

    @Override
    public void start() {
        PasswordManager pm = new PasswordManager(new Prompter(new Scanner(System.in)));
        pm.initialize();
    }

    public static void main(String[] args) {
        PasswordManagerClient pmc = new PasswordManagerClient();
        pmc.welcome(2000, "images/team5_pm_logo.png");
        pmc.start();
    }
}