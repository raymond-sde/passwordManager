package com.teamfivetech.passwordmanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class CsvUtil {
    private Path pwFilePath;
    private String pwFile;

    public CsvUtil(String pwFilePath) {
        this.pwFile = pwFilePath;
        this.pwFilePath = Path.of(pwFilePath);
    }

    public List<Login> read() throws IOException {
        List<Login> result = new ArrayList<>();
        Login.setCounter(1);        // resets counter to 1 every time the file is read so the first Login is 1.

        Files.lines(pwFilePath).forEach(line -> {
            String[] logins = line.split(",");

            String siteName = logins[1];
            String userName = logins[2];
            String password = logins[3];

            result.add(new Login(siteName, userName, password));
        });
        return result;
    }

    public void write(Login login) {
        try (PrintWriter out = new PrintWriter(new FileWriter(pwFile, true))) {
            String output = login.getId() + "," + login.getSiteName() + "," + login.getUserName() + ","
                                + login.getPassword();
            out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}