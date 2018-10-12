package nl.han.ica.dea.cumali.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

public class HomeControllerTest {
    @Test
    void testShouldReturn200WhenNavigatedToHomepage(){
        HomeController homeController = new HomeController();
        Response response = homeController.routes();

        Assertions.assertEquals(200, response.getStatus());
    }
}
