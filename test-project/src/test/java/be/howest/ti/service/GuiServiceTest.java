package be.howest.ti.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuiServiceTest {

    @Test
    void isInputEmptyFalse() {
        //Arrange: create a GuiService object, create input
        GuiService guiService = new GuiService();
        String username = "username1";
        String password = "password1";
        //Act: call the method isInputEmpty
        boolean result = guiService.isInputEmpty(username, password);

        //Assert: check if the method returns false
        assertFalse(result);
    }


    @Test
    void isInputEmptyTrue() {
        //Arrange: create a GuiService object, create input
        GuiService guiService = new GuiService();
        String username = "";
        String password = "password1";
        //Act: call the method isInputEmpty
        boolean result = guiService.isInputEmpty(username, password);

        //Assert: check if the method returns true
        assertTrue(result);
    }
}
