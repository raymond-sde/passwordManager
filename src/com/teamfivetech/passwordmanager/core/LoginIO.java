package com.teamfivetech.passwordmanager.core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LoginIO {
    private String pwFile;

    private Base64EncoderDecoder encoder = new Base64EncoderDecoder();

    public LoginIO(String pwFilePath) {
        this.pwFile = pwFilePath;
    }

    public List<Login> read() throws IOException {
        List<Login> result = new ArrayList<>();
        Login.setCounter(1);        // resets counter to 1 every time the file is read so the first Login is 1.
        Files.lines(Path.of(pwFile)).forEach(line -> {
            String[] logins = line.split(",");

            String siteName = logins[1];
            String userName = logins[2];
            String password = new String(encoder.decode(logins[3]));

            result.add(new Login(siteName, userName, password));
        });
        return result;
    }

    public void write(Login login) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(pwFile, true))) {
            byte[] passwordByte = login.getPassword().getBytes();
            String output = login.getId() + "," + login.getSiteName() + "," + login.getUserName() + ","
                    + encoder.encode(passwordByte);
            out.println(output);
        }
        catch (IOException e){
            throw e;
        }
    }
}