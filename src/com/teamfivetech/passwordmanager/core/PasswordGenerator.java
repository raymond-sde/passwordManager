package com.teamfivetech.passwordmanager.core;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class PasswordGenerator {
    // ASCII code [A-Z]
    private static final int UPPERCASE_MIN = 65;
    private static final int UPPERCASE_MAX = 90;
    // ASCII code [a-z]
    private static final int LOWERCASE_MIN = 97;
    private static final int LOWERCASE_MAX = 122;
    // ASCII code [0-9]
    private static final int NUMBER_MIN = 48;
    private static final int NUMBER_MAX = 57;
    // Allowed symbols in password
    private static final String SYMBOLS = "!@#$%^&*().";
    private static final Random rand = new Random();

    // Store generate methods in HashMap for easy filtering and invoking within an iteration
    private final Map<String, Callable<String>> generateActions = new HashMap<>() {{
        put(PasswordConstants.GET_RANDOM_LOWER, () -> getRandomLower());
        put(PasswordConstants.GET_RANDOM_UPPER, () -> getRandomUpper());
        put(PasswordConstants.GET_RANDOM_NUMBER, () -> getRandomNumber());
        put(PasswordConstants.GET_RANDOM_SYMBOL, () -> getRandomSymbol());
    }};

    /**
     * Generates a password based on preset security profiles (LOW, MEDIUM, HIGH)
     */
    public String generate(SecurityLevel securityLevel) {
        String result = "";
        // Store desired actions (generateActions HashMap keys)
        List<String> actions = new ArrayList<>();
        switch (securityLevel) {
            // 8 length, 4 upper, 4 lower
            case LOW:
                actions.add(PasswordConstants.GET_RANDOM_UPPER);
                actions.add(PasswordConstants.GET_RANDOM_LOWER);
                result = generateByLengthActions(securityLevel.getPasswordLength(), actions);
                break;
            // 12 length, 4 upper, 4 lower, 4 number
            case MEDIUM:
                actions.add(PasswordConstants.GET_RANDOM_UPPER);
                actions.add(PasswordConstants.GET_RANDOM_LOWER);
                actions.add(PasswordConstants.GET_RANDOM_NUMBER);
                result = generateByLengthActions(securityLevel.getPasswordLength(), actions);
                break;
            // 16 length, 4 upper, 4 lower, 4 number, 4 symbol
            case HIGH:
                actions.add(PasswordConstants.GET_RANDOM_UPPER);
                actions.add(PasswordConstants.GET_RANDOM_LOWER);
                actions.add(PasswordConstants.GET_RANDOM_NUMBER);
                actions.add(PasswordConstants.GET_RANDOM_SYMBOL);
                result = generateByLengthActions(securityLevel.getPasswordLength(), actions);
                break;
            default:
                System.out.println("Invalid security level");
        }
        return result;
    }

    /**
     * Generates a password based on custom user preferences of desired length, uppercase, lowercase, number, symbol
     */
    public String generate(int length, Map<String,Boolean> generateOptions) {
        Map<String,Boolean> options = generateOptions.entrySet().stream()
                .filter(Map.Entry::getValue)
                .collect(Collectors.toMap(Map.Entry::getKey, stringBooleanEntry -> true));

        List<String> actions = new ArrayList<>(options.keySet());

        String password = generateByLengthActions(length, actions);
        // Trim any remaining characters that exceed desired length
        password = password.substring(0, Math.min(password.length(), length));

        return password;
    }

    /**
     * Returns filtered generateActions HashMap by desired actions
     */
    private Map<String, Callable<String>> findActionsByName(List<String> actions) {
        return generateActions.entrySet().stream()
                // keep only the map entries that match the actions list
                .filter(map -> actions.contains(map.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Generates password on varying length and complexity
     */
    private String generateByLengthActions(int length, List<String> actions) {
        String result = "";
        List<String> characters = new ArrayList<>();
        int actionCount = findActionsByName(actions).values().size();

        if (actionCount == 0) return result;

        for (int i = 0; i < length; i += actionCount) {
            findActionsByName(actions).values().forEach(callable -> {
                try {
                    characters.add(callable.call());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // Randomize character index locations
        Collections.shuffle(characters);
        result = String.join("", characters);

        return result;
    }

    private String getRandomUpper() {
        int randomUpperAsciiCode = rand.nextInt(UPPERCASE_MAX - UPPERCASE_MIN + 1) + UPPERCASE_MIN;
        return Character.toString((char) randomUpperAsciiCode);
    }

    private String getRandomLower() {
        int randomUpperAsciiCode = rand.nextInt(LOWERCASE_MAX - LOWERCASE_MIN + 1) + LOWERCASE_MIN;
        return Character.toString((char) randomUpperAsciiCode);
    }

    private String getRandomNumber() {
        int randomUpperAsciiCode = rand.nextInt(NUMBER_MAX - NUMBER_MIN + 1) + NUMBER_MIN;
        return Character.toString((char) randomUpperAsciiCode);
    }

    private String getRandomSymbol() {
        int index = rand.nextInt(SYMBOLS.length());
        return Character.toString(SYMBOLS.charAt(index));
    }
}