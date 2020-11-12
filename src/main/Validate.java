package main;

public class Validate {

    public static boolean isCorrectRecord(String record) {
        if (hasInvalidSymbol(record.toCharArray())) {
            return false;
        }
        if (isCorrectBrackets(record.toCharArray())) {
            return false;
        }
        return true;
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
            }
            if (inputted == ')') {
                rigthBracket++;
            }
        }
        if (leftBracket != rigthBracket) {
            return false;
        }
        return true;
    }
}
