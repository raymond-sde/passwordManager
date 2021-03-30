package com.teamfivetech.passwordmanager.controller.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordGeneratorTest {
    // 8 length, 4 upper, 4 lower in any order
    private static final String SECURITY_LEVEL_LOW_REGEX =
            "^(?=(.*[A-Z]){4})(?=(.*[a-z]){4}).{8}$";
    // 12 length, 4 upper, 4 lower, 4 number in any order
    private static final String SECURITY_LEVEL_MEDIUM_REGEX =
            "^(?=(.*[A-Z]){4})(?=(.*[a-z]){4})(?=(.*[0-9]){4}).{12}$";
    // 16 length, 4 upper, 4 lower, 4 number, 4 symbol in any order
    private static final String SECURITY_LEVEL_HIGH_REGEX =
            "^(?=(.*[A-Z]){4})(?=(.*[a-z]){4})(?=(.*[0-9]){4})(?=(.*[!@#$%^&*()]){4}).{16}$";

    private PasswordGenerator passwordGenerator;

    @Before
    public void setUp() {
        passwordGenerator = new PasswordGenerator();
    }

    @Test
    public void generate_shouldReturnStringEightLengthFourUpperFourLower_whenSecurityLevelLow() {
        for (int i = 0; i < 5; i++) {
            String password = passwordGenerator.generate(SecurityLevel.LOW);
            assertTrue(password.matches(SECURITY_LEVEL_LOW_REGEX));
        }
    }

    @Test
    public void generate_shouldReturnStringTwelveLengthFourUpperFourLowerFourNumber_whenSecurityLevelMedium() {
        for (int i = 0; i < 5; i++) {
            String password = passwordGenerator.generate(SecurityLevel.MEDIUM);
            assertTrue(password.matches(SECURITY_LEVEL_MEDIUM_REGEX));
        }
    }

    @Test
    public void generate_shouldReturnStringSixteenLengthFourUpperFourLowerFourNumberFourSymbol_whenSecurityLevelHigh() {
        for (int i = 0; i < 5; i++) {
            String password = passwordGenerator.generate(SecurityLevel.HIGH);
            assertTrue(password.matches(SECURITY_LEVEL_HIGH_REGEX));
        }
    }
}