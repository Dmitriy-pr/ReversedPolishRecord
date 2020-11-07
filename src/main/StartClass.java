package main;

import java.util.*;

public class StartClass {

    private static Double calculate(List<String> postfix) {
        try {
            Deque<Double> stack = new ArrayDeque<>();
            for (String x : postfix) {
                if (x.equals("+")) {
                    stack.push(stack.pop() + stack.pop());
                } else if (x.equals("-")) {
                    Double b = stack.pop(), a = stack.pop();
                    stack.push(a - b);
                } else if (x.equals("*")) {
                    stack.push(stack.pop() * stack.pop());
                } else if (x.equals("/")) {
                    Double b = stack.pop(), a = stack.pop();
                    stack.push(a / b);
                } else if (x.equals("u-")) {
                    stack.push(-stack.pop());
                } else {
                    stack.push(Double.valueOf(x));
                }
            }
            return stack.pop();
        } catch (Exception ex) {
            System.err.println("Некорректное выражение! Код ошибки: " + ex.getLocalizedMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.print("Введите выражение: ");
        Scanner in = new Scanner(System.in);
        String record = in.nextLine();
        ParseRecord parseRecord = new ParseRecord();
        List<String> expression = parseRecord.parse(record);
        boolean flag = parseRecord.flag;
        if (flag) {
            Double value = calculate(expression);
            if (value != null) {
                System.out.println("Ответ : " + value);
            }
        }
    }
}
