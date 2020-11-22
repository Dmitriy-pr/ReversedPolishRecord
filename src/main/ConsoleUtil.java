package main;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleUtil {
    private static Logger logger = Logger.getLogger(ConsoleUtil.class.getName());
    private ConsoleUtil() {}
    private static final Scanner scanner = new Scanner(System.in);

    public static void print(String str) {
        logger.log(Level.INFO, str);
    }

    public static void error(String str) {
        logger.log(Level.SEVERE, str);
    }

    public static String getRecord() {
        return scanner.nextLine();
    }
}
