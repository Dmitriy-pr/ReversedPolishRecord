package main;

import java.util.*;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        ParseRecord parseRecord = new ParseRecord();
        boolean end = false;
        while (!end) {
            ConsoleUtil.print("\nВведите выражение: ");
            String record = ConsoleUtil.getRecord();
            if (record == null || record.isEmpty()) {
                end = true;
            } else if (!Validate.isCorrectRecord(record)) {
                List<String> expression = parseRecord.parse(record);
                boolean flag = parseRecord.isFlag();
                if (flag) {
                    Double value = Calculate.calculate(expression);
                    if (value != null) {
                        ConsoleUtil.print("Ответ : " + value + "\n");
                    }
                }
            } else {
                ConsoleUtil.error("Выражение содержит недопустимые символы!");
            }
        }
        Thread.sleep(1000);
    }
}
