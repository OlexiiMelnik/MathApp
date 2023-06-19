package math.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidationEquationServiceTest {
    private ValidationEquationService service;

    @BeforeEach
    void setUp() {
        service = new ValidationEquationService();
    }

    @Test
    void testIsValidEquation_TwoOperation() {
        Assertions.assertFalse(service.isValidEquation("2 + + 5 * x = 12"));
        Assertions.assertFalse(service.isValidEquation("3 / 0 * x = 15"));
        Assertions.assertFalse(service.isValidEquation("23 + * / x = 21"));
        Assertions.assertFalse(service.isValidEquation("5 + (3 + -x) = 5+"));
        Assertions.assertFalse(service.isValidEquation("5 * + (3 + -x) = 5"));
        Assertions.assertFalse(service.isValidEquation("5  + (3 + -x) = + 5"));
        Assertions.assertTrue(service.isValidEquation(" (9 / 3) + x = 6 "));
        Assertions.assertTrue(service.isValidEquation(" 6 + -x / 2 = 18 "));
    }

    @Test
    void testIsValidEquation_ContainOnlyX() {
        Assertions.assertFalse(service.isValidEquation("2 +  5 * y = 12"));
        Assertions.assertFalse(service.isValidEquation("3 + 6 = 9"));
        Assertions.assertTrue(service.isValidEquation(" (9 / 3) + x = 6 "));
        Assertions.assertTrue(service.isValidEquation(" 6 + -x / 2 = 18 "));
    }

    @Test
    void testIsValidEquation_BracketPlacement() {
        Assertions.assertFalse(service.isValidEquation("3 * (6 -x() = 14"));
        Assertions.assertFalse(service.isValidEquation("25 + (-x * 9 )) = 23"));
        Assertions.assertTrue(service.isValidEquation(" 3 + ( 6 * (2 -x)) = 2 "));
        Assertions.assertTrue(service.isValidEquation(" (25 - x) * 3 = 54 "));
    }
}