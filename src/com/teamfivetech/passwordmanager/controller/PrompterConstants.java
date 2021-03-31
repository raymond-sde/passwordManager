package com.teamfivetech.passwordmanager.controller;

public class PrompterConstants {
    public static final String NUMBER_PROMPT = "Please enter a number ";
    public static final String NUMBER_ERROR = "That is not a valid number";
    public static final String SECURITY_LEVEL_PROMPT = "Please enter level of security (Low, Medium, High): ";
    // low, medium, high (ignore case)
    public static final String SECURITY_LEVEL_REGEX = "(?i)^low$|^medium$|^high$";
    public static final String SECURITY_LEVEL_ERROR = "That is not a valid security level";
    public static final String USERNAME_PROMPT = "Enter new Username: ";
    public static final String SITE_NAME_PROMPT = "Enter new site name: ";
    public static final String VALID_RESPONSE_REGEX = "^[a-zA-Z0-9!@#$%^&*().]+$";
    public static final String EMPTY_SITE_NAME_ERROR = "Site name is blank or has invalid characters";
    public static final String EMPTY_USERNAME_ERROR = "Username is blank or has invalid characters";
    public static final String WRITE_SUCCESS = "New Login successfully saved for: ";
    public static final String WRITE_FAIL = "New Login creation failed: ";
    public static final String READ_FAIL = "Password retrieval failed: ";
    public static final String READ_FILE_EMPTY = "There are no Logins currently stored in Password Manager";
}