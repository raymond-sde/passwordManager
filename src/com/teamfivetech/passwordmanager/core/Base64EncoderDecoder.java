package com.teamfivetech.passwordmanager.core;

import java.util.Base64;

class Base64EncoderDecoder implements EncoderDecoder {

    private Base64.Decoder decoder = Base64.getDecoder();
    private  Base64.Encoder encoder = Base64.getEncoder();

    @Override       // interface EncoderDecoder
    public String encode(byte[] input) {
        return encoder.encodeToString(input);
    }

    @Override       // interface EncoderDecoder
    public byte[] decode(String input) {
        return decoder.decode(input);
    }
}