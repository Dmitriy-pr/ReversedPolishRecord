package main;

public class Validate {
    private Validate() {}

    public static boolean isCorrectRecord(String record) {
        return !hasInvalidSymbol(record.toCharArray()) && !isCorrectBrackets(record.toCharArray());
    }

    private static boolean hasInvalidSymbol(char[] chars) {
        String alphabet = "qwertyuiopasdfghjklzxcvbnmйцукенгшщзхъфывапролджэячсмитьбюё,<>\\|=_!@#$%^&?;:'\"";
        char[] alphabetChars = alphabet.toCharArray();
        for (char inputted : chars) {
            for (char symbol : alphabetChars) {
                if (inputted == symbol) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isCorrectBrackets(char[] chars) {
        int leftBracket = 0;
        int rigthBracket = 0;
        for (char inputted : chars) {
            if (inputted == '(') {
                leftBracket++;
            } else if (inputted == ')') {
                rigthBracket++;
            }
        }
        return leftBracket == rigthBracket;
    }
}
