package main;

import java.util.*;

class ParseRecord {

    private static final String OPERATORS = "+-*/";
    private static final String DELIMITERS = "() " + OPERATORS;
    private boolean flag = true;
    private final List<String> postfix = new ArrayList<>();
    private Deque<String> stack = new ArrayDeque<>();

    public boolean isFlag() {
        return flag;
    }

    private static boolean isDelimiter(String token) {
        if (token.length() != 1) {
            return false;
        }
        for (int i = 0; i < DELIMITERS.length(); i++) {
            if (token.charAt(0) == DELIMITERS.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOperator(String token) {
        if (token.equals("u-")) {
            return true;
        }
        for (int i = 0; i < OPERATORS.length(); i++) {
            if (token.charAt(0) == OPERATORS.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static int getPriority(String token) {
        if (token.equals("(")) {
            return 1;
        } else if (token.equals("+") || token.equals("-")) {
            return 2;
        } else if (token.equals("*") || token.equals("/")) {
            return 3;
        }
        return 4;
    }

    public List<String> parse(String record) {
        flag = true;
        StringTokenizer tokenizer = new StringTokenizer(record, DELIMITERS, true);
        String prev = "";
        String curr;
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
                ConsoleUtil.error("Некорректное выражение");
                flag = false;
                return postfix;
            } else if (curr.equals(" ")) {
                continue;
            } else if (isDelimiter(curr)) {
                if (curr.equals("(")) {
                    stack.push(curr);
                } else if (curr.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        postfix.add(stack.pop());
                        if (stack.isEmpty()) {
                            ConsoleUtil.error("Ошибки со скобками!");
                            flag = false;
                            return postfix;
                        }
                    }
                    stack.pop();
                } else if (curr.equals("-") && (prev.equals("") || (isDelimiter(prev) && !prev.equals(")")))) {
                    curr = "u-";
                } else {
                    while (!stack.isEmpty() && (getPriority(curr) <= getPriority(stack.peek()))) {
                        postfix.add(stack.pop());
                    }
                    stack.push(curr);
                }
            } else {
                postfix.add(curr);
            }
            prev = curr;
        }
        stackNotEmpty();
        return postfix;
    }

    public void stackNotEmpty() {
        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) {
                postfix.add(stack.pop());
            } else {
                ConsoleUtil.error("Ошибки со скобками!");
                flag = false;
                return;
            }
        }
    }
}