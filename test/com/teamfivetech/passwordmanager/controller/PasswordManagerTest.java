package com.teamfivetech.passwordmanager.controller;

import com.apps.util.Prompter;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class PasswordManagerTest {
    private static final String RANGE = 1 + "-" + 3;
    private static final String NUMBER_RANGE_REGEX = "[" + RANGE + "]";

    @Test
    public void prompterShouldRead_whenValidNumberResponse() throws Exception {
        Prompter prompter = new Prompter(new Scanner(new File("responses/validNumberResponses.txt")));
        String userPrompt = PrompterConstants.NUMBER_PROMPT + RANGE + ": ";
        String numberPrompt;

        numberPrompt = prompter.prompt(userPrompt, NUMBER_RANGE_REGEX, PrompterConstants.NUMBER_ERROR);
        assertEquals("1", numberPrompt);

        numberPrompt = prompter.prompt(userPrompt, NUMBER_RANGE_REGEX, PrompterConstants.NUMBER_ERROR);
        assertEquals("2", numberPrompt);

        numberPrompt = prompter.prompt(userPrompt, NUMBER_RANGE_REGEX, PrompterConstants.NUMBER_ERROR);
        assertEquals("3", numberPrompt);
    }

    @Test
    public void prompterShouldThrowException_whenInvalidNumberRegex() {
        try {
            Prompter prompter = new Prompter(new Scanner(new File("responses/invalidNumberResponses.txt")));
            String userPrompt = PrompterConstants.NUMBER_PROMPT + RANGE + ": ";
            String numberPrompt = prompter.prompt(userPrompt, NUMBER_RANGE_REGEX, PrompterConstants.NUMBER_ERROR);

            // prompt() should return null if regex fails
            assertNull(numberPrompt);

            fail("Should throw exception");
        } catch(Exception e) {
            String expectedMsg = "No line found";
            assertEquals(expectedMsg, e.getMessage());
        }
    }

    @Test
    public void prompterShouldRead_whenValidSecurityLevelResponse() throws Exception {
        Prompter prompter = new Prompter(new Scanner(new File("responses/validSecurityLevelResponses.txt")));
        String securityLevelPrompt;

        securityLevelPrompt = prompter.prompt(PrompterConstants.SECURITY_LEVEL_PROMPT, PrompterConstants.SECURITY_LEVEL_REGEX, PrompterConstants.SECURITY_LEVEL_ERROR);
        assertEquals("low", securityLevelPrompt);

        securityLevelPrompt = prompter.prompt(PrompterConstants.SECURITY_LEVEL_PROMPT, PrompterConstants.SECURITY_LEVEL_REGEX, PrompterConstants.SECURITY_LEVEL_ERROR);
        assertEquals("LOW", securityLevelPrompt);

        securityLevelPrompt = prompter.prompt(PrompterConstants.SECURITY_LEVEL_PROMPT, PrompterConstants.SECURITY_LEVEL_REGEX, PrompterConstants.SECURITY_LEVEL_ERROR);
        assertEquals("medium", securityLevelPrompt);

        securityLevelPrompt = prompter.prompt(PrompterConstants.SECURITY_LEVEL_PROMPT, PrompterConstants.SECURITY_LEVEL_REGEX, PrompterConstants.SECURITY_LEVEL_ERROR);
        assertEquals("MEDIUM", securityLevelPrompt);

        securityLevelPrompt = prompter.prompt(PrompterConstants.SECURITY_LEVEL_PROMPT, PrompterConstants.SECURITY_LEVEL_REGEX, PrompterConstants.SECURITY_LEVEL_ERROR);
        assertEquals("high", securityLevelPrompt);

        securityLevelPrompt = prompter.prompt(PrompterConstants.SECURITY_LEVEL_PROMPT, PrompterConstants.SECURITY_LEVEL_REGEX, PrompterConstants.SECURITY_LEVEL_ERROR);
        assertEquals("HIGH", securityLevelPrompt);
    }

    @Test
    public void prompterShouldThrowException_whenInvalidSecurityLevelRegex() {
        try {
            Prompter prompter = new Prompter(new Scanner(new File("responses/invalidSecurityLevelResponses.txt")));
            String securityLevelPrompt = prompter.prompt(PrompterConstants.SECURITY_LEVEL_PROMPT, PrompterConstants.SECURITY_LEVEL_REGEX, PrompterConstants.SECURITY_LEVEL_ERROR);

            // prompt() should return null if regex fails
            assertNull(securityLevelPrompt);

            fail("Should throw exception");
        } catch(Exception e) {
            String expectedMsg = "No line found";
            assertEquals(expectedMsg, e.getMessage());
        }
    }

    @Test
    public void prompter_shouldRead_whenValidUserNameResponse() throws Exception {
        Prompter prompter = new Prompter(new Scanner(new File("responses/validUserNameResponses.txt")));
        String userNamePrompt;

        userNamePrompt = prompter.prompt(PrompterConstants.USERNAME_PROMPT, PrompterConstants.VALID_RESPONSE_REGEX, PrompterConstants.EMPTY_USERNAME_ERROR);
        assertEquals("CoolUserName", userNamePrompt);

        userNamePrompt = prompter.prompt(PrompterConstants.USERNAME_PROMPT, PrompterConstants.VALID_RESPONSE_REGEX, PrompterConstants.EMPTY_USERNAME_ERROR);
        assertEquals("FunUserName", userNamePrompt);

        userNamePrompt = prompter.prompt(PrompterConstants.USERNAME_PROMPT, PrompterConstants.VALID_RESPONSE_REGEX, PrompterConstants.EMPTY_USERNAME_ERROR);
        assertEquals("GoodUserName", userNamePrompt);
    }

    @Test
    public void prompter_shouldRead_whenValidSiteNameResponse() throws Exception {
        Prompter prompter = new Prompter(new Scanner(new File("responses/validSiteNameResponses.txt")));
        String userNamePrompt;

        userNamePrompt = prompter.prompt(PrompterConstants.SITE_NAME_PROMPT, PrompterConstants.VALID_RESPONSE_REGEX, PrompterConstants.EMPTY_SITE_NAME_ERROR);
        assertEquals("Reddit", userNamePrompt);

        userNamePrompt = prompter.prompt(PrompterConstants.SITE_NAME_PROMPT, PrompterConstants.VALID_RESPONSE_REGEX, PrompterConstants.EMPTY_SITE_NAME_ERROR);
        assertEquals("YouTube", userNamePrompt);

        userNamePrompt = prompter.prompt(PrompterConstants.SITE_NAME_PROMPT, PrompterConstants.VALID_RESPONSE_REGEX, PrompterConstants.EMPTY_SITE_NAME_ERROR);
        assertEquals("Google", userNamePrompt);
    }

    @Test
    public void prompter_shouldThrowException_whenInvalidUserNameResponse() {
        try {
            Prompter prompter = new Prompter((new Scanner(new File("responses/invalidUserNameResponses.txt"))));
            String userNamePrompt;

            userNamePrompt = prompter.prompt(PrompterConstants.USERNAME_PROMPT, PrompterConstants.VALID_RESPONSE_REGEX, PrompterConstants.EMPTY_USERNAME_ERROR);

            assertNull(userNamePrompt);
            fail("Should throw exception");
        } catch(Exception e){
            assertEquals("No line found", e.getMessage());
        }
    }

    @Test
    public void prompter_shouldThrowException_whenInvalidSiteNameResponse() {
        try {
            Prompter prompter = new Prompter((new Scanner(new File("responses/invalidSiteNameResponses.txt"))));
            String siteNamePrompt;

            siteNamePrompt = prompter.prompt(PrompterConstants.SITE_NAME_PROMPT, PrompterConstants.VALID_RESPONSE_REGEX, PrompterConstants.EMPTY_SITE_NAME_ERROR);

            assertNull(siteNamePrompt);
            fail("Should throw exception");
        } catch(Exception e){
            assertEquals("No line found", e.getMessage());
        }
    }

    @Test
    public void prompter_shouldRead_whenValidPasswordLengthResponse() throws FileNotFoundException {
        Prompter prompter = new Prompter(new Scanner(new File("responses/validPasswordLengthResponses.txt")));
        String passwordLengthPrompt;

        passwordLengthPrompt = prompter.prompt(PrompterConstants.CUSTOM_PW_PROMPT_LENGTH, PrompterConstants.CUSTOM_PW_LENGTH_REGEX, PrompterConstants. CUSTOM_PW_LENGTH_ERROR);
        assertEquals("1", passwordLengthPrompt);

        passwordLengthPrompt = prompter.prompt(PrompterConstants.CUSTOM_PW_PROMPT_LENGTH, PrompterConstants.CUSTOM_PW_LENGTH_REGEX, PrompterConstants. CUSTOM_PW_LENGTH_ERROR);
        assertEquals("5", passwordLengthPrompt);

        passwordLengthPrompt = prompter.prompt(PrompterConstants.CUSTOM_PW_PROMPT_LENGTH, PrompterConstants.CUSTOM_PW_LENGTH_REGEX, PrompterConstants. CUSTOM_PW_LENGTH_ERROR);
        assertEquals("10", passwordLengthPrompt);
    }

    @Test
    public void prompterShouldThrowException_whenInvalidPasswordLengthResponse() {
        try {
            Prompter prompter = new Prompter(new Scanner(new File("responses/invalidPasswordLengthResponses.txt")));
            String passwordLengthPrompt = prompter.prompt(PrompterConstants.CUSTOM_PW_PROMPT_LENGTH, PrompterConstants.CUSTOM_PW_LENGTH_REGEX, PrompterConstants. CUSTOM_PW_LENGTH_ERROR);

            // prompt() should return null if regex fails
            assertNull(passwordLengthPrompt);

            fail("Should throw exception");
        } catch(Exception e) {
            String expectedMsg = "No line found";
            assertEquals(expectedMsg, e.getMessage());
        }
    }

    @Test
    public void prompter_shouldRead_whenValidYesNoResponse() throws FileNotFoundException {
        Prompter prompter = new Prompter(new Scanner(new File("responses/validYesNoResponses.txt")));
        String yesNoPrompt;

        yesNoPrompt = prompter.prompt(PrompterConstants.CUSTOM_PW_PROMPT_UPPER, PrompterConstants.CUSTOM_PW_OPTION_REGEX, PrompterConstants.CUSTOM_PW_OPTION_ERROR);
        assertEquals("y", yesNoPrompt);

        yesNoPrompt = prompter.prompt(PrompterConstants.CUSTOM_PW_PROMPT_NUMBER, PrompterConstants.CUSTOM_PW_OPTION_REGEX, PrompterConstants.CUSTOM_PW_OPTION_ERROR);
        assertEquals("Y", yesNoPrompt);

        yesNoPrompt = prompter.prompt(PrompterConstants.CUSTOM_PW_PROMPT_LOWER, PrompterConstants.CUSTOM_PW_OPTION_REGEX, PrompterConstants.CUSTOM_PW_OPTION_ERROR);
        assertEquals("n", yesNoPrompt);

        yesNoPrompt = prompter.prompt(PrompterConstants.CUSTOM_PW_PROMPT_SYMBOL, PrompterConstants.CUSTOM_PW_OPTION_REGEX, PrompterConstants.CUSTOM_PW_OPTION_ERROR);
        assertEquals("N", yesNoPrompt);
    }

    @Test
    public void prompterShouldThrowException_whenInvalidYesNoResponse() {
        try {
            Prompter prompter = new Prompter(new Scanner(new File("responses/invalidYesNoResponses.txt")));
            String yesNoPrompt = prompter.prompt(PrompterConstants.CUSTOM_PW_PROMPT_UPPER, PrompterConstants.CUSTOM_PW_OPTION_REGEX, PrompterConstants.CUSTOM_PW_OPTION_ERROR);

            // prompt() should return null if regex fails
            assertNull(yesNoPrompt);

            fail("Should throw exception");
        } catch(Exception e) {
            String expectedMsg = "No line found";
            assertEquals(expectedMsg, e.getMessage());
        }
    }
}