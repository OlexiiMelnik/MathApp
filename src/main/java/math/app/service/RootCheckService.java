package math.app.service;

import org.mariuszgromada.math.mxparser.Expression;
import org.springframework.stereotype.Component;

@Component
public class RootCheckService {
    public boolean checkValidRoot(String equation, String x) {
        String equationWithRoot = equation.replaceAll("x", x);
        String normalizedString = equationWithRoot.replaceAll("\\s", "");
        String[] parts = normalizedString.split("=");
        double left = evaluateExpression(parts[0]);
        double right = evaluateExpression(parts[1]);
        return left == right;
    }

    private static double evaluateExpression(String expression) {
        Expression e = new Expression(expression);
        return e.calculate();
    }
}
