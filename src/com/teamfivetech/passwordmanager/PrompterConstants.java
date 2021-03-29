package com.teamfivetech.passwordmanager;

public class PrompterConstants {
    public static final String NUMBER_PROMPT = "Please enter a number ";
    public static final String NUMBER_ERROR = "That is not a valid number";
    public static final String SECURITY_LEVEL_PROMPT = "Please enter level of security (Low, Medium, High): ";
    // low, medium, high (ignore case)
    public static final String SECURITY_LEVEL_REGEX = "(?i)^low$|^medium$|^high$";
    public static final String SECURITY_LEVEL_ERROR = "That is not a valid security level";
}