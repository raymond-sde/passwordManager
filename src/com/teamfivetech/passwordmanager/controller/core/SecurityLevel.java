package com.teamfivetech.passwordmanager.controller.core;

public enum SecurityLevel {
    // security levels with preset password lengths
    LOW(8), MEDIUM(12), HIGH(16);

    private final int passwordLength;

    SecurityLevel(int passwordLength) {
        this.passwordLength = passwordLength;
    }
    public int getPasswordLength() {
        return this.passwordLength;
    }
}