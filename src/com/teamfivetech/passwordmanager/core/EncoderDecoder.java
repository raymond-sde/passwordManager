package com.teamfivetech.passwordmanager.core;

public interface EncoderDecoder {

    public abstract String encode(String input);

    public abstract String decode(String input);
}
