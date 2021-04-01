package com.teamfivetech.passwordmanager.core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements Base64 encoding to encode the Login object's password in a .csv file.
 *
 * @author TeamFive Technology
 * @version 1.0.0
 */
public class LoginIO {
    private String pwFile;

    private Base64EncoderDecoder encoder = new Base64EncoderDecoder();

    /**
     * Constructor method used to instantiate a new LoginIO object writing to a specific file path.
     * @param pwFilePath a String representing the filepath the LoginIO object is reading and writing to. EXAMPLE: "data/passwords.csv"
     */
    public LoginIO(String pwFilePath) {
        this.pwFile = pwFilePath;
    }

    /**
     * Method used to read and create a List of Login objects from provided .csv file.
     * @return a List of Login objects.
     * @throws IOException throws IOException
     */
    public List<Login> read() throws IOException {
        List<Login> result = new ArrayList<>();
        Login.setCounter(1);        // resets counter to 1 every time the file is read so the first Login is 1.
        Files.lines(Path.of(pwFile)).forEach(line -> {
            String[] logins = line.split(",");

            String siteName = logins[1];
            String userName = logins[2];
            String password = encoder.decode(logins[3]);

            result.add(new Login(siteName, userName, password));
        });
        return result;
    }

    /**
     * Method used to write a Login object to the .csv file provided.  New Logins are appended to the end of the file.
     * @param login a Login object
     * @throws IOException throws IOException
     */
    public void write(Login login) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(pwFile, true))) {
            String output = login.getId() + "," + login.getSiteName() + "," + login.getUserName() + ","
                    + encoder.encode(login.getPassword());
            out.println(output);
        }
        catch (IOException e){
            throw e;
        }
    }
}