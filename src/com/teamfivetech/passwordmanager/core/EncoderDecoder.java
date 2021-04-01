package com.teamfivetech.passwordmanager.core;

/**
 * An interface detailing the required methods for an EncoderDecoder implementation.
 *
 * @author TeamFive Technology
 * @version 1.0.0
 */
public interface EncoderDecoder {

    public abstract String encode(String input);

    public abstract String decode(String input);
}
