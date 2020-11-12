package main;

import java.util.Scanner;

public class ConsoleUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static void print(String str) {
        System.out.print(str);
    }

    public static void error(String str) {
        System.err.print(str);
    }

    public static String getRecord() {
        return scanner.nextLine();
    }
}
