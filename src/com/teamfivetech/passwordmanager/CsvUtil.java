package com.teamfivetech.passwordmanager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class CsvUtil {
    private static final String PATH_STRING = "data/passwords.txt";

    public CsvUtil () {
    }

    public List<Login> read() throws IOException {
        List<Login> result = new ArrayList<>();

        Files.lines(Path.of(PATH_STRING)).forEach(line -> {
            String[] logins = line.split(",");

            String siteName = logins[1];
            String userName = logins[2];
            String password = logins[3];

            result.add(new Login(siteName, userName, password));
        });
        return result;
    }

    public void write(List<Login> passwords) {
        try (PrintWriter out = new PrintWriter(new FileWriter(PATH_STRING))) {
            for (Login login : passwords) {
                out.println(login.toString());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}