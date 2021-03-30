package com.teamfivetech.passwordmanager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CsvUtilTest {
    private static final String TEST_FILE = "data/CsvUtilTest.csv";
    private CsvUtil csvUtil = null;
    private List<Login> logins = null;

    @Before
    public void setUp() throws Exception {
        csvUtil = new CsvUtil(TEST_FILE);
        try(PrintWriter out = new PrintWriter(new FileWriter(TEST_FILE))) {
            out.println("1,Reddit,sethm,a1b2c3d4");
            out.println("2,YouTube,coolnameguy,z1y2x3w4");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        logins = csvUtil.read();
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

        // insert two existing Logins for testing
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
        logins.add(newLog1);
        logins.add(newLog2);
        csvUtil.write(logins);

        List<Login> readLogins = new ArrayList<>();

        try {
            readLogins = csvUtil.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(4, logins.size());

        Login log0 = readLogins.get(0);
        assertEquals(1, log0.getId());
        assertEquals("Reddit", log0.getSiteName());
        assertEquals("sethm", log0.getUserName());
        assertEquals("a1b2c3d4", log0.getPassword());

        Login log1 = readLogins.get(1);
        assertEquals(2, log1.getId());
        assertEquals("YouTube", log1.getSiteName());
        assertEquals("coolnameguy", log1.getUserName());
        assertEquals("z1y2x3w4", log1.getPassword());

        Login log2 = readLogins.get(2);
        assertEquals(3, log2.getId());
        assertEquals("Google", log2.getSiteName());
        assertEquals("coolnamebro", log2.getUserName());
        assertEquals("pass1234", log2.getPassword());

        Login log3 = readLogins.get(3);
        assertEquals(4, log3.getId());
        assertEquals("Facebook", log3.getSiteName());
        assertEquals("MusicMan87", log3.getUserName());
        assertEquals("1234pass", log3.getPassword());
    }

    // test expected exception when file does not exist
    @Test(expected = NoSuchFileException.class)
    public void read_shouldThrowException_whenBadFilenamePassed() throws Exception{
        CsvUtil badUtil = new CsvUtil("bad/input.csv");
        logins = badUtil.read();
    }

    // wipe the test file after each test
    @AfterClass
    public static void afterClass() throws Exception {
        FileWriter fw = new FileWriter(TEST_FILE, false);
    }
}