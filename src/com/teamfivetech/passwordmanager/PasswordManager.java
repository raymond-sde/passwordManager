package com.teamfivetech.passwordmanager;
import com.apps.util.Prompter;

import java.io.IOException;
import java.util.*;

public class PasswordManager {
    private static final String LINE_SEPARATOR = new String(new char[30]).replace("\0", "-");
    private static final int NUMBER_PROMPT_MIN = 1;
    private static final String PW_FILE_PATH = "data/passwords.csv";

    private final Prompter prompter;
    private CsvUtil csvUtil = new CsvUtil(PW_FILE_PATH);

    public PasswordManager(Prompter prompter) {
        this.prompter = prompter;
    }

    public void start() {
        getPrompter().info(LINE_SEPARATOR);
        getPrompter().info("Welcome to Password Manager");

        String selection = null;
        List<String> menuOptions = Arrays.asList("Store Password", "List Passwords", "Quit");

        while(!"3".equals(selection)) {
            printMenu("main menu", menuOptions);
            selection = printNumberPrompt(3);

            if ("1".equals(selection)) storePassword();
            if ("2".equals(selection)) listPasswords();
            if ("3".equals(selection)) thankUser();
        }
    }

    public void storePassword() {
        String selection;
        List<String> menuOptions = Arrays.asList("Enter New Password", "Generate New Password", "Back to Main Menu", "Quit");

        while (true) {
            printMenu("store password", menuOptions);
            selection = printNumberPrompt(4);

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
        List<Login> readLogins = null;
        try {
            readLogins = csvUtil.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Login log : readLogins) {
            getPrompter().info(log.toString());
        }
    }

    private void thankUser() {
        getPrompter().info("Thank you for using Password Manager");
    }

    // TODO: implement CSV write functionality
    public void enterPassword() {
        getPrompter().info("enterNewPassword()");
    }

    // TODO: implement Password Generator / CSV write functionality
    public void generatePassword() {
        String securityLevel = getPrompter().prompt(PrompterConstants.SECURITY_LEVEL_PROMPT, PrompterConstants.SECURITY_LEVEL_REGEX, PrompterConstants.SECURITY_LEVEL_ERROR);
        getPrompter().info("Selected: " + securityLevel);
    }

    private void printMenu(String title, List<String> menuOptions) {
        getPrompter().info(LINE_SEPARATOR);
        getPrompter().info(title.toUpperCase());

        int index = 0;

        for (String menuOption : menuOptions) {
            getPrompter().info("[" + (index + 1) + "] " + menuOption);
            index++;
        }

        getPrompter().info(LINE_SEPARATOR);
    }

    private String printNumberPrompt(int max) {
        String range = NUMBER_PROMPT_MIN + "-" + max;
        String userPrompt = PrompterConstants.NUMBER_PROMPT + range + ": ";
        // number within range
        String regex = "[" + range + "]";
        return getPrompter().prompt(userPrompt, regex, PrompterConstants.NUMBER_ERROR);
    }

    public Prompter getPrompter() {
        return prompter;
    }
}