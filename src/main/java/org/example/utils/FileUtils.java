package org.example.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static void createNewFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
