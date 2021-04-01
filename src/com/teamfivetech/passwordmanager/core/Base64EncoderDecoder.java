package com.teamfivetech.passwordmanager.core;

import java.util.Base64;

class Base64EncoderDecoder implements EncoderDecoder {

    private Base64.Decoder decoder = Base64.getDecoder();
    private  Base64.Encoder encoder = Base64.getEncoder();

    @Override       // interface EncoderDecoder
    public String encode(String input) {
        byte[] inputByte = input.getBytes();
        return encoder.encodeToString(inputByte);
    }

    @Override       // interface EncoderDecoder
    public String decode(String input) {
        String output = new String(decoder.decode(input));
        return output;
    }
}