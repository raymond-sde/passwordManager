package com.teamfivetech.passwordmanager.controller;
import com.apps.util.Prompter;
import com.teamfivetech.passwordmanager.controller.core.LoginIO;
import com.teamfivetech.passwordmanager.controller.core.Login;
import com.teamfivetech.passwordmanager.controller.core.PasswordGenerator;
import com.teamfivetech.passwordmanager.controller.core.SecurityLevel;

import java.io.IOException;
import java.util.*;

public class PasswordManager {
    private static final String LINE_SEPARATOR = new String(new char[30]).replace("\0", "-");
    private static final int NUMBER_PROMPT_MIN = 1;
    private static final String PW_FILE_PATH = "data/passwords.csv";

    private final Prompter prompter;
    private LoginIO loginIO = new LoginIO(PW_FILE_PATH);

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
            selection = promptNumberRange(3);

            if ("1".equals(selection)) storePassword();
            if ("2".equals(selection)) listPasswords();
            if ("3".equals(selection)) thankUser();
        }
    }

    private void storePassword() {
        String selection;
        List<String> menuOptions = Arrays.asList("Enter New Password", "Generate New Password", "Back to Main Menu", "Quit");

        while (true) {
            printMenu("store password", menuOptions);
            selection = promptNumberRange(4);

            if ("1".equals(selection)) enterPassword();
            if ("2".equals(selection)) generatePassword();
            if ("3".equals(selection)) break;
            if ("4".equals(selection)) {
                thankUser();
                System.exit(0);
            }
        }
    }

    private void listPasswords() {
        List<Login> readLogins = null;
        try {
            readLogins = loginIO.read();
        } catch (IOException e) {
            e.getMessage();
        }
        for (Login log : readLogins) {
            getPrompter().info(log.toString());
        }
    }

    private void thankUser() {
        getPrompter().info("Thank you for using Password Manager");
    }

    private void enterPassword() {
        String siteName = siteNamePrompt();
        String userName = userNamePrompt();
        String password = getPrompter().prompt("Enter new password: ");
        Login newLogin = new Login(siteName, userName, password );
        try {
            loginIO.write(newLogin);
            getPrompter().info(PrompterConstants.WRITE_SUCCESS + newLogin.getSiteName());
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private void generatePassword() {
        String siteName = siteNamePrompt();
        String userName = userNamePrompt();
        String securityLevel = getPrompter().prompt(PrompterConstants.SECURITY_LEVEL_PROMPT, PrompterConstants.SECURITY_LEVEL_REGEX, PrompterConstants.SECURITY_LEVEL_ERROR);
        PasswordGenerator gen = new PasswordGenerator();
        String password = gen.generate(SecurityLevel.valueOf(securityLevel.toUpperCase()));
        Login newLogin = new Login(siteName, userName, password);
        try {
            loginIO.write(newLogin);
            getPrompter().info(PrompterConstants.WRITE_SUCCESS + newLogin.getSiteName());
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private String siteNamePrompt() {
        return getPrompter().prompt(PrompterConstants.SITE_NAME_PROMPT, PrompterConstants.VALID_RESPONSE_REGEX, PrompterConstants.EMPTY_USERNAME_ERROR);
    }

    private String userNamePrompt() {
        return getPrompter().prompt(PrompterConstants.USERNAME_PROMPT, PrompterConstants.VALID_RESPONSE_REGEX, PrompterConstants.EMPTY_SITE_NAME_ERROR);

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

    private String promptNumberRange(int max) {
        String range = NUMBER_PROMPT_MIN + "-" + max;
        String userPrompt = PrompterConstants.NUMBER_PROMPT + range + ": ";
        // number within range
        String regex = "[" + range + "]";
        return getPrompter().prompt(userPrompt, regex, PrompterConstants.NUMBER_ERROR);
    }

    private Prompter getPrompter() {
        return prompter;
    }
}