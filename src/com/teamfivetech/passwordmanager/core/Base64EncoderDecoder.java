/**
 * A class implementing the EncoderDecoder interface, which allows for Base64 encoding and decoding to be performed on the
 * password fields of Login objects.
 *
 * @author TeamFive Technology
 * @version 1.0.0
 */
package com.teamfivetech.passwordmanager.core;

import java.util.Base64;

class Base64EncoderDecoder implements EncoderDecoder {

    private Base64.Decoder decoder = Base64.getDecoder();
    private  Base64.Encoder encoder = Base64.getEncoder();

    /**
     * Method overriding EncoderDecoder encode(). Used to encode a String using Base64.
     * @param input unencoded String
     * @return encoded String
     */
    @Override       // interface EncoderDecoder
    public String encode(String input) {
        byte[] inputByte = input.getBytes();
        return encoder.encodeToString(inputByte);
    }

    /**
     * Method overriding EncoderDecoder decode(). Used to decode a String using Base64.
     * @param input encoded String
     * @return decoded String
     */
    @Override       // interface EncoderDecoder
    public String decode(String input) {
        String output = new String(decoder.decode(input));
        return output;
    }
}