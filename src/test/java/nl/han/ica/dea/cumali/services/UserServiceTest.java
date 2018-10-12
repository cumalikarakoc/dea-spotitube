package nl.han.ica.dea.cumali.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setup(){
        userService = new UserService();
    }

//    @Test
//    void testShouldReturnTrueGivenCorrectUserCredentials(){
//        boolean returnValue = userService.authenticate("cumali", "admin123");
//        Assertions.assertTrue(returnValue);
//    }

//    @Test
//    void testShouldReturnFalseGivenIncorrectUserCredentials(){
//        boolean returnValue = userService.authenticate("test", "test");
//        Assertions.assertFalse(returnValue);
//    }
}
