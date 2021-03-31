package com.teamfivetech.passwordmanager.controller.core;

public interface EncoderDecoder {

    public abstract String encode(byte[] input);

    public abstract byte[] decode(String input);
}
