package com.teamfivetech.passwordmanager.controller;

public class PrompterConstants {
    public static final String NUMBER_PROMPT = "Please enter a number ";
    public static final String NUMBER_ERROR = "That is not a valid number";
    public static final String SECURITY_LEVEL_PROMPT = "Please enter level of security (Low, Medium, High): ";
    public static final String SECURITY_LEVEL_REGEX = "(?i)^low$|^medium$|^high$";
    public static final String SECURITY_LEVEL_ERROR = "That is not a valid security level";
    public static final String USERNAME_PROMPT = "Enter new Username: ";
    public static final String SITE_NAME_PROMPT = "Enter new site name: ";
    public static final String PASSWORD_PROMPT = "Enter new password: ";
    public static final String VALID_RESPONSE_REGEX = "^[a-zA-Z0-9!@#$%^&*().]+$";
    public static final String EMPTY_SITE_NAME_ERROR = "Site name is blank or has invalid characters";
    public static final String EMPTY_USERNAME_ERROR = "Username is blank or has invalid characters";
    public static final String EMPTY_PASSWORD_ERROR = "Password is blank or has invalid characters";
    public static final String WRITE_SUCCESS = "New Login successfully saved: ";
    public static final String WRITE_FAIL = "New Login creation failed: ";
    public static final String READ_FAIL = "Password retrieval failed: ";
    public static final String READ_FILE_EMPTY = "There are no Logins currently stored in Password Manager";
    public static final String CUSTOM_PW_PROMPT_LENGTH = "Enter desired password length (number): ";
    public static final String CUSTOM_PW_LENGTH_REGEX = "^[1-9][0-9]*$";
    public static final String CUSTOM_PW_LENGTH_ERROR = "Password must have a length of 1 or more characters";
    public static final String CUSTOM_PW_PROMPT_UPPER = "Include uppercase letters? (Y/N): ";
    public static final String CUSTOM_PW_PROMPT_LOWER = "Include lowercase letters? (Y/N): ";
    public static final String CUSTOM_PW_PROMPT_NUMBER = "Include numbers? (Y/N): ";
    public static final String CUSTOM_PW_PROMPT_SYMBOL = "Include symbols? (Y/N): ";
    public static final String CUSTOM_PW_OPTION_REGEX = "^(?i)(?:y|n)$";
    public static final String CUSTOM_PW_OPTION_ERROR = "Invalid answer. Please provide either \"Y\" or \"N\"";
    public static final String CUSTOM_PW_LENGTH_LESS_THAN_OPTIONS_ERROR = "Password length cannot be less than number of included characters";
    public static final String CUSTOM_PW_REQUIRED_ONE_OPTION_ERROR = "Password must include at least 1 of the following: uppercase, lowercase, number, or symbol";
    public static final String PW_CANCEL_MSG = "Store login has been cancelled";
    public static final String THANK_USER = "Thank you for using Password Manager";
}