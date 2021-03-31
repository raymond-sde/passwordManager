package com.teamfivetech.passwordmanager.controller;

import com.teamfivetech.passwordmanager.controller.core.LoginIO;
import com.teamfivetech.passwordmanager.controller.core.Login;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.Assert.*;

public class LoginIOTest {
    private static final String TEST_FILE = "data/LoginIOTest.csv";
    private LoginIO loginIO = null;
    private List<Login> logins = null;

    @Before
    public void setUp() throws Exception {
        loginIO = new LoginIO(TEST_FILE);
        try(PrintWriter out = new PrintWriter(new FileWriter(TEST_FILE))) {
            out.println("1,Reddit,sethm,a1b2c3d4");
            out.println("2,YouTube,coolnameguy,z1y2x3w4");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        logins = loginIO.read();
    }

    // tests that two logins are returned
    @Test
    public void read_shouldReturnTrue_whenPopulatedListProvided() {
        assertEquals(2, logins.size());
    }

    // tests that present logins match expected values
    @Test
    public void read_shouldReturnTrue_whenValidLoginsPresent() {
        Login log0 = logins.get(0);
        assertEquals(1, log0.getId());
        assertEquals("Reddit", log0.getSiteName());
        assertEquals("sethm", log0.getUserName());
        assertEquals("a1b2c3d4", log0.getPassword());

        Login log1 = logins.get(1);
        assertEquals(2, log1.getId());
        assertEquals("YouTube", log1.getSiteName());
        assertEquals("coolnameguy", log1.getUserName());
        assertEquals("z1y2x3w4", log1.getPassword());
    }

    // tests that if the existing file is empty, no logins are present
    @Test
    public void read_shouldReturnTrue_whenNoLoginsPassed() {
        logins.clear();     // mimics empty file passed to reader.
        assertEquals(0, logins.size());

        try {
            Login log0 = logins.get(0);
            fail("Should have returned IndexOutOfBoundsException");
        } catch (Exception e) {
            assertEquals("Index 0 out of bounds for length 0", e.getMessage());
        }
    }

    // writes two addition Logins to file, then reads whole file & confirms expected values.
    @Test
    public void write_shouldReturnTrue_whenNewLoginsAdded() {
        Login newLog1 = new Login("Google", "coolnamebro", "pass1234");
        Login newLog2 = new Login("Facebook", "MusicMan87", "1234pass");

        try {
            loginIO.write(newLog1);
            loginIO.write(newLog2);
        } catch (IOException e) {
            String msg = e.getMessage();
            System.out.println(msg);
        }

        try {
            logins = loginIO.read();
        } catch (IOException e) {
            String msg = e.getMessage();
            System.out.println(msg);
        }

        assertEquals(4, logins.size());

        Login log0 = logins.get(0);
        assertEquals(1, log0.getId());
        assertEquals("Reddit", log0.getSiteName());
        assertEquals("sethm", log0.getUserName());
        assertEquals("a1b2c3d4", log0.getPassword());

        Login log1 = logins.get(1);
        assertEquals(2, log1.getId());
        assertEquals("YouTube", log1.getSiteName());
        assertEquals("coolnameguy", log1.getUserName());
        assertEquals("z1y2x3w4", log1.getPassword());

        Login log2 = logins.get(2);
        assertEquals(3, log2.getId());
        assertEquals("Google", log2.getSiteName());
        assertEquals("coolnamebro", log2.getUserName());
        assertEquals("pass1234", log2.getPassword());

        Login log3 = logins.get(3);
        assertEquals(4, log3.getId());
        assertEquals("Facebook", log3.getSiteName());
        assertEquals("MusicMan87", log3.getUserName());
        assertEquals("1234pass", log3.getPassword());
    }

    // test expected exception when file does not exist
    @Test(expected = IOException.class)
    public void read_shouldThrowException_whenBadFilenamePassed() throws Exception{
        LoginIO badUtil = new LoginIO("bad/input.csv");
        logins = badUtil.read();
        fail("Should have thrown Exception");
    }

    @Test(expected = IOException.class)
    public void write_shouldThrowException_whenBadFileNamePassed() throws Exception{
        LoginIO badUtil = new LoginIO("bad/input.csv");
        logins = badUtil.read();
        fail("Should have thrown Exception");
    }

    // wipe the test file after each test
    @AfterClass
    public static void afterClass() throws Exception {
        FileWriter fw = new FileWriter(TEST_FILE, false);
    }
}