package com.teamfivetech.passwordmanager.controller.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Base64EncoderDecoderTest {

    private Base64EncoderDecoder encoder = null;
    private Login log1 = null;
    private Login log2 = null;
    byte[] pwByte1 = null;
    byte[] pwByte2 = null;
    String encodedPW1 = null;
    String encodedPW2 =null;

    @Before
    public void setUp() throws Exception {
        encoder = new Base64EncoderDecoder();
        log1 = new Login("Google", "GoodName", "pass1234");
        log2 = new Login("Reddit", "CoolName", "1234pAsS");

        pwByte1 = log1.getPassword().getBytes();
        pwByte2 = log2.getPassword().getBytes();

        encodedPW1 = encoder.encode(pwByte1);
        encodedPW2 = encoder.encode(pwByte2);
    }

    @Test
    public void encode_shouldReturnFalse_whenValidPasswordEncoded() {
        assertNotEquals(log1.getPassword(), encodedPW1);
        assertNotEquals(log2.getPassword(), encodedPW2);
    }

    @Test
    public void decode_shouldReturnTrue_whenValidPasswordDecoded() {
        String decodedPW1 = new String(encoder.decode(encodedPW1));
        String decodedPW2 = new String(encoder.decode(encodedPW2));

        assertEquals(log1.getPassword(), decodedPW1);
        assertEquals(log2.getPassword(), decodedPW2);
    }

    @Test(expected = NullPointerException.class)
    public void encode_shouldThrowNullPointerException_whenNullPasswordPassed() {
        log1 = new Login("Google", "GoodName", null);

        pwByte1 = log1.getPassword().getBytes();
        encodedPW1 = encoder.encode(pwByte1);
    }

    @Test(expected = NullPointerException.class)
    public void decode_shouldThrowNullPointerException_whenNullEncryptionPassed() {
        encodedPW1 = null;
        encoder.decode(encodedPW1);
    }
}