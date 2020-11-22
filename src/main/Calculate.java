package main;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Calculate {
    private Calculate() {
    }

    public static Double calculate(List<String> postfix) {
        try {
            Deque<Double> stack = new ArrayDeque<>();
            for (String x : postfix) {
                if (x.equals("+")) stack.push(stack.pop() + stack.pop());
                else if (x.equals("-")) {
                    Double b = stack.pop();
                    Double a = stack.pop();
                    stack.push(a - b);
                } else if (x.equals("*")) {
                    stack.push(stack.pop() * stack.pop());
                } else if (x.equals("/")) {
                    Double b = stack.pop();
                    Double a = stack.pop();
                    if (b == 0) {
                        ConsoleUtil.error("Деление на ноль!");
                        return null;
                    } else {
                        stack.push(a / b);
                    }
                } else if (x.equals("u-")) stack.push(-stack.pop());
                else stack.push(Double.valueOf(x));
            }
            return stack.pop();
        } catch (Exception ex) {
            ConsoleUtil.error("Возникла ошибка при вычислении! Код ошибки: " + ex.getLocalizedMessage());
        }
        return null;
    }
}
