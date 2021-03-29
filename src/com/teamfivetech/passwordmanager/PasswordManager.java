package com.teamfivetech.passwordmanager;

import com.apps.util.Prompter;

import java.util.*;

public class PasswordManager {
    private static final String LINE_SEPARATOR = new String(new char[30]).replace("\0", "-");
    private static final int MENU_SELECTION_MIN = 1;

    public void start() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Welcome to Password Manager");

        String selection = null;
        Prompter prompter = new Prompter(new Scanner(System.in));
        List<String> menuOptions = Arrays.asList("Store Password", "List Passwords", "Quit");

        while(!"3".equals(selection)) {
            printMenu("main menu", menuOptions);
            selection = printPrompt(prompter, 3);

            if ("1".equals(selection)) storePassword();
            if ("2".equals(selection)) listPasswords();
            if ("3".equals(selection)) thankUser();
        }
    }

    public void storePassword() {
        String selection = null;
        Prompter prompter = new Prompter(new Scanner(System.in));
        List<String> menuOptions = Arrays.asList("Enter New Password", "Generate New Password", "Back to Main Menu", "Quit");

        while (true) {
            printMenu("store password", menuOptions);
            selection = printPrompt(prompter, 4);

            if ("1".equals(selection)) enterPassword();
            if ("2".equals(selection)) generatePassword();
            if ("3".equals(selection)) break;
            if ("4".equals(selection)) {
                thankUser();
                System.exit(0);
            }
        }
    }

    // TODO: implement CSV read functionality
    public void listPasswords() {
        System.out.println("listPasswords()");
    }

    private void thankUser() {
        System.out.println("Thank you for using Password Manager");
    }

    // TODO: implement CSV write functionality
    public void enterPassword() {
        System.out.println("enterNewPassword()");
    }

    // TODO: implement Password Generator / CSV write functionality
    public void generatePassword() {
        Prompter prompter = new Prompter(new Scanner(System.in));
        String securityLevel = prompter.prompt("Please enter level of security (Easy, Medium, Hard): ");
        System.out.println("Selected: " + securityLevel);
    }

    private void printMenu(String title, List<String> menuOptions) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(title.toUpperCase());

        int index = 0;

        for (String menuOption : menuOptions) {
            System.out.println("[" + (index + 1) + "] " + menuOption);
            index++;
        }

        System.out.println(LINE_SEPARATOR);
    }

    private String printPrompt(Prompter prompter, int max) {
        String userPrompt = "Please enter a number " + MENU_SELECTION_MIN + " - " + max + ": ";
        String rangeRegex = "[" + MENU_SELECTION_MIN + "-" + max + "]";
        String validationError = "That is not a valid number";
        return prompter.prompt(userPrompt, rangeRegex, validationError);
    }
}