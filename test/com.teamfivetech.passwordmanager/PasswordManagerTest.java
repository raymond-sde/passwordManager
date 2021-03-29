package com.teamfivetech.passwordmanager;

import com.apps.util.Prompter;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class PasswordManagerTest {

    @Test
    public void printNumberPrompt_shouldAcceptValidUserPrompt() throws Exception {
        Prompter prompter = new Prompter(new Scanner(new File("responses/responses.txt")));

        String userPrompt = "Please enter a number " + 1 + " - " + 3 + ": ";
        String rangeRegex = "[" + 1 + "-" + 3 + "]";
        String validationErrorMsg = "That is not a valid number";
        String menuPrompt = prompter.prompt(userPrompt, rangeRegex, validationErrorMsg);

        // insert assertions
    }
}