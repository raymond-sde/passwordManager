package com.teamfivetech.passwordmanager.controller;
import com.apps.util.Prompter;
import com.teamfivetech.passwordmanager.controller.core.*;

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
        if ("".equals(password)) return;

        Login newLogin = new Login(siteName, userName, password );

        try {
            loginIO.write(newLogin);
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

            if ("1".equals(selection)) {
                password = getPrompter().prompt("Enter new password: ");
                break;
            }

            if ("2".equals(selection)) {
                password = generatePasswordFromSecurityLevel();
                break;
            }

            if ("3".equals(selection)) {
                password = generateCustomPassword();
                break;
            }

            if ("4".equals(selection)) break;

            if ("5".equals(selection)) {
                thankUser();
                System.exit(0);
            }
        }
        return password;
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
            for (Login log : readLogins) {
                getPrompter().info(log.toString());
            }
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