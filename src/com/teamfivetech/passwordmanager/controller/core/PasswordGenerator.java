package com.teamfivetech.passwordmanager.controller.core;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class PasswordGenerator {
    private static final int UPPERCASE_MIN = 65;
    private static final int UPPERCASE_MAX = 90;
    private static final int LOWERCASE_MIN = 97;
    private static final int LOWERCASE_MAX = 122;
    private static final int NUMBER_MIN = 48;
    private static final int NUMBER_MAX = 57;
    private static final String SYMBOLS = "!@#$%^&*()";
    private static final Random rand = new Random();
    private static final String GET_RANDOM_LOWER = "getRandomLower";
    private static final String GET_RANDOM_UPPER = "getRandomUpper";
    private static final String GET_RANDOM_NUMBER = "getRandomNumber";
    private static final String GET_RANDOM_SYMBOL = "getRandomSymbol";

    private final Map<String, Callable<String>> generateActions = new HashMap<>() {{
        put(GET_RANDOM_LOWER, () -> getRandomLower());
        put(GET_RANDOM_UPPER, () -> getRandomUpper());
        put(GET_RANDOM_NUMBER, () -> getRandomNumber());
        put(GET_RANDOM_SYMBOL, () -> getRandomSymbol());
    }};

    public String generate(SecurityLevel securityLevel) {
        String result = "";
        List<String> actions = new ArrayList<>();
        switch (securityLevel) {
            // 8 length, 4 upper, 4 lower
            case LOW:
                actions.add(GET_RANDOM_UPPER);
                actions.add(GET_RANDOM_LOWER);
                result = generateByLengthActions(securityLevel.getPasswordLength(), actions);
                break;
            // 12 length, 4 upper, 4 lower, 4 number
            case MEDIUM:
                actions.add(GET_RANDOM_UPPER);
                actions.add(GET_RANDOM_LOWER);
                actions.add(GET_RANDOM_NUMBER);
                result = generateByLengthActions(securityLevel.getPasswordLength(), actions);
                break;
            // 16 length, 4 upper, 4 lower, 4 number, 4 symbol
            case HIGH:
                actions.add(GET_RANDOM_UPPER);
                actions.add(GET_RANDOM_LOWER);
                actions.add(GET_RANDOM_NUMBER);
                actions.add(GET_RANDOM_SYMBOL);
                result = generateByLengthActions(securityLevel.getPasswordLength(), actions);
                break;
            default:
                System.out.println("Invalid security level");
        }
        return result;
    }

    private Map<String, Callable<String>> findActionsByName(List<String> actions) {
        return generateActions.entrySet().stream()
                // keep only the map entries that match the actions list
                .filter(map -> actions.contains(map.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private String generateByLengthActions(int length, List<String> actions) {
        String result;
        List<String> characters = new ArrayList<>();
        int actionCount = findActionsByName(actions).values().size();

        if (actionCount == 0) return "";

        for (int i = 0; i < length; i += actionCount) {
            findActionsByName(actions).values().forEach(callable -> {
                try {
                    characters.add(callable.call());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

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