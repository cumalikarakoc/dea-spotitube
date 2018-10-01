package nl.han.ica.dea.cumali.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LoginServiceTest {
    private LoginService loginService;

    @BeforeEach
    void setup(){
        loginService = new LoginService();
    }

    @Test
    void testShouldReturnTrueGivenCorrectUserCredentials(){
        boolean returnValue = loginService.authenticate("cumali", "admin123");
        Assertions.assertTrue(returnValue);
    }

    @Test
    void testShouldReturnFalseGivenIncorrectUserCredentials(){
        boolean returnValue = loginService.authenticate("user", "pass");
        Assertions.assertFalse(returnValue);
    }
}
