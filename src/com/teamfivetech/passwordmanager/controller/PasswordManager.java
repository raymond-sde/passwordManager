package com.teamfivetech.passwordmanager.controller;
import com.apps.util.Prompter;
import com.teamfivetech.passwordmanager.core.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PasswordManager {
    private static final String LINE_SEPARATOR = new String(new char[100]).replace("\0", "-");
    private static final int NUMBER_PROMPT_MIN = 1;
    private static final String PW_FILE_PATH = "data/passwords.csv";

    private final Prompter prompter;
    private LoginIO loginIO = new LoginIO(PW_FILE_PATH);

    public PasswordManager(Prompter prompter) {
        this.prompter = prompter;
    }

    public void start() {
        getPrompter().info(LINE_SEPARATOR);

        try {
            Files.lines(Path.of(".\\", "promptTitle.txt")).forEach(System.out::println);
        } catch(IOException e) {
            getPrompter().info(PrompterConstants.READ_TITLE_FAIL + e.getMessage());
        }

        String selection = null;
        List<String> menuOptions = Arrays.asList("Store Login", "List Logins", "Quit");

        while(!"3".equals(selection)) {
            printMenu("main menu", menuOptions);
            selection = promptNumberRange(3);

            if ("1".equals(selection)) storeLogin();
            if ("2".equals(selection)) listLogins();
            if ("3".equals(selection)) thankUser();
        }
    }

    private void storeLogin() {
        String siteName = siteNamePrompt();
        String userName = userNamePrompt();
        String password = getPasswordFromPrompt();

        // User selected cancel, return user to main menu
        if ("".equals(password)) {
            getPrompter().info(PrompterConstants.PW_CANCEL_MSG);
            return;
        }

        Login newLogin = new Login(siteName, userName, password );

        try {
            loginIO.write(newLogin);
            getPrompter().info(LINE_SEPARATOR);
            getPrompter().info(PrompterConstants.WRITE_SUCCESS + "\nSite name: " + siteName + "\nUsername: " + userName + "\nPassword: " + password);
        } catch (IOException e) {
            getPrompter().info(PrompterConstants.WRITE_FAIL + e.getMessage());
        }
    }

    private String getPasswordFromPrompt() {
        String selection;
        List<String> menuOptions = Arrays.asList("Enter New Password", "Easy Generate Password", "Custom Generate Password", "Cancel", "Quit");
        String password = "";

        while(true) {
            printMenu("password options", menuOptions);
            selection = promptNumberRange(5);

            switch (selection) {
                case "1":
                    password = getPrompter().prompt("Enter new password: ");
                    break;
                case "2":
                    password = generatePasswordFromSecurityLevel();
                    break;
                case "3":
                    password = generateCustomPassword();
                    break;
                case"4":
                    break;
                case "5":
                    thankUser();
                    System.exit(0);
            }
        return password;
        }
    }

    private void listLogins() {
        List<Login> readLogins = null;
        try {
            readLogins = loginIO.read();
        } catch (IOException e) {
            getPrompter().info(PrompterConstants.READ_FAIL + e.getMessage());
        }
        if (readLogins.size() == 0) {
            getPrompter().info(LINE_SEPARATOR);
            getPrompter().info(PrompterConstants.READ_FILE_EMPTY);
        }else {
            printLogins(readLogins);
        }
    }

    private void printLogins(List<Login> readLogins) {
        System.out.println(LINE_SEPARATOR);
        System.out.printf("| %-4s", "ID");
        System.out.printf("| %-25s", "Site");
        System.out.printf("| %-25s", "User");
        System.out.printf("| %-25s", "Password");
        System.out.printf("%n");
        System.out.println(LINE_SEPARATOR);

        int index = 0;

        for (Login log : readLogins) {
            System.out.printf("| %-4s", log.getId());
            System.out.printf("| %-25s", log.getSiteName());
            System.out.printf("| %-25s", log.getUserName());
            System.out.printf("| %-25s", log.getPassword());
            System.out.printf("%n");

            if (index != readLogins.size() - 1) {
                System.out.println(LINE_SEPARATOR);
            }

            index++;
        }
    }

    private void thankUser() {
        getPrompter().info("Thank you for using Password Manager");
    }

    private String generatePasswordFromSecurityLevel() {
        String securityLevel = getPrompter().prompt(PrompterConstants.SECURITY_LEVEL_PROMPT, PrompterConstants.SECURITY_LEVEL_REGEX, PrompterConstants.SECURITY_LEVEL_ERROR);
        PasswordGenerator gen = new PasswordGenerator();
        String password = gen.generate(SecurityLevel.valueOf(securityLevel.toUpperCase()));
        return password;
    }

    private String generateCustomPassword() {
        int length = Integer.parseInt(getPrompter().prompt(PrompterConstants.CUSTOM_PW_PROMPT_LENGTH));
        boolean hasUpper = "Y".equalsIgnoreCase(getPrompter().prompt(PrompterConstants.CUSTOM_PW_PROMPT_UPPER));
        boolean hasLower = "Y".equalsIgnoreCase(getPrompter().prompt(PrompterConstants.CUSTOM_PW_PROMPT_LOWER));
        boolean hasNumber = "Y".equalsIgnoreCase(getPrompter().prompt(PrompterConstants.CUSTOM_PW_PROMPT_NUMBER));
        boolean hasSymbol = "Y".equalsIgnoreCase(getPrompter().prompt(PrompterConstants.CUSTOM_PW_PROMPT_SYMBOL));

        Map<String,Boolean> generateOptions = new HashMap<>() {{
            put(PasswordConstants.GET_RANDOM_UPPER, hasUpper);
            put(PasswordConstants.GET_RANDOM_LOWER, hasLower);
            put(PasswordConstants.GET_RANDOM_NUMBER, hasNumber);
            put(PasswordConstants.GET_RANDOM_SYMBOL, hasSymbol);
        }};

        PasswordGenerator gen = new PasswordGenerator();
        String password = gen.generate(length, generateOptions);
        return password;
    }

    private String siteNamePrompt() {
        return getPrompter().prompt(PrompterConstants.SITE_NAME_PROMPT, PrompterConstants.VALID_RESPONSE_REGEX, PrompterConstants.EMPTY_SITE_NAME_ERROR);
    }

    private String userNamePrompt() {
        return getPrompter().prompt(PrompterConstants.USERNAME_PROMPT, PrompterConstants.VALID_RESPONSE_REGEX, PrompterConstants.EMPTY_USERNAME_ERROR);

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