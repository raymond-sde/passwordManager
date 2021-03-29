package com.teamfivetech.passwordmanager;
import com.apps.util.Prompter;
import java.util.*;

public class PasswordManager {
    private static final String LINE_SEPARATOR = new String(new char[30]).replace("\0", "-");
    private static final int NUMBER_PROMPT_MIN = 1;

    private final Prompter prompter;

    public PasswordManager(Prompter prompter) {
        this.prompter = prompter;
    }

    public void start() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Welcome to Password Manager");

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
        String securityLevel = prompter.prompt(PrompterConstants.SECURITY_LEVEL_PROMPT, PrompterConstants.SECURITY_LEVEL_REGEX, PrompterConstants.SECURITY_LEVEL_ERROR);
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

    private String printNumberPrompt(int max) {
        String range = NUMBER_PROMPT_MIN + "-" + max;
        String userPrompt = PrompterConstants.NUMBER_PROMPT + range + ": ";
        // number within range
        String regex = "[" + range + "]";
        return prompter.prompt(userPrompt, regex, PrompterConstants.NUMBER_ERROR);
    }
}