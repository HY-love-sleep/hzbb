package com.hy.aviator_test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Description: txt转String
 * Author: yhong
 * Date: 2024/1/8
 */
public class TxtToStringUtil {
    public static String txtToString(String filePath) {

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\\A");

            String content = scanner.hasNext() ? scanner.next() : "";
            scanner.close();
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
