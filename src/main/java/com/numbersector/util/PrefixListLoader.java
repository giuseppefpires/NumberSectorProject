package com.numbersector.util;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PrefixListLoader {

    private static final String PREFIXES_FILE_PATH = "src/main/resources/static/prefixes.txt";
    public static Set<String> prefixList = new HashSet<>();

    public PrefixListLoader() throws IOException {
        InputStream file = new FileInputStream(PREFIXES_FILE_PATH);
        BufferedReader buf = new BufferedReader(new InputStreamReader(file));
        String line = buf.readLine();
        while (line != null) {
            prefixList.add(line);
            line = buf.readLine();
        }
        buf.close();
    }
}
