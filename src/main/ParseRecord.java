package main;

import java.util.*;

class ParseRecord {

    private static String operators = "+-*/";
    private static String delimiters = "() " + operators;
    public boolean flag = true;

    private static boolean isDelimiter(String token) {
        if (token.length() != 1) {
            return false;
        }
        for (int i = 0; i < delimiters.length(); i++) {
            if (token.charAt(0) == delimiters.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOperator(String token) {
        if (token.equals("u-")) {
            return true;
        }
        for (int i = 0; i < operators.length(); i++) {
            if (token.charAt(0) == operators.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static int getPriority(String token) {
        if (token.equals("(")) {
            return 1;
        }
        if (token.equals("+") || token.equals("-")) {
            return 2;
        }
        if (token.equals("*") || token.equals("/")) {
            return 3;
        }
        return 4;
    }

    public List<String> parse(String record) {
        flag = true;
        List<String> postfix = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(record, delimiters, true);
        String prev = "";
        String curr;
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
                ConsoleUtil.error("Некорректное выражение");
                flag = false;
                return postfix;
            }
            if (curr.equals(" ")) {
                continue;
            }
            if (isDelimiter(curr)) {
                if (curr.equals("(")) {
                    stack.push(curr);
                }
                else if (curr.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        postfix.add(stack.pop());
                        if (stack.isEmpty()) {
                            ConsoleUtil.error("Ошибки со скобками!");
                            flag = false;
                            return postfix;
                        }
                    }
                    stack.pop();
                } else {
                    if (curr.equals("-") && (prev.equals("") || (isDelimiter(prev) && !prev.equals(")")))) {
                        curr = "u-";
                    } else {
                        while (!stack.isEmpty() && (getPriority(curr) <= getPriority(stack.peek()))) {
                            postfix.add(stack.pop());
                        }
                    }
                    stack.push(curr);
                }
            } else {
                postfix.add(curr);
            }
            prev = curr;
        }

        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) {
                postfix.add(stack.pop());
            }
            else {
                ConsoleUtil.error("Ошибки со скобками!");
                flag = false;
                return postfix;
            }
        }
        return postfix;
    }
}