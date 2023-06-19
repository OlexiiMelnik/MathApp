package math.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RootCheckServiceTest {
    private RootCheckService checkService;

    @BeforeEach
    void setUp() {
        checkService = new RootCheckService();
    }

    @Test
    void checkValidRoot() {
        Assertions.assertTrue(checkService.checkValidRoot("3 * (10 + x) = 45", "5"));
        Assertions.assertTrue(checkService.checkValidRoot("5 + 8 / x = 9", "2"));
        Assertions.assertTrue(checkService.checkValidRoot("((18 + 7) / 2) * 4 = 50", "7"));
        Assertions.assertFalse(checkService.checkValidRoot("3 * (10 + x) = 45", "8"));
        Assertions.assertFalse(checkService.checkValidRoot("5 + 8 / x = 9", "19"));
        Assertions.assertFalse(checkService.checkValidRoot("((18 + x) / 2) * 4 = 50", "21"));
    }
}