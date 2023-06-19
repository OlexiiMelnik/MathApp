package math.app.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class ValidationEquationService {
    public boolean isValidEquation(String equationString) {
        if (!isContainOnlyX(equationString)) {
            return false;
        }
        if (!isBracketPlacementValid(equationString)) {
            return false;
        }
        if (!isNotHaveTwoOperation(equationString)) {
            return false;
        }
        return true;
    }

    private boolean isNotHaveTwoOperation(String equationString) {
        String normalizedString = equationString.replaceAll("\\s", "");
        int operatorCount = 0;
        if (isOperator(normalizedString.charAt(normalizedString.length() - 1))) {
            return false;
        }
        for (int i = 0; i < normalizedString.length() - 1; i++) {
            char currentChar = normalizedString.charAt(i);
            char nextChar = normalizedString.charAt(i + 1);
            if (currentChar == '=' && isOperator(nextChar) && nextChar != '-') {
                return false;
            }
            if (currentChar == '/' && nextChar == '0') {
                return false;
            }
            if (isOperator(currentChar) && isOperator(nextChar)) {
                operatorCount++;

                if (operatorCount >= 2) {
                    return false;
                }

                if (nextChar == '-') {
                    continue;
                }

                return false;
            }
            if (isOperator(currentChar) && !isOperator(nextChar)) {
                if (!Character.isDigit(nextChar) && nextChar != 'x'
                        && nextChar != ')' && nextChar != '(') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isContainOnlyX(String equationString) {
        String normalizedString = equationString.replaceAll("\\s", "");
        String pattern = "^[^a-wyzA-WYZ]*x+[^a-wyzA-WYZ]*$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(normalizedString);
        return matcher.matches();
    }

    private boolean isBracketPlacementValid(String equationString) {
        String normalizedString = equationString.replaceAll("\\s", "");
        int openBracketCount = 0;
        int closeBracketCount = 0;
        boolean isInsideBracket = false;
        for (int i = 0; i < normalizedString.length(); i++) {
            char c = normalizedString.charAt(i);
            if (c == '(') {
                openBracketCount++;
                isInsideBracket = true;
            } else if (c == ')') {
                closeBracketCount++;
                isInsideBracket = false;
                int lastSymbolIndex = i - 1;
                if (lastSymbolIndex >= 0) {
                    char lastSymbol = normalizedString.charAt(lastSymbolIndex);
                    if (!Character.isDigit(lastSymbol) && lastSymbol != 'x'
                            && lastSymbol != ')' && lastSymbol != '(') {
                        return false;
                    }
                }
            } else if (isInsideBracket && c == '=') {
                return false;
            } else if (isInsideBracket) {
                int symbolCount = getSymbolCountBetweenBrackets(normalizedString, i);
                if (symbolCount < 3) {
                    return false;
                }
            }
        }
        return openBracketCount == closeBracketCount;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private int getSymbolCountBetweenBrackets(String equationString, int currentIndex) {
        int symbolCount = 0;
        for (int i = currentIndex; i < equationString.length(); i++) {
            char c = equationString.charAt(i);
            if (c != '(' && c != ')') {
                symbolCount++;
            }
        }
        return symbolCount;
    }
}
