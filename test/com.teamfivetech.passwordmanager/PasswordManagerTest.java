package com.teamfivetech.passwordmanager;

import com.apps.util.Prompter;
import org.junit.Test;

import java.io.File;
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
            Prompter prompter = new Prompter(new Scanner(new File("responses/invalidNumberPrompt.txt")));
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
            Prompter prompter = new Prompter(new Scanner(new File("responses/invalidSecurityLevelPrompt.txt")));
            String securityLevelPrompt = prompter.prompt(PrompterConstants.SECURITY_LEVEL_PROMPT, PrompterConstants.SECURITY_LEVEL_REGEX, PrompterConstants.SECURITY_LEVEL_ERROR);

            // prompt() should return null if regex fails
            assertNull(securityLevelPrompt);

            fail("Should throw exception");
        } catch(Exception e) {
            String expectedMsg = "No line found";
            assertEquals(expectedMsg, e.getMessage());
        }
    }
}